/*
 * Copyright (c) 2016,2017, easytnt All Rights Reserved. 深圳市易考试乐学测评有限公司 版权所有.
 */

package com.easytnt.ts.domain.model.school.position;

import com.easytnt.commons.util.DateUtilWrapper;
import com.easytnt.ts.domain.model.school.Course;
import com.easytnt.ts.domain.model.school.SchoolId;
import com.easytnt.ts.domain.model.school.clazz.Teach;
import com.easytnt.ts.domain.model.school.common.Period;
import com.google.common.base.Objects;
import com.google.common.collect.Sets;

import java.util.Date;
import java.util.Set;

/**
 * 教师，负责某课程教学
 *
 * @author Liguiqing
 * @since V3.0
 */

public class Teacher extends Position {

    private Course course;

    private Set<Teach> teachs;

    public Teacher(SchoolId schoolId, String name, String identity, Period period, Course course) {
        super(schoolId,identity,name, period);
        this.course = course;
        this.teachs = Sets.newHashSet();
    }

    public Course course() {
        return course;
    }

    public boolean isTeaching(){
        Date today = DateUtilWrapper.today();
        return this.period().isBetween(today);
    }

    @Override
    public Position renew(Period newPerid) {
        return new Teacher(this.schoolId(),this.name(),this.identity(),newPerid,this.course);
    }

    public  <T extends Position> T transfer(PositionTransfer transfer){
        return transfer.translate(this);
    }


    @Override
    public String positionName() {
        return "教师";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Teacher teacher = (Teacher) o;
        return Objects.equal(course, teacher.course);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(super.hashCode(), course);
    }
}