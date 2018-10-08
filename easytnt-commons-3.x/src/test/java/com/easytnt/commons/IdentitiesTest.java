/*
 * Copyright (c) 2016,2018, easytnt All Rights Reserved. 深圳市易考试乐学测评有限公司 版权所有.
 */

package com.easytnt.commons;

import com.easytnt.commons.domain.Identities;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * @author Liguiqing
 * @since V3.0
 */

public class IdentitiesTest {


    @Test
    public void testGenId()throws Exception{
        String id = Identities.genId("TST");
        assertTrue(id.length()>36);
        assertTrue(id.startsWith("TST"));
    }

    @Test
    public void testGenIdNone()throws Exception{
        String id = Identities.genIdNone("TST");
        assertTrue(id.length()<36);
        assertTrue(id.startsWith("TST"));
    }
}