/*
 * Copyright (c) 2016,2017, easytnt All Rights Reserved. 深圳市易考试乐学测评有限公司 版权所有.
 */

package com.easytnt.ts.domain.model.school.student;

import com.easytnt.commons.AssertionConcerns;
import com.easytnt.commons.domain.ValueObject;
import com.easytnt.ts.domain.model.school.clazz.Clazz;
import com.easytnt.ts.domain.model.school.clazz.ClazzId;
import com.easytnt.ts.domain.model.school.common.Period;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

import java.util.Date;

/**
 * 学生所属班级
 * @author Liguiqing
 * @since V3.0
 */

public class BelongToClazz extends ValueObject{

    private ClazzId clazzId;

    private StudentId studentId;

    private Period period;

    public BelongToClazz(Clazz clazz, StudentId studentId, Date starts,Date ends) {
        AssertionConcerns.assertArgumentNotNull(clazz,"请提供学生所属班级");
        AssertionConcerns.assertArgumentNotNull(clazz,"请提供学生所属班级");
        AssertionConcerns.assertArgumentTrue(clazz.canBeManaged(),"请提供学生所属班级");
        this.clazzId = clazz.clazzId();
        this.studentId = studentId;
        this.period = new Period(starts,ends);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BelongToClazz that = (BelongToClazz) o;
        return Objects.equal(clazzId, that.clazzId) &&
                Objects.equal(studentId, that.studentId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(clazzId, studentId);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("clazzId", clazzId)
                .add("studentId", studentId)
                .add("period", period)
                .toString();
    }

    public ClazzId clazzId() {
        return clazzId;
    }

    public StudentId studentId() {
        return studentId;
    }

    public Period period() {
        return period;
    }
}