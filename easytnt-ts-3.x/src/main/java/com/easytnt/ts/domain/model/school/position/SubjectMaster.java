/*
 * Copyright (c) 2016,2017, easytnt All Rights Reserved. 深圳市易考试乐学测评有限公司 版权所有.
 */

package com.easytnt.ts.domain.model.school.position;

import com.easytnt.ts.domain.model.school.SchoolId;
import com.easytnt.ts.domain.model.school.staff.Period;


/**
 * 学科负责人,也称科目组长，在学校里某个学科的教学管理工作，是跨年级的
 *
 * @author Liguiqing
 * @since V3.0
 */

public class SubjectMaster extends Position {

    private String subjectId;

    private String subjectName;

    public SubjectMaster(SchoolId schoolId, String name, String identity, Period period,
                         String subjectName,String subjectId) {
        super(schoolId,name, identity, period);
        this.subjectId = subjectId;
        this.subjectName = subjectName;
    }

    public String subjectId() {
        return subjectId;
    }

    public String subjectName() {
        return subjectName;
    }
}