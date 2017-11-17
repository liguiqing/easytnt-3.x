/*
 * Copyright (c) 2016,2017, easytnt All Rights Reserved. 深圳市易考试乐学测评有限公司 版权所有.
 */

package com.easytnt.ts.domain.model.school.clazz;

import com.easytnt.commons.domain.AbstractDomainEvent;

import java.util.Date;

/**
 * @author Liguiqing
 * @since V3.0
 */

public class TeacherJoinedToClazz extends AbstractDomainEvent {
    private ClazzId clazzId;

    private String teacherName;

    private String identity;

    public TeacherJoinedToClazz(ClazzId clazzId, String teacherName, String identity) {
        this.clazzId = clazzId;
        this.teacherName = teacherName;
        this.identity = identity;
    }

    public TeacherJoinedToClazz(int eventVersion, Date occurredOn, ClazzId clazzId, String teacherName, String identity) {
        super(eventVersion, occurredOn);
        this.clazzId = clazzId;
        this.teacherName = teacherName;
        this.identity = identity;
    }

    public ClazzId clazzId() {
        return clazzId;
    }

    public String teacherName() {
        return teacherName;
    }

    public String identity() {
        return identity;
    }
}