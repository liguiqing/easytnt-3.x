package com.easytnt.mock.mark.team;

import com.easytnt.mock.AbstractMockTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Copyright (c) 2016,$today.year, 深圳市易考试乐学测评有限公司
 **/
public class MarkerMockTest extends AbstractMockTest {

    @Autowired
    private MarkerMock marker;

    @Test
    public void getMockValue() {
        assertNotNull(marker);
        String fields = "marker_id, exam_id, subject_id, mark_item_id, person_id, org_id, name, role, planned, finished, status, online, is_del";
        Stream.of(fields.split(",")).map(String::trim).forEach(s->{
            Object[] vs = marker.getMockValue(s);
            assertEquals(marker.size(),vs.length);
        });

    }

}