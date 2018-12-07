package com.easytnt.ashman;

import com.hyd.ssdb.SsdbClient;
import com.udpwork.ssdb.SSDB;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileSystems;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

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
        //testDel();
        //test();

        logger.debug("start move");
        Ssdb2Ssdb s2s = new Ssdb2Ssdb();
        try {
            s2s.readConfig();
            s2s.initClient();
            s2s.start();
        }finally {
            s2s.srcClient.close();
            s2s.targetClient.close();
        }
    }

    private static void testDel()throws Exception {
        SSDB ssdb2 = new SSDB("192.168.1.251", 8888);
        String url = "jdbc:mysql://192.168.1.251:3306/easytntv2_ty?useUnicode=true&amp;characterEncoding=utf-8&amp;autoReconnect=true&amp;rewriteBatchedStatements=true";
        Md5Removeable md5s = new Md5Removeable("com.mysql.jdbc.Driver", url, "root", "newa_newc", 5000);
        Deque<String> md5c = md5s.next(false);

        ssdb2.multi_del(md5c.toArray(new String[]{}));
    }

    private static void test()throws Exception{
        //SsdbClient client1 = new SsdbClient("192.168.1.230", 8888);
        //SsdbClient client2 = new SsdbClient("192.168.1.251", 8888);
        SSDB ssdb1 =  new SSDB("192.168.1.230", 8888);
        SSDB ssdb2 =  new SSDB("192.168.1.251", 8888);

        String url = "jdbc:mysql://192.168.1.251:3306/easytntv2_ty?useUnicode=true&amp;characterEncoding=utf-8&amp;autoReconnect=true&amp;rewriteBatchedStatements=true";
        Md5Removeable md5s = new Md5Removeable("com.mysql.jdbc.Driver",url,"root","newa_newc",5000);

        Deque<String> md5c = md5s.next(false);

        while(!md5c.isEmpty()){
            String key = md5c.pop();
            try {
                byte[] bytes = ssdb1.get(key);
                if(bytes != null && bytes.length >0) {
                    logger.debug("Copy {}",key);
                    ssdb2.set(key, key.getBytes());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        ssdb1.close();
        ssdb2.close();
        //client1.close();
        //client2.close();
    }

    private void start() {
        int size = getConfigInt("s2s.batch");
        Md5Removeable md5s = new Md5Removeable(getConfig("jdbc.driver"),
                getConfig("jdbc.url"),getConfig("jdbc.username"),getConfig("jdbc.password"),size);
        int threadable = getConfigInt("s2s.threadable");
        if(threadable >0) {
            useThreads(md5s);
        }else{
            noThreads(md5s);
        }

        logger.debug("Remove completed");

    }

    private void noThreads(Md5Removeable md5s){
        boolean finished = isFinished();
        int copied = getConfigInt("s2s.copied");
        while(true){
            Deque<String> md5List = md5s.next(finished);
            if(md5List.isEmpty())
                break;

            if(copied >0){
                while(!md5List.isEmpty()){
                    copy(md5List.pop());
                }
            }else{

                delOnly(new ArrayList<>(md5List),1);
            }
        }
    }

    private void delOnly(List<String> keys,int trys){
        try{
            logger.debug("Delete md5s --> {}",keys.size());
            this.srcClient.del(keys);
        }catch (Exception e){
            initClient();
            delOnly(keys,trys++);
        }
    }

    private boolean isFinished(){
        boolean finisend = true;
        int f = getConfigInt("s2s.finished");
        if(f == 0 )
            finisend = false;
        return finisend;
    }


    private void useThreads(Md5Removeable md5s){
        int workThread = getConfigInt("s2s.thread");
        ExecutorService service = Executors.newFixedThreadPool(workThread);
        boolean finished = isFinished();
        int copied = getConfigInt("s2s.copied");
        while(true){
            Deque<String> md5List = md5s.next(finished);
            if(md5List.isEmpty())
                break;

            if(copied >0){
                while(!md5List.isEmpty()){
                    Future future = service.submit(()-> copy( md5List.pop()));
                    future.isDone();
                }
            }else{
                delOnly(new ArrayList<>(md5List),1);
            }
        }
        service.shutdown();
    }

    private void copy(String md5){
        copy(md5,1);
    }

    private void copy(String md5,int trys){
        try {
            if (trys == 5)
                return;

            if(this.targetClient.exists(md5)){
                logger.debug("{} exists ,need'nt copy ", md5);
                return;
            }

            if(!this.srcClient.exists(md5)) {
                logger.debug("Needn't copy, {} not exists ", md5);
                return;
            }


            logger.debug("Copy {}", md5);
            byte[] imgBytes = this.srcClient.getBytes(md5);
            if (imgBytes != null && imgBytes.length > 0)
                this.targetClient.set(md5, imgBytes);

            int del = getConfigInt("s2s.src.del");
            if (del > 0) {
                logger.debug("Delete {}", md5);
                this.srcClient.del(md5);
            }
        }catch (Exception e){
            initClient();
            copy(md5,trys++);
        }
    }

    private void initClient(){
        String srcHost = getConfig("ssdb.host.src");
        int srcPort = getConfigInt("ssdb.port.src");
        String targetHost = getConfig("ssdb.host.target");
        int targetPort = getConfigInt("ssdb.port.target");

        if(this.srcClient != null)
            this.srcClient.close();
        if(this.targetClient != null)
            this.targetClient.close();

        this.srcClient = new SsdbClient(srcHost,srcPort,5);
        this.targetClient = new SsdbClient(targetHost,targetPort,5);
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