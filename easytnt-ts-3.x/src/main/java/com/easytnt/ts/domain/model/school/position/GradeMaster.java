/*
 * Copyright (c) 2016,2017, easytnt All Rights Reserved. 深圳市易考试乐学测评有限公司 版权所有.
 */

package com.easytnt.ts.domain.model.school.position;

import com.easytnt.ts.domain.model.school.Grade;
import com.easytnt.ts.domain.model.school.SchoolId;
import com.easytnt.ts.domain.model.school.staff.Period;

/**
 * 年级负责人
 *
 * @author Liguiqing
 * @since V3.0
 */

public class GradeMaster extends Position {
    private Grade grade;

    public GradeMaster(SchoolId schoolId, Grade grade, String name, String identity, Period period) {
        super(schoolId,name, identity, period);
        this.grade = grade;
    }


    @Override
    public String positionName() {
        return "年级主任";
    }

    public Grade grade() {
        return grade;
    }
}