/*
 * Copyright (c) 2016,2017, easytnt All Rights Reserved. 深圳市易考试乐学测评有限公司 版权所有.
 */

package com.easytnt.ts.domain.model.school.student;

import com.easytnt.commons.domain.Entity;
import com.easytnt.ts.domain.model.school.Course;
import com.easytnt.ts.domain.model.school.Grade;
import com.easytnt.ts.domain.model.school.SchoolId;
import com.easytnt.ts.domain.model.school.clazz.ClazzId;
import com.easytnt.ts.domain.model.school.term.TermId;
import com.google.common.base.Objects;

import java.util.Date;

/**
 * 学生学习情况
 *
 * @author Liguiqing
 * @since V3.0
 */

public class Study extends Entity {

    private StudentId studentId;

    private SchoolId schoolId;

    private ClazzId clazzId;

    private Grade grade;

    private TermId termId;

    private Date starts;

    private Date ends;

    private Course course;

    public Study(StudentId studentId, SchoolId schoolId, ClazzId clazzId, Grade grade,
                 TermId termId, Course course, Date starts, Date ends) {
        this.studentId = studentId;
        this.schoolId = schoolId;
        this.clazzId = clazzId;
        this.grade = grade;
        this.termId = termId;
        this.starts = starts;
        this.ends = ends;
        this.course = course;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Study study = (Study) o;
        return Objects.equal(studentId, study.studentId) &&
                Objects.equal(clazzId, study.clazzId) &&
                Objects.equal(termId, study.termId) &&
                Objects.equal(course, study.course);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(studentId, clazzId, termId, course);
    }

    public void changeEnds(Date ends){
        this.ends = ends;
    }


}