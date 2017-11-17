/*
 * Copyright (c) 2016,2017, easytnt All Rights Reserved. 深圳市易考试乐学测评有限公司 版权所有.
 */

package com.easytnt.ts.domain.model.school;

import com.easytnt.commons.domain.ValueObject;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

/**
 * 课程
 *
 * @author Liguiqing
 * @since V3.0
 */

public class Course extends ValueObject {

    private String name;

    private String subjectId;

    public Course(String name,String subjectId){
        this.subjectId = subjectId;
        this.name = name;
    }

    public String name() {
        return name;
    }

    public String subjectId() {
        return subjectId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return   Objects.equal(subjectId, course.subjectId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode( subjectId);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("name", name)
                .add("subjectId", subjectId)
                .toString();
    }
}