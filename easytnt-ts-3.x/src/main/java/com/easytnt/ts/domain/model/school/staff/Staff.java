/*
 * Copyright (c) 2016,2017, easytnt All Rights Reserved. 深圳市易考试乐学测评有限公司 版权所有.
 */

package com.easytnt.ts.domain.model.school.staff;

import com.easytnt.commons.AssertionConcerns;
import com.easytnt.commons.domain.Entity;
import com.easytnt.ts.domain.model.school.common.Gender;
import com.easytnt.ts.domain.model.school.SchoolId;
import com.easytnt.ts.domain.model.school.common.Identity;
import com.easytnt.ts.domain.model.school.common.Person;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

import java.util.Date;

/**
 * 教职工
 *
 * @author Liguiqing
 * @since V3.0
 */

public abstract class Staff extends Entity {
    private SchoolId schoolId;

    private Person person;

    private Gender gender;

    private Identity identity; //唯一标识,可以是学校的工号，也可以由系统自动生成

    private Period period;

    private StaffPost post;

    public Staff(SchoolId schoolId, Person person,  Gender gender,
                 Identity identity, Period period, StaffPost post) {
        this.schoolId = schoolId;
        this.person = person;
        this.gender = gender;
        this.identity = identity;
        this.period = period;
        this.post = post;
    }

    /**
     * 续签
     * @param ends
     * @return
     */
    public Staff renew(Date ends){
        boolean b = this.period.gratherThan(ends);
        AssertionConcerns.assertArgumentTrue(b,"续签日期应该大于当前任职的结束日期");
        Period newPeriod = new Period(this.period.starts(),ends);
        return renew(newPeriod);
    }


    public void changePeriod(Period period) {
        this.period = period;
    }

    protected abstract Staff renew(Period newPeroid);

    public boolean isPostOf(StaffPost post){
        return this.post.equals(post);
    }

    public boolean isTeaching(){
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Staff staff = (Staff) o;
        return Objects.equal(identity, staff.identity);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(identity);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("identity", identity)
                .add("period", period)
                .toString();
    }

    public SchoolId schoolId() {
        return schoolId;
    }

    public Person person() {
        return person;
    }

    public Gender gender() {
        return gender;
    }

    public Identity identity() {
        return identity;
    }

    public Period period() {
        return period;
    }

    public StaffPost post() {
        return post;
    }
}
