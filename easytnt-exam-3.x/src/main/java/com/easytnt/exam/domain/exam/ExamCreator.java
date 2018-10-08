/*
 * Copyright (c) 2016,2018, easytnt All Rights Reserved. 深圳市易考试乐学测评有限公司 版权所有.
 */

package com.easytnt.exam.domain.exam;

import com.easytnt.commons.domain.ValueObject;
import com.google.common.base.Objects;

/**
 * 考试创建者
 *
 * @author Liguiqing
 * @since V3.0
 */

public class ExamCreator extends ValueObject {

    private String creatorOrgId;

    private CreatorOrgType creatorOrgTyp;

    private String creatorId;

    public ExamCreator(String creatorOrgId, CreatorOrgType creatorOrgTyp, String creatorId) {
        this.creatorOrgId = creatorOrgId;
        this.creatorOrgTyp = creatorOrgTyp;
        this.creatorId = creatorId;
    }

    public String creatorOrgId() {
        return creatorOrgId;
    }

    public CreatorOrgType creatorOrgTyp() {
        return creatorOrgTyp;
    }

    public String creatorId() {
        return creatorId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExamCreator that = (ExamCreator) o;
        return Objects.equal(creatorOrgId, that.creatorOrgId) &&
                creatorOrgTyp == that.creatorOrgTyp &&
                Objects.equal(creatorId, that.creatorId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(creatorOrgId, creatorOrgTyp, creatorId);
    }
}