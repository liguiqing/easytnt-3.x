/*
 * Copyright (c) 2016,2017, easytnt All Rights Reserved. 深圳市易考试乐学测评有限公司 版权所有.
 */

package com.easytnt.ts.domain.model.school.staff;

import com.easytnt.ts.domain.model.school.common.Period;

/**
 * @author Liguiqing
 * @since V3.0
 */

public class ActSubjectMaster implements Act {

    private String subjectId;

    private String subjectName;

    public ActSubjectMaster(String subjectId, String subjectName) {
        this.subjectId = subjectId;
        this.subjectName = subjectName;
    }

    @Override
    public SubjectMaster actTo(Staff staff, Period period) {
        return new SubjectMaster(staff.schoolId(), staff.staffId().id(),
                staff.name(), period, this.subjectName, this.subjectId);
    }
}