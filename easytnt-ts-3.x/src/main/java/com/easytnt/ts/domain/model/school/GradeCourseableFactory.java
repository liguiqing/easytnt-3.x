/*
 * Copyright (c) 2016,2017, easytnt All Rights Reserved. 深圳市易考试乐学测评有限公司 版权所有.
 */

package com.easytnt.ts.domain.model.school;

import com.easytnt.commons.spring.SpringContextUtil;
import com.easytnt.ts.infrastructure.strategy.DefaultGradeCourseable;

/**
 * @author Liguiqing
 * @since V3.0
 */

public class GradeCourseableFactory {

    public static GradeCourseable lookup(SchoolId schoolId){
        //TODO
        return SpringContextUtil.getBean(DefaultGradeCourseable.class);
    }

}