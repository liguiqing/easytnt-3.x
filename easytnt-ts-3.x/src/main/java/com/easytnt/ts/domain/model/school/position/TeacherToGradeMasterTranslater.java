/*
 * Copyright (c) 2016,2017, easytnt All Rights Reserved. 深圳市易考试乐学测评有限公司 版权所有.
 */

package com.easytnt.ts.domain.model.school.position;

import com.easytnt.ts.domain.model.school.Grade;
import com.easytnt.ts.domain.model.school.common.Period;

/**
 * 老师担任为年级主任
 *
 * @author Liguiqing
 * @since V3.0
 */

public class TeacherToGradeMasterTranslater implements PositionTransfer {

    private Grade grade;

    private Period period;

    public TeacherToGradeMasterTranslater(Grade grade, Period period) {
        this.grade = grade;
        this.period = period;
    }

    @Override
    public GradeMaster translate(Teacher teacher) {
        return new GradeMaster(teacher.schoolId(),this.grade,teacher.name(),teacher.identity(),this.period);
    }
    
}