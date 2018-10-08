package com.easytnt.mock.examinee;

import com.easytnt.commons.util.ArraysUtilWraper;
import com.easytnt.mock.AbstractMockTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.stream.Stream;

import static org.junit.Assert.*;

/**
 * Copyright (c) 2016,$today.year, 深圳市易考试乐学测评有限公司
 **/
public class ExamineeItemMockTest extends AbstractMockTest {

    @Autowired
    ExamineeItemMock examineeItem;

    @Test
    public void getMockValue() {
        String fields = "examinee_item_id, exam_id, subject_id, mark_item_id, batch_id, crypt_code, sheet_key, purpose, is_del";
        Stream.of(fields.split(",")).map(String::trim).forEach(s->{
            Object[] vs = examineeItem.getMockValue(s);
            assertEquals(examineeItem.size(),vs.length);
            if("crypt_code".equals(s)){
                assertTrue(ArraysUtilWraper.allElementsNotNull(vs));
            }
        });
    }
}