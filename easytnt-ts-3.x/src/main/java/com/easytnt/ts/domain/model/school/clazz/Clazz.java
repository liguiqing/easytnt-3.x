/*
 * Copyright (c) 2016,2017, easytnt All Rights Reserved. 深圳市易考试乐学测评有限公司 版权所有.
 */

package com.easytnt.ts.domain.model.school.clazz;

import com.easytnt.commons.AssertionConcerns;
import com.easytnt.commons.domain.Entity;
import com.easytnt.commons.util.DateUtilWrapper;
import com.easytnt.ts.domain.model.school.*;
import com.easytnt.ts.domain.model.school.common.Period;
import com.easytnt.ts.domain.model.school.common.WLType;
import com.easytnt.ts.domain.model.school.term.Term;
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

public abstract class Clazz extends Entity {
    private SchoolId schoolId;

    private ClazzId clazzId;

    private String name;

    private String clazzNo;//班号，学校自编，是班级连续的标识

    private String createDate; //建班日期，使用时格式为YYYY-mm

    private Set<ClazzCatagory> catagories;

    private Set<ClazzHistory> histories;

    public Clazz(SchoolId schoolId, ClazzId clazzId, String name, String clazzNo, Date createDate,
                 Grade grade,Term term) {
        this(schoolId,clazzId,name,clazzNo,createDate,grade,WLType.None,term);
    }


    public Clazz(SchoolId schoolId, ClazzId clazzId, String name, String clazzNo, Date createDate,
                 Grade grade, WLType wl, Term term) {
        this(schoolId,clazzId,name,clazzNo,DateUtilWrapper.toString(createDate,"yyyyy-MM"),
                grade,wl,term);
    }

    public Clazz(SchoolId schoolId, ClazzId clazzId, String name, String clazzNo, String createDate,
                 Grade grade,  WLType wl, Term term) {
        AssertionConcerns.assertArgumentNotNull(schoolId,"请提供学校唯一标识");
        AssertionConcerns.assertArgumentNotNull(clazzId,"请提供班级唯一标识");
        AssertionConcerns.assertArgumentNotNull(name,"请提供班级名称");
        AssertionConcerns.assertArgumentNotNull(createDate,"请提供班级创建日期");
        AssertionConcerns.assertArgumentNotNull(grade,"请提供班级所属年级");

        this.schoolId = schoolId;
        this.clazzId = clazzId;
        this.name = name;
        this.clazzNo = clazzNo;
        this.createDate = createDate;
        this.histories = Sets.newTreeSet();

        if(term != null){
            Date date = DateUtilWrapper.toDate(createDate,"yyyy-MM");
            TermOrder order = TermOrder.orderOf(date);
            ClazzHistory aHistory = new ClazzHistory(clazzId,term,order,grade,wl);
            this.histories.add(aHistory);
        }
    }

    public Grade termGrade(Term term){
        for(ClazzHistory history:this.histories){
            if(history.termId().equals(term.termId())){
                return history.grade();
            }
        }
        throw new ClazzNotInTermException(term.toString());
    }

    public WLType termWL(Term term){
        for(ClazzHistory history:this.histories){
            if(history.termId().equals(term.termId())){
                return history.wl();
            }
        }
        return WLType.None;
    }

    public Grade periodGrade(Period period){
        for(ClazzHistory history:this.histories){
            if(history.isInPeriod(period)){
                return history.grade();
            }
        }
        return null;
    }

    /**
     * 班级升一个年级
     * 能升班的规则是，最近的班级史必须是下学期;新学期学年必须是最近班级史的下一学年
     *
     * @param term
     */
    public void upGrade(Term term){
        if(this.histories == null || this.histories.size() == 0)
            return;

        ClazzHistory top = this.histories.iterator().next();
        AssertionConcerns.assertArgumentTrue(top.termOrder() == TermOrder.Second,"上学期不能升班");
        StudyYear topYearNext = term.termYear().nextYear();
        AssertionConcerns.assertArgumentTrue(topYearNext.equals(term.termYear()),"升班后的学期必须是新的学期");

        GradeNameGenerateStrategy nameGenerateStrategy = GradeNameGenerateStrategyFactory.lookup(this.schoolId);
        Grade nextGrade = top.grade().next(nameGenerateStrategy);
        ClazzHistory newHistory = new ClazzHistory(clazzId,term,term.order(),nextGrade,top.wl());
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


    public abstract boolean canBeStudyAndTeachIn();

    public abstract boolean canBeManaged();

    public abstract boolean canBeStudied();

    protected boolean classOf(String clazzName) {
        return this.getClass().getSimpleName().equals(clazzName);
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

    public Set<ClazzCatagory> catagories() {
        return ImmutableSet.copyOf(catagories);
    }

    public Set<ClazzHistory> histories() {
        return ImmutableSet.copyOf(histories);
    }

    protected Clazz(){

    }

}