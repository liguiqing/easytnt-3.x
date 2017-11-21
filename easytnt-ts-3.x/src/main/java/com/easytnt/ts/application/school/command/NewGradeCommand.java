/*
 * Copyright (c) 2016,2017, easytnt All Rights Reserved. 深圳市易考试乐学测评有限公司 版权所有.
 */

package com.easytnt.ts.application.school.command;

import com.google.common.base.MoreObjects;

import java.util.Date;

/**
 * @author Liguiqing
 * @since V3.0
 */

public class NewGradeCommand {
    private String name;

    private String gradeLevel;

    private String studyYear;

    private String masterMame;

    private String masterIdentity;

    private Date masterStarts;

    private Date masterEends;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGradeLevel() {
        return gradeLevel;
    }

    public void setGradeLevel(String gradeLevel) {
        this.gradeLevel = gradeLevel;
    }

    public String getMasterMame() {
        return masterMame;
    }

    public void setMasterMame(String masterMame) {
        this.masterMame = masterMame;
    }

    public String getMasterIdentity() {
        return masterIdentity;
    }

    public void setMasterIdentity(String masterIdentity) {
        this.masterIdentity = masterIdentity;
    }

    public Date getMasterStarts() {
        return masterStarts;
    }

    public void setMasterStarts(Date masterStarts) {
        this.masterStarts = masterStarts;
    }

    public Date getMasterEends() {
        return masterEends;
    }

    public void setMasterEends(Date masterEends) {
        this.masterEends = masterEends;
    }

    public String getStudyYear() {
        return studyYear;
    }

    public void setStudyYear(String studyYear) {
        this.studyYear = studyYear;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("name", name)
                .add("gradeLevel", gradeLevel)
                .add("masterMame", masterMame)
                .add("masterIdentity", masterIdentity)
                .add("masterStarts", masterStarts)
                .add("masterEends", masterEends)
                .toString();
    }
}