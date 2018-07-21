package com.easytnt.mock.mark.item;

import com.easytnt.mock.AbstractMockTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Copyright (c) 2016,$today.year, 深圳市易考试乐学测评有限公司
 **/
public class SheetSlicesTest extends AbstractMockTest {
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