/*
 * Copyright (c) 2016,2018, easytnt All Rights Reserved. 深圳市易考试乐学测评有限公司 版权所有.
 */

package com.easytnt.exam.domain.exam;

import com.easytnt.commons.AssertionConcerns;
import com.easytnt.commons.domain.DomainEventPublisher;
import com.easytnt.commons.domain.TracerEntity;
import com.easytnt.commons.util.DateUtilWrapper;
import com.easytnt.share.domain.common.Period;
import com.easytnt.share.domain.id.exam.ExamId;
import com.easytnt.share.domain.id.exam.ProjectId;
import com.easytnt.share.domain.id.org.TargetOrgId;
import com.easytnt.share.domain.school.Grade;
import com.google.common.base.MoreObjects;

import java.util.Date;

/**
 * 考试
 *
 * @author Liguiqing
 * @since V3.0
 */

public class Exam extends TracerEntity {

    private ExamId examId;

    private ProjectId projectId;

    private TargetOrgId targetOrgId;

    private ExamCreator creator;

    private String name;

    private Grade grade;

    private Period period;

    private int category;

    private ExamScope scope;

    private Date createTime;

    private ExamStatus status;

    public Exam(ExamId examId, ProjectId projectId, TargetOrgId targetOrgId,
                Grade grade,ExamScope scope, ExamCreator creator) {
        AssertionConcerns.assertArgumentNotNull(examId,"无效的考试标识");
        AssertionConcerns.assertArgumentNotNull(projectId,"无效的项目标识");
        AssertionConcerns.assertArgumentNotNull(targetOrgId,"无效的考试主办单位标识");
        AssertionConcerns.assertArgumentNotNull(creator,"无效的考试创建者标识");

        this.examId = examId;
        this.projectId = projectId;
        this.targetOrgId = targetOrgId;
        this.grade = grade;
        this.scope = scope;
        this.creator = creator;
        this.createTime = DateUtilWrapper.now();
    }

    public void reName(String name){
        this.name = name;
    }

    public void updatePeriod(Period newPeriod){
        this.period = period;
    }

    public void updateCategory(int newCategory){
        this.category = category;
    }

    public void cancel(){
        this.status = ExamStatus.Cancel;
        this.trace();
        DomainEventPublisher.instance().publish(new ExamCanceled(this.examId));
    }

    public void finish(){
        this.status = ExamStatus.Finish;
        this.trace();
        DomainEventPublisher.instance().publish(new ExamFinished(this.examId));
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("grade", grade)
                .add("period", period)
                .toString();
    }

    public ExamId examId() {
        return examId;
    }

    public ProjectId projectId() {
        return projectId;
    }

    public TargetOrgId targetOrgId() {
        return targetOrgId;
    }

    public ExamCreator creator() {
        return creator;
    }

    public Grade grade() {
        return grade;
    }

    public Period period() {
        return period;
    }

    public int category() {
        return category;
    }

    public ExamScope scope() {
        return scope;
    }

    public Date createTime() {
        return createTime;
    }

    public ExamStatus status() {
        return status;
    }
}