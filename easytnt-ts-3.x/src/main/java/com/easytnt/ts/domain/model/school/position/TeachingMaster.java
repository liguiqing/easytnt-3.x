/*
 * Copyright (c) 2016,2017, easytnt All Rights Reserved. 深圳市易考试乐学测评有限公司 版权所有.
 */

package com.easytnt.ts.domain.model.school.position;

import com.easytnt.ts.domain.model.school.SchoolId;
import com.easytnt.ts.domain.model.school.staff.Period;

/**
 * 其他校领导，如教务主任
 *
 * @author Liguiqing
 * @since V3.0
 */
public class TeachingMaster extends Position {

    private String positionName;

    public TeachingMaster(SchoolId schoolId, String name, String identity, Period period,String positionName) {
        super(schoolId,identity,name, period);
        this.positionName = positionName;
    }

    @Override
    public String positionName() {
        return this.positionName;
    }
}
