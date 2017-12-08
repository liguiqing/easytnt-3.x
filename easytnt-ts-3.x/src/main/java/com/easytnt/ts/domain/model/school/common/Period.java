/*
 * Copyright (c) 2016,2017, easytnt All Rights Reserved. 深圳市易考试乐学测评有限公司 版权所有.
 */

package com.easytnt.ts.domain.model.school.common;

import com.easytnt.commons.AssertionConcerns;
import com.easytnt.commons.util.DateUtilWrapper;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

import java.util.Date;

/**
 * 时期段
 *
 * @author Liguiqing
 * @since V3.0
 */

public class Period {

    private Date starts;

    private Date ends;

    public Period(Date starts, Date ends) {
        AssertionConcerns.assertArgumentNotNull(starts,"请提供开始时间");
        if(ends != null){
            AssertionConcerns.assertArgumentTrue(DateUtilWrapper.lessThan(starts,ends),"开始时间不能大于结束时间");
        }
        this.starts = starts;
        this.ends = ends;
    }

    public boolean gratherThan(Date ends){
        return DateUtilWrapper.greaterThanYYMMDD(this.ends,ends);
    }

    public boolean isBetween(Date aDate){
        return DateUtilWrapper.lessThan(this.ends,ends) && DateUtilWrapper.greaterThanYYMMDD(this.starts,aDate);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Period period = (Period) o;
        return Objects.equal(starts, period.starts) &&
                Objects.equal(ends, period.ends);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(starts, ends);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("starts", starts)
                .add("ends", ends)
                .toString();
    }

    public Date starts() {
        return starts;
    }

    public Date ends() {
        return ends;
    }

    public boolean hasEnds() {
        return this.ends != null;
    }
}