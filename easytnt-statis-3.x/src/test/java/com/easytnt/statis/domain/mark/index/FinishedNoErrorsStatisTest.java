package com.easytnt.statis.domain.mark.index;

import com.easytnt.statis.domain.mark.MarkItemStatis;
import com.easytnt.statis.domain.mark.MarkScore;
import com.easytnt.statis.domain.mark.StatisResult;
import com.easytnt.statis.domain.mark.Times1Data;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * @author Liguiqing
 * @since V3.0
 */

public class FinishedNoErrorsStatisTest {

    @Test
    public void statis()throws Exception{
        Times1Data times1 = new Times1Data();
        List<MarkScore> mss = times1.toMarkScores();
        MarkItemStatis itemStatis = new MarkItemStatis(times1.markItemId,times1.itemName,times1.timesRequired,times1.fullScore);
        for(MarkScore ms:mss){
            itemStatis.getAndIncrement(ms);
        }

        FinishedNoErrorsStatis statis = new FinishedNoErrorsStatis(null);

        statis.computer(itemStatis);

        List<StatisResult> resultList = itemStatis.getStatisResults();
        StatisResult aResult = resultList.get(0);
        assertEquals(statis.getName(),aResult.getName());
        assertEquals(times1.getTotal(),aResult.getValue().intValue(),0);
    }
}