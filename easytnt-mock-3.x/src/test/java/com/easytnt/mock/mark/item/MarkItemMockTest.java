package com.easytnt.mock.mark.item;

import com.easytnt.mock.AbstractMockTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;

/**
 * Copyright (c) 2016,$today.year, 深圳市易考试乐学测评有限公司
 **/
public class MarkItemMockTest extends AbstractMockTest {

    @Autowired
    private MarkItemMock markItem;

    @Test
    public void getMockValue() {
        String fields = "mark_item_id, answer_sheet_id, exam_id, subject_id, name, seq, score, purpose, option, group, required_times, status, is_del";
        Stream.of(fields.split(",")).map(String::trim).forEach(s->{
            Object[] vs = markItem.getMockValue(s);
            assertEquals(markItem.size(),vs.length);
        });
    }
}