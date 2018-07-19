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
public class SheetSlicesTest {
    @Autowired
    private SheetSlicesMock sheetSlices;

    @Test
    public void getMockValue() {
        assertNotNull(sheetSlices);
        String field = "sheet_slices_id, answer_sheet_id, page, sheet, x, y, w, h, roate, purpose, repeatable, is_del";
        Stream.of(field.split(",")).map(String::trim).forEach(s->{
            Object[] vs = sheetSlices.getMockValue(s);
            assertEquals(sheetSlices.size(),vs.length);
        });
    }
}