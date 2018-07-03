package com.easytnt.statis.domain.mark.index;

import com.easytnt.statis.domain.mark.ItemStatis;
import org.junit.Test;

import static org.junit.Assert.*;

import static org.mockito.Mockito.*;
/**
 * Copyright (c) 2016,$today.year, 深圳市易考试乐学测评有限公司
 **/
public class AvgSpeedStatisTest {

    @Test
    public void getValue() {
        AvgSpeedStatis statis = new AvgSpeedStatis();
        ItemStatis target = mock(ItemStatis.class);
        when(target.getValids()).thenReturn(100);
        when(target.getTotalSpend()).thenReturn(1000d);
        statis.statis(target);
        assertEquals(10d,statis.getValue().doubleValue(),0);
    }
}