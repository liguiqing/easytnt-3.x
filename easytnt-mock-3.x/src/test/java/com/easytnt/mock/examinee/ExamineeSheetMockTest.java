package com.easytnt.mock.examinee;

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
public class ExamineeSheetMockTest {

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