package com.easytnt.mock.mark.item.kg;

import com.easytnt.mock.AbstractMockTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.stream.Stream;

import static org.junit.Assert.*;

/**
 * Copyright (c) 2016,$today.year, 深圳市易考试乐学测评有限公司
 **/
public class KgMarkMockTest  extends AbstractMockTest {

    @Autowired
    private KgMarkMock kgMark;

    @Test
    public void getMockValue() {
        assertNotNull(kgMark);
        //
        String fields = "mark_id, exam_id, subject_id, batch_id, mark_item_id, examinee_item_id, required, times, fetch_seq, arbiter, fetchsign, crypt_code, score, scores, optional, doubt, doubt_done, is_del";
        Stream.of(fields.split(",")).map(String::trim).forEach(s->{
            Object[] vs = kgMark.getMockValue(s);
            assertEquals(kgMark.size(),vs.length);
        });
    }
}