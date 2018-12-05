package com.easytnt.ashman;

import com.hyd.ssdb.SsdbClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileSystems;
import java.util.Deque;
import java.util.Properties;
import java.util.concurrent.*;

/**
 * @author Liguiqing
 * @since V3.0
 */

public class Ssdb2Ssdb {
    private static final Logger logger = LoggerFactory.getLogger(Ssdb2Ssdb.class);

    private Properties config;

    private SsdbClient srcClient;

    private SsdbClient targetClient;

    public static void main(String[] args) throws Exception {
        logger.debug("start move");
        Ssdb2Ssdb s2s = new Ssdb2Ssdb();
        s2s.readConfig();
        s2s.initClient();
        s2s.start();
    }

    private void start() {
        Md5Removeable md5s = new Md5Removeable(getConfig("jdbc.driver"),
                getConfig("jdbc.url"),getConfig("jdbc.username"),getConfig("jdbc.password"));
        int threadable = getConfigInt("s2s.threadable");
        if(threadable >0) {
            useThreads(md5s);
        }else{
            noThreads(md5s);
        }

        logger.debug("Remove completed");

    }

    private void noThreads(Md5Removeable md5s){
        while(true){
            Deque<String> md5List = md5s.next();
            if(md5List.isEmpty())
                break;
            while(!md5List.isEmpty()){
                copy(md5List.pop());
            }
        }
    }

    private void useThreads(Md5Removeable md5s){
        int workThread = getConfigInt("s2s.thread");
        ExecutorService service = Executors.newFixedThreadPool(workThread);

        while(true){
            Deque<String> md5List = md5s.next();
            if(md5List.isEmpty())
                break;
            while(!md5List.isEmpty()){
                Future future = service.submit(()-> copy( md5List.pop()));
                future.isDone();
            }
        }
        service.shutdown();
    }

    private void copy(String md5){
        copy(md5,1);
    }

    private void copy(String md5,int trys){
        if(trys == 5)
            return;

        logger.debug("Copy {}",md5);
        byte[] imgBytes = this.srcClient.getBytes(md5);
        if(imgBytes != null && imgBytes.length > 0 )
            this.targetClient.setnx(md5,imgBytes);
        int del = getConfigInt("s2s.src.del");
        if(del > 0 && this.targetClient.exists(md5)){
            logger.debug("Delete {}",md5);
            this.srcClient.del(md5);
        }
    }

    private void initClient(){
        String srcHost = getConfig("ssdb.host.src");
        int srcPort = getConfigInt("ssdb.port.src");
        String targetHost = getConfig("ssdb.host.target");
        int targetPort = getConfigInt("ssdb.port.target");
        int workThread = getConfigInt("s2s.thread");
        this.srcClient = new SsdbClient(srcHost,srcPort,5,workThread*2);
        this.targetClient = new SsdbClient(targetHost,targetPort,5,workThread*2);
    }

    private int getConfigInt(String key){
        String value = getConfig(key);
        return Integer.valueOf(value);
    }

    private String getConfig(String key){
        return config.get(key)+"";
    }

    private void readConfig() {
        config = new Properties();
        InputStream inputStream = Ssdb2Ssdb.class.getClassLoader().getResourceAsStream("s2s.properties");
        if (inputStream == null) {
            try {
                inputStream = new FileInputStream(FileSystems.getDefault().getPath("./s2s.properties").toFile());
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
}