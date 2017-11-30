/*
 * Copyright (c) 2016,2017, easytnt All Rights Reserved. 深圳市易考试乐学测评有限公司 版权所有.
 */

package com.easytnt.ts.domain.model.school.position;

import com.easytnt.ts.domain.model.school.SchoolId;
import com.easytnt.ts.domain.model.school.staff.Period;

/**
 * 校长
 *
 * @author Liguiqing
 * @since V3.0
 */

public class HeadMaster extends Position {

    public HeadMaster(SchoolId schoolId,String name, String identity, Period period) {
        super(schoolId,name, identity, period);
    }


    @Override
    public String positionName() {
        return "校长";
    }

}