/*
 * Copyright (c) 2016,2017, easytnt All Rights Reserved. 深圳市易考试乐学测评有限公司 版权所有.
 */

package com.easytnt.ts.domain.model.school.study;

import com.easytnt.commons.domain.Entity;
import com.easytnt.ts.domain.model.school.Course;
import com.easytnt.ts.domain.model.school.Grade;
import com.easytnt.ts.domain.model.school.SchoolId;
import com.easytnt.ts.domain.model.school.clazz.ClazzId;
import com.easytnt.ts.domain.model.school.student.StudentId;
import com.easytnt.ts.domain.model.school.term.TermId;

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

    public void changeEnds(Date ends){
        this.ends = ends;
    }
}