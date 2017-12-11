/*
 * Copyright (c) 2016,2017, easytnt All Rights Reserved. 深圳市易考试乐学测评有限公司 版权所有.
 */

package com.easytnt.ts.domain.model.school.clazz;

import com.easytnt.commons.AssertionConcerns;
import com.easytnt.commons.domain.DomainEventPublisher;
import com.easytnt.commons.domain.Entity;
import com.easytnt.commons.util.DateUtilWrapper;
import com.easytnt.ts.domain.model.school.*;
import com.easytnt.ts.domain.model.school.common.WLType;
import com.easytnt.ts.domain.model.school.position.ClazzMaster;
import com.easytnt.ts.domain.model.school.position.Teacher;
import com.easytnt.ts.domain.model.school.position.TeacherToClazzMasterTranslater;
import com.easytnt.ts.domain.model.school.common.Period;
import com.easytnt.ts.domain.model.school.term.Term;
import com.easytnt.ts.domain.model.school.term.TermId;
import com.easytnt.ts.domain.model.school.term.TermOrder;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;

import java.util.Date;
import java.util.Set;

/**
 * 班级
 *
 * @author Liguiqing
 * @since V3.0
 */

public class Clazz extends Entity {
    private SchoolId schoolId;

    private ClazzId clazzId;

    private String name;

    private String clazzNo;//班号，学校自编，是班级连续的标识

    private String createDate; //建班日期，使用时格式为YYYY-mm

    private ClazzAdiminType adminType;

    private Set<ClazzCatagory> catagories;

    private Set<ClazzHistory> histories;

    public Clazz(SchoolId schoolId, ClazzId clazzId, String name, String clazzNo, String createDate,
                 Grade grade, ClazzAdiminType adminType, WLType wl, TermId termId) {
        AssertionConcerns.assertArgumentNotNull(schoolId,"请提供学校唯一标识");
        AssertionConcerns.assertArgumentNotNull(clazzId,"请提供班级唯一标识");
        AssertionConcerns.assertArgumentNotNull(name,"请提供班级名称");
        AssertionConcerns.assertArgumentNotNull(createDate,"请提供班级创建日期");
        AssertionConcerns.assertArgumentNotNull(grade,"请提供班级所属年级");
        AssertionConcerns.assertArgumentNotNull(adminType,"请提供班级管理类型");

        this.schoolId = schoolId;
        this.clazzId = clazzId;
        this.name = name;
        this.clazzNo = clazzNo;
        this.createDate = createDate;
        this.adminType = adminType;


        Date date = DateUtilWrapper.toDate(createDate,"yyyy-MM");
        TermOrder order = TermOrder.orderOf(date);
        ClazzHistory aHistory = new ClazzHistory(clazzId,termId,order,grade,wl);
        this.histories = Sets.newTreeSet();
        this.histories.add(aHistory);
    }

    public boolean canBeStudyAndTeachIn(){
        return this.canBeManaged() && this.canBeStudied();
    }

    public Grade termGrade(Term term){
        for(ClazzHistory history:this.histories){
            if(history.termId().equals(term.termId())){
                return history.grade();
            }
        }
        throw new ClazzNotInTermException(term.toString());
    }

    public ClazzMaster changeClazzMaster(Teacher teacher,Period period){
        return teacher.transfer(new TeacherToClazzMasterTranslater(this.clazzId(),period));
    }

    public Teach addTeacher(Teacher teacher, Grade grade, Term term, Course course){
        AssertionConcerns.assertArgumentTrue(this.canBeStudyAndTeachIn(),"老师不能在非教学班级里教学");

        Teach teach =  new Teach(teacher,this.clazzId,grade,term.timeSpan().starts(),term.timeSpan().ends());
        DomainEventPublisher.instance().publish(new TeacherJoinedToClazz(this.clazzId,teacher.name(),
                teacher.identity()));
        return teach;
    }

    /**
     * 班级升一个年级
     * 能升班的规则是，最近的班级史必须是下学期;新学期学年必须是最近班级史的下一学年
     *
     * @param term
     */
    public void upGrade(Term term){
        ClazzHistory top = this.histories.iterator().next();
        AssertionConcerns.assertArgumentTrue(top.termOrder() == TermOrder.Second,"上学期不能升班");
        StudyYear topYearNext = term.termYear().nextYear();
        AssertionConcerns.assertArgumentTrue(topYearNext.equals(term.termYear()),"升班后的学期必须是新的学期");

        GradeNameGenerateStrategy nameGenerateStrategy = GradeNameGenerateStrategyFactory.lookup(this.schoolId);
        Grade nextGrade = top.grade().next(nameGenerateStrategy);
        ClazzHistory newHistory = new ClazzHistory(clazzId,term.termId(),term.order(),nextGrade,top.wl());
        this.histories.add(newHistory);
    }

    /**
     * 增加班分类
     *
     * @param aCatagory
     */
    public void addCatagory(ClazzCatagory aCatagory){
        if(this.catagories == null)
            this.catagories = Sets.newHashSet();
        this.catagories.add(aCatagory);
    }

    public boolean canBeManaged(){
        return this.adminType.canBeManaged();
    }

    public boolean canBeStudied(){
        return this.adminType.canBeStudied();
    }

    public SchoolId schoolId() {
        return schoolId;
    }

    public ClazzId clazzId() {
        return clazzId;
    }

    public String name() {
        return name;
    }

    public String clazzNo() {
        return clazzNo;
    }

    public String createDate() {
        return createDate;
    }

    public ClazzAdiminType adminType() {
        return adminType;
    }

    public Set<ClazzCatagory> catagories() {
        return ImmutableSet.copyOf(catagories);
    }

    public Set<ClazzHistory> histories() {
        return ImmutableSet.copyOf(histories);
    }

    protected Clazz(){
        //Only 4 persistent
    }

}