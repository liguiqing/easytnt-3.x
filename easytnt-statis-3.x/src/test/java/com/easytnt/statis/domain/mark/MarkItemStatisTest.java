package com.easytnt.statis.domain.mark;

import com.easytnt.commons.util.DateUtilWrapper;
import com.easytnt.share.domain.id.mark.MarkItemId;
import com.easytnt.statis.domain.mark.index.*;
import org.junit.Test;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Copyright (c) 2016,$today.year, 深圳市易考试乐学测评有限公司
 **/
public class MarkItemStatisTest {

    @Test
    public void getMarkItemId() {
        String id = "MKIMf56738c4642c435cb6f78896671ad785";
        String itemName = "31";
        MarkItemId markItemId = new MarkItemId(id);
        MarkItemStatis mistatis = new MarkItemStatis(markItemId,itemName,1,10);
        assertEquals(mistatis.getMarkItemId(),markItemId);

    }

    @Test
    public void getName() {
        String id = "MarkItemId9527";
        String itemName = "item1";
        MarkItemId markItemId = new MarkItemId(id);
        MarkItemStatis mistatis = new MarkItemStatis(markItemId,itemName,1,10);
        assertEquals(mistatis.getName(),itemName);
    }

    @Test
    public void supports(){
        String id = "MarkItemId9527";
        String itemName = "item1";
        MarkItemId markItemId = new MarkItemId(id);
        MarkItemStatis mistatis = new MarkItemStatis(markItemId,itemName,1,10);
        assertTrue(mistatis.supports(new MarkItemId("MarkItemId9527")));
        assertFalse(mistatis.supports(new MarkItemId("M1arkItemId9527")));
        assertFalse(mistatis.supports(new MarkItemId(" MarkItemId9527 ")));
        assertFalse(mistatis.supports(new MarkItemId()));
    }

    @Test
    public void getAndIncrement() {
    	 String id = "MKIMf56738c4642c435cb6f78896671ad785";
         String itemName = "31";
        MarkItemId markItemId = new MarkItemId(id);
        MarkItemStatis mistatis = new MarkItemStatis(markItemId,itemName,1,10);

        Date fetchTime  = DateUtilWrapper.now();
        Date submitTime  = DateUtilWrapper.addSecondTo(fetchTime,10);

        MarkScore score = new MarkScore.Builder()
                .score(10d)
                .curTimes(1)
                .finalScore(10d)
                .fetchTime(fetchTime)
                .submitTime(submitTime)
                .timesRequired(1)
                .totalTimes(1)
                .build();
        Score s = mistatis.getAndIncrement(score);
        assertEquals(10,s.getValue(),0);
        assertEquals(10, s.getTotalSpend(),0);
        assertEquals(10,mistatis.getAvgScore(),0);
    }

    @Test
    public void times1(){
        Times1Data times1 = new Times1Data();
        List<MarkScore> mss = times1.toMarkScores();
        MarkItemStatis mistatis = new MarkItemStatis(times1.markItemId,times1.itemName,times1.timesRequired,times1.fullScore);

        for(MarkScore ms:mss){
            mistatis.getAndIncrement(ms);
        }
        double[] scores = times1.getScores();
        List<Score> scoreList = mistatis.getScores();
        assertEquals(scores.length,scoreList.size());
        for(int i=0;i<scores.length;i++){
            int count = times1.getCountOf(scores[i]);
            Score score = scoreList.get(i);
            assertEquals(scores[i],score.getValue(),0);
            assertEquals(count,score.size(),0);
        }

        assertEquals(times1.getTotal(),mistatis.getValids());
        assertEquals(times1.getTotal(),mistatis.getTotal());
        assertEquals(0,mistatis.getErrors());
        assertTrue(mistatis.supports(times1.markItemId));
        assertFalse(mistatis.hasTotalRequired());
        assertTrue(mistatis.getAvgScore()==times1.totalScore/times1.getTotal());

        StatisIndex noErrors = new FinishedNoErrorsStatis()
                .append(new AvgScoreStatis()).append(new AvgSpeedStatis()).append(new SdScoreStatis());

        noErrors.statis(mistatis);

        List<StatisResult> results = mistatis.getStatisResults();
        StatisResult result1 = results.get(0);
        assertTrue(result1.getValue().intValue() == mistatis.getTotal());
        StatisResult result2 = results.get(1);
        assertTrue(result2.getValue().doubleValue() == times1.totalScore/times1.getTotal());
        StatisResult result3 = results.get(2);
        assertTrue(result3.getValue().doubleValue() == (times1.totalSpend*1d)/(times1.getTotal()));
        StatisResult result4 = results.get(3);
        assertTrue(result4.getValue().doubleValue() == times1.getSd());

    }
}