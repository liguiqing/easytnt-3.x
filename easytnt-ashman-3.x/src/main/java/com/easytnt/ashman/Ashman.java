package com.easytnt.ashman;

import com.easytnt.ashman.ssdb.SSDB;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.file.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.stream.Stream;

/**
 * @author Liguiqing
 * @since V3.0
 */

public class Ashman {
    private static final Logger logger = LoggerFactory.getLogger(Ashman.class);

    private File log;

    private int from ;

    private int to ;

    private Properties config;

    private List<String> cleanFileNames;

    private String workPath;

    private boolean removeToSSDB;

    private SSDB ssdb;

    private long examId = 0;

    private boolean up;

    private boolean cleaning = true;

    private boolean deleteMd5Path;

    private List<String> logContent;

    private long cleanTotal;

    public static void main(String[] args){
        Ashman ashman = new Ashman();
        ashman.clean();
    }

    public Ashman(){
        logger.debug("Ashman is running");

        readConfig();
        readExamId();

        this.workPath = getConfig("work.path");
        this.up = getIntConfig("work.up") > 0;
        this.deleteMd5Path = getIntConfig("work.md5.delete") > 0;

        this.from = getIntConfig("work.time.start");
        this.to = getIntConfig("work.time.end");

        String fileNames = getConfig("img.origin.names");
        this.cleanFileNames = Arrays.asList(fileNames.split(";"));

        this.removeToSSDB = getIntConfig("img.remove") > 0;


        String host = getConfig("ssdb.host");
        int port = getIntConfig("ssdb.port");
        try{
            logger.debug("Connect to SSDB : " +host +" "+ port);
            this.ssdb = new SSDB(host,port);
            logger.debug("Connect to SSDB success");
        }catch (Exception e){
            logger.error(e.getLocalizedMessage());
        }
    }

    private int getIntConfig(String key){
        String value = getConfig(key);
        return Integer.valueOf(value);
    }

    private String getConfig(String key){
        return config.get(key)+"";
    }

    private void readConfig(){
        config = new Properties();
        InputStream inputStream = Ashman.class.getClassLoader().getResourceAsStream("config.properties");
        if(inputStream == null){
            try {
                inputStream = new FileInputStream(FileSystems.getDefault().getPath("./config.properties").toFile());
            } catch (FileNotFoundException e) {
                logger.debug("Can not read config.properties");
                logger.error(e.getLocalizedMessage());
            }
        }
        try {
            config.load(inputStream);
        } catch (IOException e) {
            logger.debug("Can not read config.properties");
            logger.error(e.getLocalizedMessage());
        }

    }

    public void clean() {
        Md5DataSource md5ds = new Md5DataSource(getConfig("jdbc.driver"),
                getConfig("jdbc.url"),getConfig("jdbc.username"),getConfig("jdbc.password"));
        this.logContent = new ArrayList<>();
        while(true) {
            if (isWorkTime()) {
                initLog();
                cleaning = true;
                List<String> md5s = md5ds.next(this.examId);
                updateExamId();
                md5s.forEach(md5 -> destroy(md5));
                StringBuilder sb = new StringBuilder();
                this.logContent.forEach(s -> sb.append(s).append("\n"));
                writeLog(sb.toString());
                this.logContent.clear();
                writeExamId();
            } else {
                if(cleaning)
                    writeExamId();
                cleaning = false;
                LocalDateTime now = LocalDateTime.now();
                LocalDateTime startTime = now.plusHours(24-now.getHour() + this.from).with(LocalTime.MIN);
                this.logContent.clear();
                long sleep = startTime.toInstant(ZoneOffset.of("+8")).toEpochMilli() - now.toInstant(ZoneOffset.of("+8")).toEpochMilli();
                try {
                    logger.debug("Next work time is {} ,I will sleep {} hours,see you!", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(startTime),(24-now.getHour() + this.from));
                    md5ds.closeConnection();
                    Thread.sleep(sleep-1000);
                } catch (InterruptedException e) {
                    logger.error(e.getLocalizedMessage());
                }
            }
            if(this.examId == 0 || this.examId > 10000){
                writeExamId();
                System.exit(0);
            }
        }
    }

    private void updateExamId() {
        if(this.up){
            this.examId++;
        }else{
            if(this.examId > 0 )
                this.examId--;
        }
    }

    private void writeExamId(){
        Path examIdFile = getExamIdFile();
        try (BufferedWriter writer = Files.newBufferedWriter(examIdFile,StandardOpenOption.APPEND)){
            writer.write(this.examId+"@"+LocalDateTime.now()+" ---> Clean total " + (this.cleanTotal/1024/1024) +"Mb");
            this.cleanTotal = 0;
        } catch (IOException e) {
            logger.error(e.getLocalizedMessage());
        }
    }

    private void initLog(){
        LocalDate today = LocalDate.now();
        String logFileName = "./md5-"+today.toString()+".log";
        File logFile = FileSystems.getDefault().getPath(logFileName).toFile();
        if(!logFile.exists()){
            try {
                boolean b = logFile.createNewFile();
                if(!b){
                    logger.debug("Log file " + logFileName + "create failure");
                }else{
                    this.log = logFile;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else {
            this.log = logFile;
        }
    }

    private void readExamId(){
        Path examIdFile = getExamIdFile();
        try(BufferedReader reader =Files.newBufferedReader(examIdFile)) {
            reader.lines().forEach(line->{
                String[] s = line.split("@");
                if(s.length>1)
                    this.examId = Long.valueOf(s[0]);
            });
        } catch (IOException e) {
            logger.error(e.getLocalizedMessage());
        }
    }

    private Path getExamIdFile(){
        Path path = Paths.get("examid.txt");
        if(!Files.exists(path)){
            try {
                Files.createFile(path);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return path;
    }

    private boolean isWorkTime(){
        LocalDateTime now = LocalDateTime.now();
        return now.getHour()>=(this.from) && now.getHour() <= this.to;
    }

    private void destroy(String md5) {
        String fpath = toPath(md5);
        Path path = Paths.get(fpath);
        File md5Dir = path.toFile();
        StringBuilder sb = new StringBuilder(md5);
        sb.append(":{path:").append(fpath).append(",");
        if(md5Dir.isDirectory()){
            File[] imgs = md5Dir.listFiles();
            if(imgs == null || imgs.length == 0)
                return;
            Stream.of(imgs).filter(img->!cleanFileNames.contains(img.getName())).forEach(img->{
                long space = img.length();
                if(img.delete()){
                    sb.append(img.getName()).append(",");
                    this.cleanTotal += space;
                    logger.debug("File "+img.getName()+" has bean deleted");
                }
            });
        }
        sb.append("}");
        this.logContent.add(sb.toString());
        removeToSSDB(md5,path);
        deleteMd5(path);
    }

    private void deleteMd5(Path path) {
        if(this.deleteMd5Path) {
            deleteDir(path);
        }
    }

    private void deleteDir(Path path){
        File dir = path.toFile();
        if(dir.getAbsolutePath().equals(this.workPath)){
            return;
        }

        if(dir.isDirectory()){
            File[] files = dir.listFiles();
            if(files != null){
                Stream.of(files).forEach(file -> {
                    this.cleanTotal += file.length();
                    file.delete();
                });
            }
            Path parentDir = Paths.get(dir.getParent());
            if(!dir.delete()){
                return;
            }
            files = parentDir.toFile().listFiles();
            if(files != null && files.length == 0){
                deleteDir(parentDir);
            }
        }
    }

    private void removeToSSDB(String key,Path path){
        if(!path.toFile().exists()){
            return;
        }

        if(this.removeToSSDB){
            File[] imgs = path.toFile().listFiles();
            if(imgs != null && imgs.length > 0){
                try {
                    Path img = imgs[0].toPath();
                    logger.debug("Removing image {} to SSDB ",img.toUri().toString());
                    this.ssdb.set(key, Files.readAllBytes(imgs[0].toPath()));
                    logger.debug("Remove image {} to SSDB success",img.toUri().toString());
                } catch (Exception e) {
                    logger.error(e.getLocalizedMessage());
                }
            }
        }
    }

    private void writeLog(String logContent) {
        try (BufferedWriter writer = Files.newBufferedWriter(this.log.toPath(), StandardOpenOption.APPEND)){
            writer.append(logContent);
        } catch (IOException e) {
            logger.error(e.getLocalizedMessage());
        }
    }

    private  String toPath(String md5){
        String f3 = md5.substring(0, 3);
        String m3 = md5.substring(3, 6);
        return this.workPath+"/"+quarterOfHex(f3) + "/" + quarterOfHex(m3) + "/" + md5;
    }

    private  int quarterOfHex(String str) {
        return Integer.valueOf(str, 16) / 4;
    }

}