/*
 * Copyright (c) 2016,2017, easytnt All Rights Reserved. 深圳市易考试乐学测评有限公司 版权所有.
 */

package com.easytnt.ts.domain.model.school.position;

import com.easytnt.commons.domain.ValueObject;
import com.easytnt.ts.domain.model.school.SchoolId;
import com.easytnt.ts.domain.model.school.common.Period;
import com.google.common.base.Objects;

/**
 * 学校职务
 * 如果校长，年级主任，学科老师等
 *
 * @author Liguiqing
 * @since V3.0
 */

public abstract class Position extends ValueObject implements Comparable<Position> {

    private SchoolId schoolId;

    private Period period;

    private String name; //教职工姓名

    private String identity; //唯一身份标识，关联到staff.Staff时就是Staff.staffId

    public Position(SchoolId schoolId, String name, String identity, Period period) {
        this.schoolId = schoolId;
        this.period = period;
        this.name = name;
        this.identity = identity;
    }

    public abstract String positionName();

    public abstract Position renew(Period newPerid);

    public boolean sameSchoolOf(SchoolId schoolId){
        return this.schoolId.equals(schoolId);
    }

    @Override
    public int compareTo(Position duty) {
        if(this.identity.equals(duty.identity)){
            return 0;
        }
        return -1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position duty = (Position) o;
        return Objects.equal(schoolId, duty.schoolId) &&
                Objects.equal(identity, duty.identity);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(schoolId, identity);
    }

    public SchoolId schoolId() {
        return schoolId;
    }

    public Period period() {
        return period;
    }

    public String name() {
        return name;
    }

    public String identity() {
        return identity;
    }


}