/*
 * Copyright (c) 2016,2017, easytnt All Rights Reserved. 深圳市易考试乐学测评有限公司 版权所有.
 */

package com.easytnt.ts.domain.model.school;

import com.easytnt.commons.domain.ValueObject;
import com.google.common.base.Objects;

/**
 * 年级
 *
 * @author Liguiqing
 * @since V3.0
 */

public class Grade extends ValueObject {

    private String name;

    private GradeLevel seq;

    private StudyYear year;


    public Grade(String name, GradeLevel seq, StudyYear year) {
        this.name = name;
        this.seq = seq;
        this.year = year;
    }

    public Grade next(GradeNameGenerateStrategy nameGenerateStrategy){
        StudyYear year = this.year.nextYear();
        GradeLevel level = this.seq.next();
        String name = nameGenerateStrategy.genGradeName(level);
        return new Grade(name, level, year);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Grade grade = (Grade) o;
        return seq == grade.seq &&
                Objects.equal(year, grade.year);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(seq, year);
    }

    public String name() {
        return name;
    }

    public GradeLevel seq() {
        return seq;
    }

    public StudyYear year() {
        return year;
    }
}