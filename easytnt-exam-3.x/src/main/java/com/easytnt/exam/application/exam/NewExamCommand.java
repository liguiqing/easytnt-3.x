/*
 * Copyright (c) 2016,2018, easytnt All Rights Reserved. 深圳市易考试乐学测评有限公司 版权所有.
 */

package com.easytnt.exam.application.exam;

import java.util.Date;

/**
 * @author Liguiqing
 * @since V3.0
 */

public class NewExamCommand {
    private String name;

    private String gradeName;

    private Date starts;

    private Date ends;

    private int category;

    private int scope;

    private String targetOrgId;

    private String creatorOrgId;

    private int creatorOrgType;

    private String creatorId;

    public NewExamCommand(String name, String gradeName, Date starts, Date ends,
                          int category, int scope, String targetOrgId,
                          String creatorOrgId, int creatorOrgType, String creatorId) {
        this.name = name;
        this.gradeName = gradeName;
        this.starts = starts;
        this.ends = ends;
        this.category = category;
        this.scope = scope;
        this.targetOrgId = targetOrgId;
        this.creatorOrgId = creatorOrgId;
        this.creatorOrgType = creatorOrgType;
        this.creatorId = creatorId;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public String getTargetOrgId() {
        return targetOrgId;
    }

    public void setTargetOrgId(String targetOrgId) {
        this.targetOrgId = targetOrgId;
    }

    public String getCreatorOrgId() {
        return creatorOrgId;
    }

    public void setCreatorOrgId(String creatorOrgId) {
        this.creatorOrgId = creatorOrgId;
    }

    public int getCreatorOrgType() {
        return creatorOrgType;
    }

    public void setCreatorOrgType(int creatorOrgType) {
        this.creatorOrgType = creatorOrgType;
    }

    public String getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }

    public String getName() {
        return name;
    }

    public String getGradeName() {
        return gradeName;
    }

    public Date getStarts() {
        return starts;
    }

    public Date getEnds() {
        return ends;
    }

    public int getCategory() {
        return category;
    }

    public int getScope() {
        return scope;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGradeName(String gradeName) {
        this.gradeName = gradeName;
    }

    public void setStarts(Date starts) {
        this.starts = starts;
    }

    public void setEnds(Date ends) {
        this.ends = ends;
    }

    public void setCatagory(int category) {
        this.category = category;
    }

    public void setScope(int scope) {
        this.scope = scope;
    }
}