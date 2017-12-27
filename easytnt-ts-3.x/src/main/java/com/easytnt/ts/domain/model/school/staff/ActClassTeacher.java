/*
 * Copyright (c) 2016,2017, easytnt All Rights Reserved. 深圳市易考试乐学测评有限公司 版权所有.
 */

package com.easytnt.ts.domain.model.school.staff;

import com.easytnt.ts.domain.model.school.Grade;
import com.easytnt.ts.domain.model.school.clazz.Clazz;
import com.easytnt.ts.domain.model.school.common.Period;

/**
 * @author Liguiqing
 * @since V3.0
 */

public class ActClassTeacher implements  Act {

    private Clazz clazz;

    private Teacher teacher;

    private Grade grade;

    public ActClassTeacher(Clazz clazz, Teacher teacher, Grade grade) {
        this.clazz = clazz;
        this.teacher = teacher;
        this.grade = grade;
    }

    @Override
    public Position actTo(Staff staff, Period period) {
        return  new ClazzTeacher(staff.schoolId() ,this.clazz, staff.staffId().id(),
                staff.name(), period,this.teacher,this.grade);
    }
}