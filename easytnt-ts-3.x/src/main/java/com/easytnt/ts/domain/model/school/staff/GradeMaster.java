/*
 * Copyright (c) 2016,2017, easytnt All Rights Reserved. 深圳市易考试乐学测评有限公司 版权所有.
 */

package com.easytnt.ts.domain.model.school.staff;

import com.easytnt.ts.domain.model.school.common.Gender;
import com.easytnt.ts.domain.model.school.Grade;
import com.easytnt.ts.domain.model.school.SchoolId;
import com.easytnt.ts.domain.model.school.common.Identity;
import com.easytnt.ts.domain.model.school.common.IdentityType;
import com.easytnt.ts.domain.model.school.common.Person;

/**
 * 年级负责人
 *
 * @author Liguiqing
 * @since V3.0
 */

public class GradeMaster extends Staff {
    private Grade grade;

    public GradeMaster(SchoolId schoolId, Grade grade, String name, String identity, Period period) {
        super(schoolId,new Person(identity,name), Gender.Unknow,
                new Identity(IdentityType.JobNo,identity), period,StaffPost.Grade);
        this.grade = grade;
    }

    public GradeMaster(SchoolId schoolId,Grade grade,String name, Gender gender, String identity, Period period) {
        //super(schoolId,name, gender, identity, period,StaffPost.Grade);
        super(schoolId,new Person(identity,name,gender), Gender.Unknow,
                new Identity(IdentityType.JobNo,identity), period,StaffPost.Grade);
        this.grade = grade;
    }

    @Override
    protected Staff renew(Period newPeroid) {
        this.changePeriod(newPeroid);
        return this;
    }

}