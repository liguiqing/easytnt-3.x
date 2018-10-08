package com.easytnt.statis.domain.mark.index;

import com.easytnt.statis.domain.mark.MarkItemStatis;
import com.easytnt.statis.domain.mark.MarkScore;
import com.easytnt.statis.domain.mark.StatisResult;
import com.easytnt.statis.domain.mark.Times1Data;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Copyright (c) 2016,$today.year, 深圳市易考试乐学测评有限公司
 **/
public class SdScoreStatisTest {

    @Test
    public void statis() {
        Times1Data times1 = new Times1Data();
        List<MarkScore> mss = times1.toMarkScores();
        MarkItemStatis itemStatis = new MarkItemStatis(times1.markItemId,times1.itemName,times1.timesRequired,times1.fullScore);
        for(MarkScore ms:mss){
            itemStatis.getAndIncrement(ms);
        }

        SdScoreStatis statis = new SdScoreStatis();

        statis.computer(itemStatis);

        double sd = times1.getSd();
        List<StatisResult> resultList = itemStatis.getStatisResults();
        StatisResult aResult = resultList.get(0);
        assertEquals(statis.getName(),aResult.getName());
        assertEquals(sd,aResult.getValue().doubleValue(),0);
    }
}