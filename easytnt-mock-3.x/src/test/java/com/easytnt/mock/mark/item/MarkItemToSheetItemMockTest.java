package com.easytnt.mock.mark.item;

import com.easytnt.mock.AbstractMockTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.stream.Stream;

import static org.junit.Assert.*;

/**
 * Copyright (c) 2016,$today.year, 深圳市易考试乐学测评有限公司
 **/
public class MarkItemToSheetItemMockTest extends AbstractMockTest {

    @Autowired
    private MarkItemToSheetItemMock markItemToSheetItem;

    @Test
    public void getMockValue() {
        assertNotNull(markItemToSheetItem);
        String fields = "mark_item_id, answer_sheet_item_id, mark_item_score_id, is_del";
        Stream.of(fields.split(",")).map(String::trim).forEach(s->{
            Object[] vs = markItemToSheetItem.getMockValue(s);
            assertEquals(markItemToSheetItem.size(),vs.length);
        });
    }
}