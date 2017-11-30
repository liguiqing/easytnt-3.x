/*
 * Copyright (c) 2016,2017, easytnt All Rights Reserved. 深圳市易考试乐学测评有限公司 版权所有.
 */

package com.easytnt.ts.domain.model.school.position;

import com.easytnt.ts.domain.model.school.clazz.ClazzId;
import com.easytnt.ts.domain.model.school.staff.Period;

/**
 * 老师担任为班主任
 *
 * @author Liguiqing
 * @since V3.0
 */

public class TeacherToClazzMasterTranslater implements PositionTransfer {

    private ClazzId clazzId;

    private Period period;

    public TeacherToClazzMasterTranslater(ClazzId clazzId, Period period) {
        this.clazzId = clazzId;
        this.period = period;
    }

    @Override
    public ClazzMaster translate(Teacher teacher) {
        return new ClazzMaster(clazzId,teacher.schoolId(),teacher.name(),teacher.identity(),this.period);
    }

}