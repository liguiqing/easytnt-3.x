package com.easytnt.mock.mark.item;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.stream.Stream;

import static org.junit.Assert.*;

/**
 * Copyright (c) 2016,$today.year, 深圳市易考试乐学测评有限公司
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext-mock-test-ds.xml","classpath:META-INF/spring/applicationContext-mock-app.xml"})
public class MarkItemMockTest {

    @Autowired
    private MarkItemMock markItem;

    @Test
    public void getMockValue() {
        String fields = "mark_item_id, answer_sheet_id, exam_id, subject_id, name, seq, score, purpose, option, group, required_times, status, is_del";
        Stream.of(fields.split(",")).map(String::trim).forEach(s->{
            Object[] vs = markItem.getMockValue(s);
            assertEquals(markItem.size(),vs.length);
        });
    }
}