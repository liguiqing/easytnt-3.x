/*
 * Copyright (c) 2016,2017, easytnt All Rights Reserved. 深圳市易考试乐学测评有限公司 版权所有.
 */

package com.easytnt.ts.domain.model.school.position;

import com.easytnt.ts.domain.model.school.SchoolId;
import com.easytnt.ts.domain.model.school.clazz.ClazzId;
import com.easytnt.ts.domain.model.school.staff.Period;

/**
 * 班主任
 *
 * @author Liguiqing
 * @since V3.0
 */

public class ClazzMaster extends Position {

    private ClazzId clazzId;

    public ClazzMaster(ClazzId clazzId,SchoolId schoolId,String name, String identity, Period period) {
        super(schoolId,name, identity, period);
        this.clazzId = clazzId;
    }

    @Override
    public String positionName() {
        return "班主任";
    }

    public ClazzId clazzId() {
        return clazzId;
    }
}