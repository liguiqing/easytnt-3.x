package com.easytnt.commons.util;

import static com.easytnt.commons.util.StringUtilWrapper.Word;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Copyright (c) 2016,$today.year, 深圳市易考试乐学测评有限公司
 **/
public class StringUtilWrapperTest {

    @Test
    public void ramdonOf() {
        Word[] words = new Word[]{Word.A};
        String sw = StringUtilWrapper.ramdonOf(words);
        assertEquals("a",sw);
    }

    @Test
    public void ramdonOf1() {
        for(int i=0;i<100;i++){
            String sw = StringUtilWrapper.ramdonOf('a','b');
            assertTrue("a".equals(sw)||"b".equals(sw));

            sw = StringUtilWrapper.ramdonOf('a','b','c');
            assertTrue("a".equals(sw)||"b".equals(sw)||"c".equals(sw));
        }

    }
}