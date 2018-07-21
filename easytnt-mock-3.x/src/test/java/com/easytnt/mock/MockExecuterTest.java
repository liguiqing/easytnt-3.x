package com.easytnt.mock;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;

import static org.junit.Assert.assertNotNull;

/**
 * Copyright (c) 2016,$today.year, 深圳市易考试乐学测评有限公司
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext-mock-test-ds.xml","classpath:META-INF/spring/applicationContext-mock-app.xml"})
public class MockExecuterTest {

    @Autowired
    private JdbcTemplate jdbc;

    @Test
    public void exec() throws Exception{
        assertNotNull(jdbc);

        MockExecuter executer = new MockExecuter(jdbc);
        executer.exec();
    }

    @Test
    public void test(){
        String s = "mark_id, exam_id, subject_id, batch_id, mark_item_id, examinee_item_id, school_id, clazz_id, purpose, point, room, crypt_code, group_no, fetch_seq, required, times, arbiter, fetchsign, unabled, formal_marker_ids, score, is_del";
        System.out.println("switch(key){");
        Arrays.stream(s.split(",")).map(String::trim).map(a->"case \"" + a + "\": return new Object[]{};").forEach(System.out::println);
        System.out.println("default: return this.repeator.repeatOf(this.size(),null);");
        System.out.println("}");
//        Arrays.stream(repeatOfGroup(10,new String[]{"a","b","c"})).forEach(System.out::println);
//        System.out.println("---------------------------------------");
//        Arrays.stream(repeatOfGroup(9,new String[]{"a","b","c"})).forEach(System.out::println);
//        System.out.println("---------------------------------------");
//        Arrays.stream(repeatOfGroup(11,new String[]{"a","b","c"})).forEach(System.out::println);
//        System.out.println("---------------------------------------");
//        Arrays.stream(repeatOfGroup(12,new String[]{"a","b","c"})).forEach(System.out::println);
//        String[] ss = Stream.of(s.split(",")).map(s1 -> "?").toArray(String[]::new);
//        System.out.println(Arrays.toString(ss));

    }

    private String replaceTo(String s){
        return "?";
    }

    protected String[] repeatOfGroup(int size,String[] src){
        int group = src.length;//(int)(Math.ceil((size*1d)/(src.length*1d)));
        String[] values = new String[size];
        int j = 0;
        for(int i=0;i<size;i++){
            values[i] = src[j++];
            if(j==group)
                j=0;
        }
        return values;
    }
}