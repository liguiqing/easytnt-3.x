/*
 * Copyright (c) 2016,2017, easytnt All Rights Reserved. 深圳市易考试乐学测评有限公司 版权所有.
 */

package com.easytnt.ts.domain.model.school.term;

import com.easytnt.commons.util.DateUtilWrapper;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * TimeSpan test
 *
 * @author Liguiqing
 * @since V3.0
 */

public class TermBetweenTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testConstructorTrue()throws Exception{
        new TimeSpan(DateUtilWrapper.today(),DateUtilWrapper.tomorrow());
    }

    @Test
    public void testConstructorFalse()throws Exception{
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("期结束时间不能在开学时间之前");
        new TimeSpan(DateUtilWrapper.today(),DateUtilWrapper.today());
    }
}