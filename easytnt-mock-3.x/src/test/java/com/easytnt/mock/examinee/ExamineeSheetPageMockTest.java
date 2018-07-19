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
public class ExamineeSheetPageMockTest {

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