/*
 * Copyright (c) 2016,2017, easytnt All Rights Reserved. 深圳市易考试乐学测评有限公司 版权所有.
 */

package com.easytnt.ts.application.study;

import com.easytnt.ts.domain.model.school.SchoolId;
import com.easytnt.ts.domain.model.school.clazz.ClazzId;
import com.easytnt.ts.domain.model.school.student.StudentId;
import com.easytnt.ts.domain.model.school.term.TermId;

/**
 * @author Liguiqing
 * @since V3.0
 */

public class JoinClazzCommand {

    private StudentId stdudentId;

    private SchoolId schoolId;

    private ClazzId clazzId;

    private TermId termId;

    public JoinClazzCommand(StudentId stdudentId, SchoolId schoolId, ClazzId clazzId, TermId termId) {
        this.stdudentId = stdudentId;
        this.schoolId = schoolId;
        this.clazzId = clazzId;
        this.termId = termId;
    }

    public JoinClazzCommand() {
    }

    public StudentId getStdudentId() {
        return stdudentId;
    }

    public void setStdudentId(StudentId stdudentId) {
        this.stdudentId = stdudentId;
    }

    public SchoolId getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(SchoolId schoolId) {
        this.schoolId = schoolId;
    }

    public ClazzId getClazzId() {
        return clazzId;
    }

    public void setClazzId(ClazzId clazzId) {
        this.clazzId = clazzId;
    }

    public TermId getTermId() {
        return termId;
    }

    public void setTermId(TermId termId) {
        this.termId = termId;
    }
}