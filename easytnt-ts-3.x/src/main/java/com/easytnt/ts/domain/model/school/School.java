/*
 * Copyright (c) 2016,2017, easytnt All Rights Reserved. 深圳市易考试乐学测评有限公司 版权所有.
 */

package com.easytnt.ts.domain.model.school;

import com.easytnt.commons.AssertionConcerns;
import com.easytnt.commons.domain.Entity;
import com.easytnt.ts.domain.model.school.common.Identity;
import com.easytnt.ts.domain.model.school.position.HeadMaster;
import com.easytnt.ts.domain.model.school.common.Period;
import com.easytnt.ts.domain.model.school.staff.Staff;
import com.easytnt.ts.domain.model.school.staff.StaffId;
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
        AssertionConcerns.assertArgumentNotNull(tenantId,"请提供租户唯一标识");
        AssertionConcerns.assertArgumentNotNull(schoolId,"请提供学校唯一标识");
        AssertionConcerns.assertArgumentNotNull(schoolId,"请提供校名");
        this.name = name;
        this.type = type == null?SchoolType.Unkow:type;
        this.schoolId = schoolId;
        this.tenantId = tenantId;
    }

    public HeadMaster changeHeadMaster(String masterName,String masterId,Date starts,Date ends){
        HeadMaster master = new HeadMaster(this.schoolId(),masterName, masterId,
                new Period(starts, ends));
        return master;
    }

    public Staff join(String staffName, StaffId staffId, Date starts, Date ends, Identity identity){
        Staff staff = new Staff(this.schoolId(), staffId, staffName, identity,new Period(starts, ends));
        return staff;
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
        for(int i=type.gradeFrom();i <= type.gradeTo();i++){
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