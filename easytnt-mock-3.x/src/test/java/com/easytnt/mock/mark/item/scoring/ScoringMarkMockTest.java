package com.easytnt.mock.mark.item.scoring;

import com.easytnt.commons.util.ArraysUtilWraper;
import com.easytnt.mock.AbstractMockTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.stream.Stream;

import static org.junit.Assert.*;

/**
 * Copyright (c) 2016,$today.year, 深圳市易考试乐学测评有限公司
 **/
public class ScoringMarkMockTest extends AbstractMockTest {

    @Autowired
    private ScoringMarkMock ScoringMark;

    @Test
    public void getMockValue() {
        String fields = "mark_id, exam_id, subject_id, batch_id, mark_item_id, examinee_item_id, school_id, clazz_id, purpose, point, room, crypt_code, group_no, fetch_seq, required, times, arbiter, fetchsign, unabled, formal_marker_ids, score, is_del";
        Stream.of(fields.split(",")).map(String::trim).forEach(s->{
            Object[] vs = ScoringMark.getMockValue(s);
            assertEquals(ScoringMark.size(),vs.length);
        });
    }
}