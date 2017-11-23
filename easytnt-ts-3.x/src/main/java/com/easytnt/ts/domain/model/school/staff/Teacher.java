/*
 * Copyright (c) 2016,2017, easytnt All Rights Reserved. 深圳市易考试乐学测评有限公司 版权所有.
 */

package com.easytnt.ts.domain.model.school.staff;

import com.easytnt.commons.util.DateUtilWrapper;
import com.easytnt.ts.domain.model.school.Course;
import com.easytnt.ts.domain.model.school.Grade;
import com.easytnt.ts.domain.model.school.clazz.ClazzId;
import com.easytnt.ts.domain.model.school.common.Gender;
import com.easytnt.ts.domain.model.school.SchoolId;
import com.easytnt.ts.domain.model.school.common.Identity;
import com.easytnt.ts.domain.model.school.common.IdentityType;
import com.easytnt.ts.domain.model.school.common.Person;

import java.util.Date;

/**
 * 教师，负责某课程教学
 *
 * @author Liguiqing
 * @since V3.0
 */

public class Teacher extends Staff {

    private Course course;

    public Teacher(SchoolId schoolId, String name, String identity, Period period, Course course) {
        //super(schoolId,name, Gender.Unknow, identity, period,StaffPost.Clazz);
        super(schoolId,new Person(identity,name), Gender.Unknow,
                new Identity(IdentityType.JobNo,identity), period,StaffPost.Clazz);
        this.course = course;
    }

    public Teacher(SchoolId schoolId,String name, Gender gender, String identity, Period period,Course course) {
        //super(schoolId,name, gender, identity, period,StaffPost.Clazz);
        super(schoolId,new Person(identity,name), gender,
                new Identity(IdentityType.JobNo,identity), period,StaffPost.Clazz);
        this.course = course;
    }

    @Override
    protected Teacher renew(Period newPeroid) {
        this.changePeriod(newPeroid);
        return this;
    }

    public Course course() {
        return course;
    }

    public boolean isTeaching(){
        Date today = DateUtilWrapper.today();
        return this.period().isBetween(today);
    }

    /**
     * 教其他课程
     *
     * @param course
     * @param period
     * @return
     */
    public Teacher teachingMore(Course course,Period period){
        Teacher newTeacher = new Teacher(this.schoolId(),this.person().name(),this.person().identity(),period,course);
        return newTeacher;
    }

    /**
     * 作为班主任
     *
     * @param clazzId
     * @param period
     * @return
     */
    public ClazzMaster asClazzMaster(ClazzId clazzId,Period period){
        return new ClazzMaster(clazzId,this.schoolId(),this.person().name(),this.person().identity(),period);
    }

    /**
     * 作为年级主任
     *
     * @param grade
     * @param period
     * @return
     */
    public GradeMaster asGradeMaster(Grade grade,Period period){
        return new GradeMaster(this.schoolId(),grade,this.person().name(),this.person().identity(),period);
    }

    /**
     * 作为学科负责人
     *
     * @param period
     * @param subjectName
     * @param subjectId
     * @return
     */
    public SubjectMaster asSubjectMaster(Period period,String subjectName,String subjectId){
        return new SubjectMaster(this.schoolId(), this.person().name(), this.person().identity(),
                period, subjectName, subjectId);
    }
}