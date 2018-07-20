package com.easytnt.mock.examinee;

import com.easytnt.mock.AbstracMockTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Copyright (c) 2016,$today.year, 深圳市易考试乐学测评有限公司
 **/
public class ExamineeSheetPageMockTest extends AbstracMockTest {

    @Autowired
    private ExamineeSheetPageMock examineeSheetPage;

    @Test
    public void getMockValue() {
        assertNotNull(examineeSheetPage);
        String field = "sheet_id, page, sheet, img_url, roate, is_del";
        Stream.of(field.split(",")).map(String::trim).forEach(s->{
            Object[] vs = examineeSheetPage.getMockValue(s);
            assertEquals(examineeSheetPage.realSize(),vs.length);
        });
    }
}