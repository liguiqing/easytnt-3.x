package com.easytnt.mock.mark.team;

import com.easytnt.mock.AbstractMockTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.stream.Stream;

import static org.junit.Assert.*;

/**
 * Copyright (c) 2016,$today.year, 深圳市易考试乐学测评有限公司
 **/
public class MarkTeamMockTest  extends AbstractMockTest {

    @Autowired
    private MarkTeamMock markTeam;

    @Test
    public void getMockValue() {
        assertNotNull(markTeam);
        String fields = "team_id, parent_team_id, exam_id, subject_id, org_id, mark_item_id, name, planned, finished, level, is_del";
        Stream.of(fields.split(",")).map(String::trim).forEach(s->{
            Object[] vs = markTeam.getMockValue(s);
            assertEquals(markTeam.size(),vs.length);
        });
    }
}