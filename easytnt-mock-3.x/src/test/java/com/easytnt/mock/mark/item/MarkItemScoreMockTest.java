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
public class MarkItemScoreMockTest {

    @Autowired
    private MarkItemScoreMock markItemScore;

    @Test
    public void getMockValue() {
        assertNotNull(markItemScore);
        String fields = "mark_item_score_id, mark_item_id, parent_score_id, name, seq, score, score_limite, score_linear, error, scope, person_id, is_del";
        Stream.of(fields.split(",")).map(String::trim).forEach(s->{
            Object[] vs = markItemScore.getMockValue(s);
            assertEquals(markItemScore.size(),vs.length);
        });
    }
}