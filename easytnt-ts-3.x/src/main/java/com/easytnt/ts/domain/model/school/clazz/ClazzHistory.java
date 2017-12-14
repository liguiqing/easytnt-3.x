/*
 * Copyright (c) 2016,2017, easytnt All Rights Reserved. 深圳市易考试乐学测评有限公司 版权所有.
 */

package com.easytnt.ts.domain.model.school.clazz;

import com.easytnt.commons.domain.ValueObject;
import com.easytnt.ts.domain.model.school.Grade;
import com.easytnt.ts.domain.model.school.common.WLType;
import com.easytnt.ts.domain.model.school.position.ClazzMaster;
import com.easytnt.ts.domain.model.school.position.Teacher;
import com.easytnt.ts.domain.model.school.term.TermId;
import com.easytnt.ts.domain.model.school.term.TermOrder;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

/**
 * 班级历史
 *
 * @author Liguiqing
 * @since V3.0
 */

public class ClazzHistory extends ValueObject implements Comparable<ClazzHistory>{

    private ClazzId clazzId;

    private TermId termId;

    private TermOrder termOrder;

    private Grade grade; //年级

    private WLType wl;

    private ClazzMaster master;

    private Teacher mainTeacher;

    protected ClazzHistory(ClazzId clazzId, TermId termId,TermOrder termOrder, Grade grade, WLType wl) {
        this.clazzId = clazzId;
        this.termId = termId;
        this.grade = grade;
        this.wl = wl;
        this.termOrder = termOrder;
    }

    public ClazzId clazzId() {
        return clazzId;
    }

    public TermId termId() {
        return termId;
    }

    public Grade grade() {
        return grade;
    }

    public WLType wl() {
        return wl;
    }

    public TermOrder termOrder() {
        return termOrder;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClazzHistory that = (ClazzHistory) o;
        return Objects.equal(clazzId, that.clazzId) &&
                Objects.equal(termId, that.termId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(clazzId, termId);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("clazzId", clazzId)
                .add("termId", termId)
                .add("grade", grade)
                .toString();
    }

    protected ClazzHistory(){
        //Only 4 persistent
    }


    @Override
    public int compareTo(ClazzHistory other) {
        int g =  this.grade.seq().getSeq() - other.grade.seq().getSeq();
        if(g==0)
            return this.termOrder.compareTo(other.termOrder);
        else
            return g;
    }
}