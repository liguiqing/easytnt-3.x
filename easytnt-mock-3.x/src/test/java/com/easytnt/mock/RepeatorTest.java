package com.easytnt.mock;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Copyright (c) 2016,$today.year, 深圳市易考试乐学测评有限公司
 **/
public class RepeatorTest {

    @Test
    public void repeatOfGroupOfEachMaxLt(){
        Repeator repeator = new Repeator();
        String[] as = repeator.repeatOfGroupOfEachMaxLt(2, 1,3,"a","b");
        assertEquals(3,as.length);
        assertEquals("a",as[0]);
        assertEquals("b",as[1]);
        assertEquals("a",as[2]);

        as = repeator.repeatOfGroupOfEachMaxLt(2, 2,3,"a","b");
        assertEquals(3,as.length);
        assertEquals("a",as[0]);
        assertEquals("a",as[1]);
        assertEquals("b",as[2]);

        as = repeator.repeatOfGroupOfEachMaxLt(2, 2,5,"a","b");
        assertEquals(5,as.length);
        assertEquals("a",as[0]);
        assertEquals("a",as[1]);
        assertEquals("b",as[2]);
        assertEquals("b",as[3]);
        assertEquals("a",as[4]);
    }

    @Test
    public void repeatOfGroupOfEach(){
        Repeator repeator = new Repeator();
        String[] as = repeator.repeatOfGroupOfEach(1,2);
        assertNull(as);

        as = repeator.repeatOfGroupOfEach(1, 1,"a","b");
        assertNotNull(as);
        assertEquals(2,as.length);
        assertEquals("a",as[0]);
        assertEquals("b",as[1]);

        as = repeator.repeatOfGroupOfEach(2, 1,"a","b");
        assertNotNull(as);
        assertEquals(4,as.length);
        assertEquals("a",as[0]);
        assertEquals("b",as[1]);
        assertEquals("a",as[2]);
        assertEquals("b",as[3]);

        as = repeator.repeatOfGroupOfEach(1, 2,"a","b");
        assertNotNull(as);
        assertEquals(4,as.length);
        assertEquals("a",as[0]);
        assertEquals("a",as[1]);
        assertEquals("b",as[2]);
        assertEquals("b",as[3]);

        as = repeator.repeatOfGroupOfEach(2, 2,"a","b");
        assertNotNull(as);
        assertEquals(8,as.length);
        assertEquals("a",as[0]);
        assertEquals("a",as[1]);
        assertEquals("b",as[2]);
        assertEquals("b",as[3]);
        assertEquals("a",as[4]);
        assertEquals("a",as[5]);
        assertEquals("b",as[6]);
        assertEquals("b",as[7]);

        as = repeator.repeatOfGroupOfEach(3, 2,"a","b");
        assertNotNull(as);
        assertEquals(12,as.length);
        assertEquals("a",as[0]);
        assertEquals("a",as[1]);
        assertEquals("b",as[2]);
        assertEquals("b",as[3]);
        assertEquals("a",as[4]);
        assertEquals("a",as[5]);
        assertEquals("b",as[6]);
        assertEquals("b",as[7]);
        assertEquals("a",as[8]);
        assertEquals("a",as[9]);
        assertEquals("b",as[10]);
        assertEquals("b",as[11]);

        as = repeator.repeatOfGroupOfEach(2, 2,"a","b","c");
        assertNotNull(as);
        assertEquals(12,as.length);
        assertEquals("a",as[0]);
        assertEquals("a",as[1]);
        assertEquals("b",as[2]);
        assertEquals("b",as[3]);
        assertEquals("c",as[4]);
        assertEquals("c",as[5]);
        assertEquals("a",as[6]);
        assertEquals("a",as[7]);
        assertEquals("b",as[8]);
        assertEquals("b",as[9]);
        assertEquals("c",as[10]);
        assertEquals("c",as[11]);
    }

    @Test
    public void repeatOfGroup() {
        Repeator repeator = new Repeator();
        String[] as = repeator.repeatOfGroup(1);
        assertNull(as);

        as = repeator.repeatOfGroup(1, "a");
        assertNotNull(as);
        assertEquals(as.length,1);
        assertEquals("a",as[0]);

        as = repeator.repeatOfGroup(1, "a","b");
        assertNotNull(as);
        assertEquals(as.length,2);
        assertEquals("a",as[0]);
        assertEquals("b",as[1]);

        as = repeator.repeatOfGroup(2, "a","b");
        assertNotNull(as);
        assertEquals(as.length,4);
        assertEquals("a",as[0]);
        assertEquals("b",as[1]);
        assertEquals("a",as[2]);
        assertEquals("b",as[3]);

        as = repeator.repeatOfGroup(3, "a","b");
        assertNotNull(as);
        assertEquals(as.length,6);
        assertEquals("a",as[0]);
        assertEquals("b",as[1]);
        assertEquals("a",as[2]);
        assertEquals("b",as[3]);
        assertEquals("a",as[4]);
        assertEquals("b",as[5]);

        as = repeator.repeatOfGroup(2, "a","b","c");
        assertNotNull(as);
        assertEquals(as.length,6);
        assertEquals("a",as[0]);
        assertEquals("b",as[1]);
        assertEquals("c",as[2]);
        assertEquals("a",as[3]);
        assertEquals("b",as[4]);
        assertEquals("c",as[5]);
    }

    @Test
    public void repeatOf() {
        Repeator repeator = new Repeator();
        String[] as = repeator.repeatOf(0,"a");
        assertNull(as);
        as = repeator.repeatOf(-1,"a");
        assertNull(as);
        as = repeator.repeatOf(1,"a");
        assertTrue(as.length == 1);
        assertEquals("a",as[0]);

        as = repeator.repeatOf(2,"a");
        assertTrue(as.length == 2);
        assertEquals("a",as[0]);
        assertEquals("a",as[1]);

        as = repeator.repeatOf(10,"a");
        assertTrue(as.length == 10);
        assertEquals("a",as[0]);
        assertEquals("a",as[1]);
        assertEquals("a",as[1]);
        assertEquals("a",as[9]);

    }

    @Test
    public void repeatOfMixedRandom() {
        Repeator repeator = new Repeator();

        String[] as = repeator.repeatOfMixedRandom(10,"a",0.5,"b");
        assertTrue(as.length == 10);
        int i = 0,j=0;
        for(String s:as){
            if("a".equals(s))
                i++;
            if("b".equals(s))
                j++;
        }
        assertTrue(i<=5);
        assertTrue(j>=5);
    }

    @Test
    public void repeatOfGroupMixedRandom() {
        Repeator repeator = new Repeator();

        String[] as = repeator.repeatOfGroupMixedRandom(10,new String[]{"a"},0.5,"b","c");
        assertTrue(as.length == 20);
        if(true) {
            int i = 0, j = 0, k = 0;
            for (String s : as) {
                if ("a".equals(s))
                    i++;
                if ("b".equals(s))
                    j++;
                if ("c".equals(s))
                    k++;
            }
            assertTrue(i <= 10);
            assertTrue((j + k) >= 10);
        }

        as = repeator.repeatOfGroupMixedRandom(15,new String[]{"a"},0.3,"b","c");
        assertTrue(as.length == 30);
        if(true) {
            int i = 0, j = 0, k = 0;
            for (String s : as) {
                if ("a".equals(s))
                    i++;
                if ("b".equals(s))
                    j++;
                if ("c".equals(s))
                    k++;
            }
            assertTrue(i <= 10);
            assertTrue((j + k) >= 20);
        }
    }
}