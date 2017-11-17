/*
 * Copyright (c) 2016,2017, easytnt All Rights Reserved. 深圳市易考试乐学测评有限公司 版权所有.
 */

package com.easytnt.ts.domain.model.school.staff;

import com.easytnt.ts.domain.model.school.common.Gender;
import com.easytnt.ts.domain.model.school.SchoolId;
import com.easytnt.ts.domain.model.school.clazz.ClazzId;
import com.easytnt.ts.domain.model.school.common.Identity;
import com.easytnt.ts.domain.model.school.common.IdentityType;
import com.easytnt.ts.domain.model.school.common.Person;

/**
 * 班主任
 *
 * @author Liguiqing
 * @since V3.0
 */

public class ClazzMaster extends Staff {

    private ClazzId clazzId;

    public ClazzMaster(ClazzId clazzId,SchoolId schoolId,String name, String identity, Period period) {
        this(clazzId,schoolId,name, Gender.Unknow, identity, period);
    }

    public ClazzMaster(ClazzId clazzId,SchoolId schoolId, String name, Gender gender, String identity, Period period) {
        super(schoolId,new Person(identity,name), Gender.Unknow,
                new Identity(IdentityType.JobNo,identity), period,StaffPost.Clazz);
        this.clazzId = clazzId;
    }

    @Override
    protected Staff renew(Period newPeroid) {
        this.changePeriod(newPeroid);
        return this;
    }

    public ClazzId clazzId() {
        return clazzId;
    }
}