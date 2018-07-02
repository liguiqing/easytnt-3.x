package com.easytnt.statis.domain.task;

import org.junit.Test;

import java.util.Random;

/**
 * @author Liguiqing
 * @since V3.0
 */

public class StatisTaskTest {

    @Test
    public void testStart() throws Exception{
        StatisTask task = new StatisTask(null,null,null);
        Math.random();
        Random random = new Random(10);
        int lenth = 100;

        Thread[] threads = new Thread[lenth];
        for(int i=0;i<lenth;i++){
            threads[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    try{
                        long k = Math.abs(random.nextInt(500));
                        System.out.println(k);
                        Thread.sleep(k);
                    }catch (Exception e){

                    }
                    task.start();
                }
            });
        }

        for(int i=0;i<lenth;i++){
            threads[i].start();
        }

        for(int i=0;i<lenth;i++){
            threads[i].join();
        }

    }
}