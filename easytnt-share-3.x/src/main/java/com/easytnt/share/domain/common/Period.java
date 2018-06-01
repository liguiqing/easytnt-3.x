/*
 * Copyright (c) 2016,2018, easytnt All Rights Reserved. 深圳市易考试乐学测评有限公司 版权所有.
 */

package com.easytnt.share.domain.common;

import com.easytnt.commons.AssertionConcerns;
import com.easytnt.commons.util.DateUtilWrapper;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

import java.util.Date;

/**
 * 时段
 *
 * @author Liguiqing
 * @since V3.0
 */

public class Period {

    private Date starts;

    private Date ends;

    public Period(Date starts, Date ends) {
        AssertionConcerns.assertArgumentNotNull(starts,"com.easytnt.share.domain.common.Period.starts");
        AssertionConcerns.assertArgumentNotNull(ends,"com.easytnt.share.domain.common.Period.ends");
        boolean b = DateUtilWrapper.largeThan(ends, starts);
        AssertionConcerns.assertArgumentTrue(b,"com.easytnt.share.domain.common.Period.valid");
        this.starts = starts;
        this.ends = ends;
    }

    public Date starts() {
        return starts;
    }

    public Date ends() {
        return ends;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("starts", starts)
                .add("ends", ends)
                .toString();
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
}