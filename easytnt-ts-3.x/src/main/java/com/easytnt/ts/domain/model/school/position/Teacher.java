/*
 * Copyright (c) 2016,2017, easytnt All Rights Reserved. 深圳市易考试乐学测评有限公司 版权所有.
 */

package com.easytnt.ts.domain.model.school.position;

import com.easytnt.commons.util.DateUtilWrapper;
import com.easytnt.ts.domain.model.school.Course;
import com.easytnt.ts.domain.model.school.Grade;
import com.easytnt.ts.domain.model.school.clazz.ClazzId;
import com.easytnt.ts.domain.model.school.SchoolId;
import com.easytnt.ts.domain.model.school.staff.Period;

import java.util.Date;

/**
 * 教师，负责某课程教学
 *
 * @author Liguiqing
 * @since V3.0
 */

public class Teacher extends Position {

    private Course course;

    public Teacher(SchoolId schoolId, String name, String identity, Period period, Course course) {
        super(schoolId,identity,name, period);
        this.course = course;
    }

    public Course course() {
        return course;
    }

    public boolean isTeaching(){
        Date today = DateUtilWrapper.today();
        return this.period().isBetween(today);
    }

    public  <T extends Position> T transfer(PositionTransfer transfer){
        return transfer.translate(this);
    }


    @Override
    public String positionName() {
        return "教师";
    }
}