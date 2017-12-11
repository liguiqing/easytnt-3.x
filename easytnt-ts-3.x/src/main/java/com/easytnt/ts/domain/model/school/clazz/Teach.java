/*
 * Copyright (c) 2016,2017, easytnt All Rights Reserved. 深圳市易考试乐学测评有限公司 版权所有.
 */

package com.easytnt.ts.domain.model.school.clazz;

import com.easytnt.commons.AssertionConcerns;
import com.easytnt.commons.domain.ValueObject;
import com.easytnt.ts.domain.model.school.Grade;
import com.easytnt.ts.domain.model.school.GradeCourseable;
import com.easytnt.ts.domain.model.school.GradeCourseableFactory;
import com.easytnt.ts.domain.model.school.common.Period;
import com.easytnt.ts.domain.model.school.position.Teacher;
import com.google.common.base.Objects;

import java.util.Date;

/**
 * 老师班级教学情况
 *
 *
 * @author Liguiqing
 * @since V3.0
 */

public class Teach extends ValueObject{

    private Teacher teacher;

    private ClazzId clazzId;

    private Period period;

    public Teach(Teacher teacher, ClazzId clazzId, Grade grade, Date starts, Date ends) {
        AssertionConcerns.assertArgumentNotNull(teacher,"请提供教师");
        AssertionConcerns.assertArgumentNotNull(clazzId,"请提供班级");

        GradeCourseable gradeCourseable = GradeCourseableFactory.lookup(teacher.schoolId());
        boolean canBeTeached = grade.canBeTeachOrStudyOfCourse(gradeCourseable, teacher.course());
        AssertionConcerns.assertArgumentTrue(canBeTeached,grade.name()+"不能教授"+teacher.course().name());
        this.teacher = teacher;
        this.clazzId = clazzId;
        this.period = new Period(starts, ends);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Teach teach = (Teach) o;

        return Objects.equal(teacher, teach.teacher) &&
                Objects.equal(clazzId, teach.clazzId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(teacher, clazzId);
    }

    public Teacher teacher() {
        return teacher;
    }

    public ClazzId clazzId() {
        return clazzId;
    }

    public Period period() {
        return period;
    }
}