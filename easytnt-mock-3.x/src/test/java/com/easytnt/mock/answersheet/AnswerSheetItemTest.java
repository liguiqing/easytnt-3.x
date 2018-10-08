package com.easytnt.mock.answersheet;

import com.easytnt.commons.spring.SpringContextUtil;
import com.easytnt.mock.AbstractMockTest;
import org.junit.Test;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Copyright (c) 2016,$today.year, 深圳市易考试乐学测评有限公司
 **/

public class AnswerSheetItemTest  extends AbstractMockTest {

    @Test
    public void getValue() {
        AnswerSheetItem asi  = SpringContextUtil.getBean(AnswerSheetItem.class);
        String s = "answer_sheet_item_id, parent_item_id, exam_id, subject_id, answer_sheet_id, target_subject_id, target_subject_name, origin_item_id, name, catagory1, catagory2, score, required, num, content, st_type, readonly, rule, last_update_time, last_operator_id, last_operator_name, is_del";
        Arrays.stream(s.split(",")).map(String::trim).map(asi::getMockValue).forEach(a->{
            assertEquals(asi.getItemsCount(),a.length);
        });
    }

    @Test
    public void getItems() {
    }

    @Test
    public void getItemsSubjects() {
        AnswerSheetItem asi  = SpringContextUtil.getBean(AnswerSheetItem.class);
        String[] ids = asi.getItemsSubjects();

        assertNotNull(ids);
        assertEquals(20+21+83,ids.length);
        Stream.of(ids).forEach(id->assertNotNull(id));
    }

    @Test
    public void ids() {
        AnswerSheetItem asi  = SpringContextUtil.getBean(AnswerSheetItem.class);
        String[] ids = asi.ids();
        assertNotNull(ids);
        assertEquals(20+21+83,ids.length);
        Stream.of(ids).forEach(id->assertNotNull(id));
    }

    @Test
    public void getSheetItemParentIds(){
        AnswerSheetItem asi  = SpringContextUtil.getBean(AnswerSheetItem.class);
        String[] pids = asi.getSheetItemParentIds();
        assertNotNull(pids);
    }
}