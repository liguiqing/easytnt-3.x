package com.easytnt.statis.domain.task;

import com.easytnt.share.domain.id.mark.MarkItemId;
import com.easytnt.statis.domain.mark.*;
import com.google.common.collect.Lists;
import org.junit.Test;
import org.mockito.stubbing.Answer;
import org.mockito.stubbing.OngoingStubbing;

import java.util.*;

import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.powermock.api.mockito.PowerMockito.*;

/**
 * @author Liguiqing
 * @since V3.0
 */

public class StatisTaskTest {

    @Test
    public void testStart() throws Exception{
        ItemDataSet dataSet = mock(ItemDataSet.class);
        MarkScore score = mock(MarkScore.class);
        Collection<MarkScore> scores = mock(Collection.class);
        Iterator<MarkScore> it = mock(Iterator.class);
        when(dataSet.next()).thenReturn(scores);
        when(scores.iterator()).thenReturn(it).thenReturn(it).thenReturn(null);

        when(it.next()).thenReturn(score).thenReturn(score);
        ItemStatis itemStatis = mock(ItemStatis.class);

        when(itemStatis.supports(any())).thenReturn(true);
        when(itemStatis.getAndIncrement(any())).thenReturn(null);
        List<ItemStatis> itemStatises = Lists.newArrayList();
        itemStatises.add(itemStatis);
        StatisIndex mockIndex = mock(StatisIndex.class);
        doNothing().when(mockIndex).statis(itemStatis);
        StatisTask task = new StatisTask.Builder(new MarkItemId("MarkerItemId9527"))
                .useDataSet(dataSet).statisFor(itemStatises).withIndex(mockIndex).build();

        Random random = new Random(10);
        int length = 100;

        Thread[] threads = new Thread[length];
        for(int i=0;i<length;i++){
            threads[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    try{
                        long k = Math.abs(random.nextInt(500));
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
        while(task.isFinished()){
            break;
        }
        List<ItemStatis> statis = task.getStatises();
        assertNotNull(statis);
    }

}