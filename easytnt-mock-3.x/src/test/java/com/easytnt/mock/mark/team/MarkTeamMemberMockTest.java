package com.easytnt.mock.mark.team;

import com.easytnt.mock.AbstractMockTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.stream.Stream;

import static org.junit.Assert.*;

/**
 * Copyright (c) 2016,$today.year, 深圳市易考试乐学测评有限公司
 **/
public class MarkTeamMemberMockTest extends AbstractMockTest {

    @Autowired
    private MarkTeamMemberMock markTeamMember;

    @Test
    public void getMockValue() {
        assertNotNull(markTeamMember);
        String fields = "team_id, mark_item_id, marker_id, role, is_del";
        Stream.of(fields.split(",")).map(String::trim).forEach(s->{
            Object[] vs = markTeamMember.getMockValue(s);
            assertEquals(markTeamMember.size(),vs.length);
        });
    }
}