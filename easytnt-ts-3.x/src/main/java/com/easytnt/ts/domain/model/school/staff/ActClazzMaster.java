/*
 * Copyright (c) 2016,2017, easytnt All Rights Reserved. 深圳市易考试乐学测评有限公司 版权所有.
 */

package com.easytnt.ts.domain.model.school.staff;

import com.easytnt.commons.AssertionConcerns;
import com.easytnt.ts.domain.model.school.clazz.Clazz;
import com.easytnt.ts.domain.model.school.common.Period;

/**
 * @author Liguiqing
 * @since V3.0
 */

public class ActClazzMaster implements Act{

    private Clazz clazz;

    public ActClazzMaster(Clazz clazz) {
        this.clazz = clazz;
    }

    @Override
    public ClazzMaster actTo(Staff staff, Period period) {
        AssertionConcerns.assertArgumentTrue(this.clazz.canBeManaged(),"教学班不能安排班主任");
        return new ClazzMaster(this.clazz.clazzId(),staff.schoolId(),staff.staffId().id(),staff.name(),period);
    }
}