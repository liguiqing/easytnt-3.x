/*
 * Copyright (c) 2016,2017, easytnt All Rights Reserved. 深圳市易考试乐学测评有限公司 版权所有.
 */

package com.easytnt.ts.domain.model.school.position;

import com.easytnt.commons.AssertionConcerns;
import com.easytnt.ts.domain.model.school.*;
import com.easytnt.ts.domain.model.school.clazz.Clazz;
import com.easytnt.ts.domain.model.school.clazz.ClazzId;
import com.easytnt.ts.domain.model.school.common.Period;
import com.google.common.base.Objects;

/**
 * 班级学科教师
 *
 * @author Liguiqing
 * @since V3.0
 */

public class ClazzTeacher extends Position{

    private ClazzId clazzId;

    private Teacher teacher;


    public ClazzTeacher(SchoolId schoolId, Clazz clazz,String name, String identity,
                        Period period, Teacher teacher,Grade grade) {
        super(schoolId, name, identity, period);
        AssertionConcerns.assertArgumentNotNull(clazz,"请提供班级");
        AssertionConcerns.assertArgumentTrue(clazz.canBeStudyAndTeachIn(),"非教学班不能安排教学老师");
        AssertionConcerns.assertArgumentEquals(schoolId,teacher.schoolId(),"教师不在此校任职");
        GradeCourseable gradeCourseable = GradeCourseableFactory.lookup(schoolId);
        boolean canBeTeached = grade.canBeTeachOrStudyOfCourse(gradeCourseable, teacher.course());
        AssertionConcerns.assertArgumentTrue(canBeTeached,grade.name()+"不能教授"+teacher.course().name());

        this.clazzId = clazz.clazzId();
        this.teacher = teacher;
    }

    @Override
    public String positionName() {
        return this.teacher.positionName();
    }

    @Override
    public Position renew(Period newPerid) {
        ClazzTeacher newTeacher = new ClazzTeacher(this.schoolId(),this.name(),this.identity(),newPerid);
        newTeacher.clazzId = this.clazzId;
        newTeacher.teacher = this.teacher;
        return newTeacher;
    }

    @Override
    public Position rename(String newName) {
        ClazzTeacher newTeacher = new ClazzTeacher(this.schoolId(),newName,this.identity(),this.period());
        newTeacher.clazzId = this.clazzId;
        newTeacher.teacher = this.teacher;
        return newTeacher;
    }

    protected ClazzTeacher(SchoolId schoolId,String name, String identity, Period period){
        super(schoolId, name, identity, period);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ClazzTeacher that = (ClazzTeacher) o;
        return Objects.equal(clazzId, that.clazzId) &&
                Objects.equal(teacher, that.teacher);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(super.hashCode(), clazzId, teacher);
    }

    protected ClazzTeacher(){
        super();
    }

    public ClazzId clazzId() {
        return clazzId;
    }

    public Teacher teacher() {
        return teacher;
    }

}