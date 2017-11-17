/*
 * Copyright (c) 2016,2017, easytnt All Rights Reserved. 深圳市易考试乐学测评有限公司 版权所有.
 */

package com.easytnt.ts.domain.model.school.student;

import com.easytnt.commons.domain.AbstractDomainEvent;
import com.easytnt.ts.domain.model.school.clazz.ClazzId;

import java.util.Date;

/**
 * @author Liguiqing
 * @since V3.0
 */

public class StudentClazzChanged extends AbstractDomainEvent {

    private ClazzId from ;

    private ClazzId to;

    private Student student;

    public StudentClazzChanged(ClazzId from,ClazzId to, Student student) {
        this.to = to;
        this.student = student;
    }

    public ClazzId from() {
        return from;
    }

    public ClazzId to() {
        return to;
    }

    public Student student() {
        return student;
    }
}