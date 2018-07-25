package com.easytnt.mock.mark.item.scoring;

import com.easytnt.mock.AbstractMockTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Stream;

import static org.junit.Assert.*;

/**
 * Copyright (c) 2016,$today.year, 深圳市易考试乐学测评有限公司
 **/
public class ScoringMarkHandlerMockTest extends AbstractMockTest {

    @Autowired
    private ScoringMarkHandlerMock scoringMarkHandler;

    @Test
    public void getMockValue() {
        assertNotNull(scoringMarkHandler);
        String fields = "handler_id, mark_id, mark_item_id, marker_id, mark_type, fetch_times, fetch_time, valid, submit_time, submit_times, score, scores, unabled, unabled_catagory, marker_seq, is_del";
        Stream.of(fields.split(",")).map(String::trim).forEach(s->{
            Object[] vs = scoringMarkHandler.getMockValue(s);
            assertEquals(scoringMarkHandler.size(),vs.length);
            if(s.equals("unabled")){
                Object[] score = scoringMarkHandler.getMockValue("score");
                Object[] fetch_times = scoringMarkHandler.getMockValue("fetch_times");
                int length = score.length;
                for(int i=0;i<length;i++){
                    if(((Integer)vs[i]).compareTo(1) == 0){
                        assertNull(score[i]);
                        assertEquals(1, fetch_times[i]);
                    }
                }
            }
        });
    }
}