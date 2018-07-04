package com.easytnt.statis.domain.task;

import com.easytnt.share.domain.id.mark.MarkItemId;
import com.easytnt.statis.domain.mark.*;
import com.easytnt.statis.domain.mark.index.AvgScoreStatis;
import com.easytnt.statis.domain.mark.index.AvgSpeedStatis;
import com.easytnt.statis.domain.mark.index.FinishedNoErrorsStatis;
import com.easytnt.statis.domain.mark.index.SdScoreStatis;
import org.junit.Test;
import org.powermock.api.mockito.PowerMockito;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.powermock.api.mockito.PowerMockito.*;

/**
 * @author Liguiqing
 * @since V3.0
 */

public class StatisTaskTest {

    @Test
    public void testStart() throws Exception{
        ItemDataSet dataSet = mock(ItemDataSet.class);
        DataPool dataPool = new DataPool();
        int size = 10000;
        int start = 0;
        while(true){
            List<MarkScore> scores = dataPool.getMarkScore(start,size);
            if(scores == null)
                break;
            when(dataSet.next()).thenReturn(scores);
            start = size + start;
        }
        StatisIndex mockIndex = PowerMockito.mock(StatisIndex.class);

        ItemStatis itemStatis = PowerMockito.mock(ItemStatis.class);
        List<ItemStatis> itemStatises = PowerMockito.mock(List.class);
        StatisTask task = new StatisTask(dataPool.times1.markItemId,dataSet,itemStatises,mockIndex);
        Random random = new Random(10);
        int length = 100;

        Thread[] threads = new Thread[length];
        for(int i=0;i<length;i++){
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

        for(int i=0;i<length;i++){
            threads[i].start();
        }

        for(int i=0;i<length;i++){
            threads[i].join();
        }

    }

    private static class DataPool{
        private List<MarkScore> allScores;
        private Times1Data times1 = new Times1Data();

        public DataPool() {
            allScores = times1.toMarkScores();
        }

        public List<MarkScore> getMarkScore(int start, int size) {
            if(start > allScores.size())
                return null;

            List<MarkScore> mss = new ArrayList<>(size);
            int end = start * size;
            if (end > this.allScores.size()) {
                end = this.allScores.size();
            }
            for(int i=size;i<end;i++){
                mss.add(allScores.get(i));
            }
            return mss;
        }
    }
}