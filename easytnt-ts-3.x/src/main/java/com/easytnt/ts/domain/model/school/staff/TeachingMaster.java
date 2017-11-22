/*
 * Copyright (c) 2016,2017, easytnt All Rights Reserved. 深圳市易考试乐学测评有限公司 版权所有.
 */

package com.easytnt.ts.domain.model.school.staff;

import com.easytnt.ts.domain.model.school.SchoolId;
import com.easytnt.ts.domain.model.school.common.Gender;
import com.easytnt.ts.domain.model.school.common.Identity;
import com.easytnt.ts.domain.model.school.common.IdentityType;
import com.easytnt.ts.domain.model.school.common.Person;

/**
 * 教务主任
 *
 * @author Liguiqing
 * @since V3.0
 */
public class TeachingMaster extends Staff{

    public TeachingMaster(SchoolId schoolId, String name, String identity, Period period) {
        super(schoolId,new Person(identity,name), Gender.Unknow,
                new Identity(IdentityType.JobNo,identity), period,StaffPost.School);
    }

    public TeachingMaster(SchoolId schoolId,String name, Gender gender, String identity, Period period) {
        super(schoolId,new Person(identity,name,gender), Gender.Unknow,
                new Identity(IdentityType.JobNo,identity), period,StaffPost.School);
    }

    @Override
    protected Staff renew(Period newPeroid) {
        this.changePeriod(newPeroid);
        return this;
    }
}
