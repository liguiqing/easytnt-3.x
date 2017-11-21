/*
 * Copyright (c) 2016,2017, easytnt All Rights Reserved. 深圳市易考试乐学测评有限公司 版权所有.
 */

package com.easytnt.ts.domain.model.school;

import com.easytnt.commons.domain.Entity;
import com.easytnt.ts.domain.model.school.staff.HeadMaster;
import com.easytnt.ts.domain.model.school.staff.Period;
import com.easytnt.ts.domain.model.school.staff.Teacher;
import com.easytnt.ts.domain.model.school.term.*;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 学校
 *
 * @author Liguiqing
 * @since V3.0
 */

public class School extends Entity {

    private SchoolId schoolId;

    private TenantId tenantId;

    private String name;//学校名称

    private SchoolType type;


    public School(TenantId tenantId,SchoolId schoolId,String name,SchoolType type){
        this.name = name;
        this.type = type;
        this.schoolId = schoolId;
        this.tenantId = tenantId;
    }

    public HeadMaster newHeaderMaster(String masterName,String masterId,Date starts,Date ends){
        HeadMaster master = new HeadMaster(this.schoolId(),masterName, masterId,
                new Period(starts, ends));
        return master;
    }

    public Teacher join(String teacherName, String teacherId, Date starts, Date ends, Course course){
        Teacher teacher = new Teacher(this.schoolId(), teacherName, teacherId, new Period(starts, ends), course);
        return teacher;
    }

    public Term newTerm(TermId termId, String termName,String year, TermOrder order, Date starts, Date ends){
        Term term = new Term(termId,termName,new StudyYear(year),order,new TimeSpan(starts,ends),this.schoolId());
        return term;
    }

    public List<Grade> grades (){
        GradeNameGenerateStrategy nameGenerateStrategy =
                GradeNameGenerateStrategyFactory.lookup(this.schoolId());
        SchoolType type = this.type();
        StudyYear year = StudyYear.yearOfNow();
        ArrayList<Grade> grads = Lists.newArrayList();
        for(int i=type.gradeFrom();i<type.gradeTo();i++){
            GradeLevel level = GradeLevel.fromLevel(i);
            Grade grade = new Grade(nameGenerateStrategy.genGradeName(level),level,year);
            grads.add(grade);
        }
        return grads;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        School school = (School) o;
        return Objects.equal(schoolId, school.schoolId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(schoolId);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("schoolId", schoolId)
                .add("tenantId", tenantId)
                .add("name", name)
                .add("type", type)
                .toString();
    }

    public String name(){
        return this.name;
    }

    public SchoolId schoolId() {
        return schoolId;
    }

    public TenantId tenantId() {
        return tenantId;
    }

    public SchoolType type() {
        return type;
    }



}