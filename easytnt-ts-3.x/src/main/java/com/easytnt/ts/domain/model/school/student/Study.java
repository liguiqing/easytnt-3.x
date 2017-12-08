/*
 * Copyright (c) 2016,2017, easytnt All Rights Reserved. 深圳市易考试乐学测评有限公司 版权所有.
 */

package com.easytnt.ts.domain.model.school.student;

import com.easytnt.commons.AssertionConcerns;
import com.easytnt.commons.domain.ValueObject;
import com.easytnt.ts.domain.model.school.Course;
import com.easytnt.ts.domain.model.school.Grade;
import com.easytnt.ts.domain.model.school.SchoolId;
import com.easytnt.ts.domain.model.school.clazz.Clazz;
import com.easytnt.ts.domain.model.school.clazz.ClazzId;
import com.easytnt.ts.domain.model.school.common.Period;
import com.easytnt.ts.domain.model.school.position.Teacher;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

import java.util.Date;

/**
 * 学生课程学习情况：学生什么时间在哪个班级学习什么课程
 *
 * @author Liguiqing
 * @since V3.0
 */

public class Study extends ValueObject {

    private StudentId studentId;

    private SchoolId schoolId;

    private ClazzId clazzId;

    private Grade grade;

    private Period period;

    private Teacher teacher;

    public Study(StudentId studentId, SchoolId schoolId, Clazz clazz, Grade grade,
                 Course course, Teacher teacher,Date starts, Date ends) {
        AssertionConcerns.assertArgumentNotNull(studentId,"请提供学生");
        AssertionConcerns.assertArgumentNotNull(schoolId,"请提供学习学校");
        AssertionConcerns.assertArgumentNotNull(clazz,"请提供学习班级");
        AssertionConcerns.assertArgumentNotNull(teacher,"请提供课程老师");
        AssertionConcerns.assertArgumentNotNull(course,"请提供学习课程");
        AssertionConcerns.assertArgumentTrue(clazz.canBeStudied(),"不能在非教学班级学习");
        AssertionConcerns.assertArgumentTrue(teacher.isTeaching(),"课程老师已经离职");
        AssertionConcerns.assertArgumentTrue(teacher.canBeTeachOf(course),"课程老师教授课程与学习");
        this.studentId = studentId;
        this.schoolId = schoolId;
        this.clazzId = clazz.clazzId();
        this.grade = grade;
        this.period = new Period(starts, ends);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Study study = (Study) o;
        return Objects.equal(studentId, study.studentId) &&
                Objects.equal(clazzId, study.clazzId) &&
                Objects.equal(grade, study.grade) &&
                Objects.equal(teacher, study.teacher);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(studentId, clazzId, grade, teacher);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("studentId", studentId)
                .add("clazzId", clazzId)
                .add("grade", grade)
                .add("teacher", teacher)
                .toString();
    }

    public StudentId studentId() {
        return studentId;
    }

    public SchoolId schoolId() {
        return schoolId;
    }

    public ClazzId clazzId() {
        return clazzId;
    }

    public Grade grade() {
        return grade;
    }

    public Period period() {
        return period;
    }

    public Teacher teacher() {
        return teacher;
    }
}