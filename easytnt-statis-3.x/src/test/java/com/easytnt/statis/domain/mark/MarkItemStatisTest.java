package com.easytnt.statis.domain.mark;

import com.easytnt.share.domain.id.mark.MarkItemId;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Copyright (c) 2016,$today.year, 深圳市易考试乐学测评有限公司
 **/
public class MarkItemStatisTest {

    @Test
    public void getMarkItemId() {
        String id = "MarkItemId9527";
        String itemName = "item1";
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
}