/*
 * Copyright (c) 2016,2017, easytnt All Rights Reserved. 深圳市易考试乐学测评有限公司 版权所有.
 */

package com.easytnt.ts.domain.model.school.student;

import com.easytnt.ts.domain.model.school.common.Identity;
import com.easytnt.ts.domain.model.school.common.IdentityType;
import com.easytnt.ts.domain.model.school.common.Period;

/**
 * @author Liguiqing
 * @since V3.0
 */

public class StudentIdentity extends Identity {


    public StudentIdentity(StudentId studentId,IdentityType type, String value) {
        super(studentId,type, value);
    }

    public StudentIdentity(StudentId studentId,IdentityType type, String value, Period validity) {
        super(studentId,type, value, validity);
    }
}