package com.easytnt.statis.domain.mark.index;

import com.easytnt.commons.util.DateUtilWrapper;
import com.easytnt.statis.domain.mark.ItemStatis;
import com.easytnt.statis.domain.mark.MarkScore;
import com.easytnt.statis.domain.mark.Score;
import com.easytnt.statis.domain.mark.Times1Data;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Copyright (c) 2016,$today.year, 深圳市易考试乐学测评有限公司
 **/
public class SdScoreStatisTest {

    @Test
    public void getValue() {
        Times1Data times1Data = new Times1Data();
        SdScoreStatis statis = new SdScoreStatis();
        ItemStatis target = mock(ItemStatis.class);
        when(target.getValids()).thenReturn(times1Data.getTotal());


        List<Score> scoreList = times1Data.toScores();
        when(target.getScores()).thenReturn(scoreList);

        double sd = times1Data.getSd();
        statis.statis(target);
        assertEquals(sd,statis.getValue().doubleValue(),0);
    }
}