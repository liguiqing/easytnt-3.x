/*
 * Copyright (c) 2016,2017, easytnt All Rights Reserved. 深圳市易考试乐学测评有限公司 版权所有.
 */

package com.easytnt.ts.domain.model.school;

import com.easytnt.commons.AssertionConcerns;
import com.easytnt.commons.domain.ValueObject;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

/**
 * 学年
 * 学年是跨年度，结束年度是开始年度的下一年
 * @author Liguiqing
 * @since V3.0
 */

public class StudyYear extends ValueObject {

    private String name;

    private int startsYear;

    private int endsYear;

    public StudyYear(int startsYear, int endsYear) {
        init(startsYear, endsYear);
    }

    /**
     *
     * @param year YYYY-YYYY
     */
    public StudyYear(String year) {
        String[] years = year.split("-");
        init(Integer.valueOf(years[0]), Integer.valueOf(years[1]));
    }

    private void init(int startsYear, int endsYear){
        AssertionConcerns.assertArgumentTrue((endsYear-startsYear==1),"学年开始年度与结束年度不合理：startsYear-"
                + startsYear + ",endsYear-" + endsYear);
        this.startsYear = startsYear;
        this.endsYear = endsYear;
        this.name = this.startsYear + "至" +this.endsYear +"学年";
    }

    public String year(){
        return this.startsYear + "-" + this.endsYear;
    }

    public StudyYear nextYear(){
        return new StudyYear(this.endsYear,this.endsYear+1);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.startsYear,this.endsYear);
    }

    @Override
    public boolean equals(Object o) {
        if (o != null && o instanceof StudyYear) {
            StudyYear that = (StudyYear) o;
            return Objects.equal(this.startsYear, that.startsYear) && Objects.equal(this.endsYear,that.endsYear) ;
        }
        return false;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this).add("starsDate",this.startsYear)
                .add("endsDate",this.endsYear).toString();
    }

    public int startsYear() {
        return startsYear;
    }

    public int endsYear() {
        return endsYear;
    }
}