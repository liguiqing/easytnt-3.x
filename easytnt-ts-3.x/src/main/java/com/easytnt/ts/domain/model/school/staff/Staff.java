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
import com.easytnt.ts.domain.model.school.position.Position;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.collect.Sets;

import javax.persistence.Id;
import java.util.Date;
import java.util.Set;

/**
 * 教职工
 *
 * @author Liguiqing
 * @since V3.0
 */

public  class Staff extends Entity {
    private SchoolId schoolId;

    private StaffId staffId;

    private String name;

    private Gender gender;

    private Set<Identity> identity; //唯一标识,可以是学校的工号，也可以由系统自动生成

    private Period period;

    private Set<Position> positions;

    public Staff(SchoolId schoolId,StaffId staffId,String name,Identity identity, Period period) {
        this(schoolId,staffId,name, Gender.Unknow, identity, period);
    }

    public Staff(SchoolId schoolId,StaffId staffId,String name,Gender gender,Identity identity, Period period) {
        this.schoolId = schoolId;
        this.staffId = staffId;
        this.name = name;
        this.gender = gender;
        this.identity = Sets.newHashSet();
        this.addIdentity(identity);
        this.period = period;
    }

    public void addIdentity(Identity identity){
        this.identity.add(identity);
    }

    public void addPosition(Position aPosition){
        if(this.positions == null)
            this.positions = Sets.newHashSet();
    }

    /**
     * 续签
     * @param ends
     * @return
     */
    public void renew(Date ends){
        boolean b = this.period.gratherThan(ends);
        AssertionConcerns.assertArgumentTrue(b,"续签日期应该大于当前任职的结束日期");
        this.period = new Period(this.period.starts(),ends);
    }

    public void changePeriod(Period period) {
        this.period = period;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Staff staff = (Staff) o;
        return Objects.equal(schoolId, staff.schoolId) &&
                Objects.equal(staffId, staff.staffId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(schoolId, staffId);
    }

    public SchoolId schoolId() {
        return schoolId;
    }

    public StaffId staffId() {
        return staffId;
    }

    public String name() {
        return name;
    }

    public Gender gender() {
        return gender;
    }

    public Set<Identity> identity() {
        return identity;
    }

    public Period period() {
        return period;
    }

}
