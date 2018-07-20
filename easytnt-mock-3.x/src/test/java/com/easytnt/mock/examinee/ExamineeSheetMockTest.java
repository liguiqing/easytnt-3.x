package com.easytnt.mock.examinee;

import com.easytnt.mock.AbstracMockTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.stream.Stream;

import static org.junit.Assert.*;

/**
 * Copyright (c) 2016,$today.year, 深圳市易考试乐学测评有限公司
 **/
public class ExamineeSheetMockTest extends AbstracMockTest {

    @Autowired
    private ExamineeSheetMock examineeSheet;

    @Test
    public void getMockValue() {
        assertNotNull(examineeSheet);
        String field = "sheet_id, answer_sheet_id, exam_id, subject_id, batch_id, sheet_key, catagory, exam_number, seq, crypt_code, scoredable, markable, score, kg_score, zg_score, is_del";
        Stream.of(field.split(",")).map(String::trim).forEach(s->{
            Object[] vs = examineeSheet.getMockValue(s);
            assertEquals(examineeSheet.realSize(),vs.length);
        });
    }

    @Test
    public void ids() {
        assertNotNull(examineeSheet);
        String[] ids = examineeSheet.ids();
        int size = examineeSheet.realSize();
        assertEquals(size,ids.length);
        Stream.of(ids).forEach(id->{
            assertTrue(id.startsWith("ESH"));
        });
    }
}