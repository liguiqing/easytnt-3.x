/*
 * Copyright (c) 2016,2017, easytnt All Rights Reserved. 深圳市易考试乐学测评有限公司 版权所有.
 */

package com.easytnt.ts.domain.model.school.student;

import com.easytnt.commons.AssertionConcerns;
import com.easytnt.commons.domain.DomainEventPublisher;
import com.easytnt.commons.domain.Entity;
import com.easytnt.ts.domain.model.school.Course;
import com.easytnt.ts.domain.model.school.Grade;
import com.easytnt.ts.domain.model.school.SchoolId;
import com.easytnt.ts.domain.model.school.clazz.Clazz;
import com.easytnt.ts.domain.model.school.clazz.ClazzId;
import com.easytnt.ts.domain.model.school.common.Gender;
import com.easytnt.ts.domain.model.school.common.Identity;
import com.easytnt.ts.domain.model.school.common.Person;
import com.easytnt.ts.domain.model.school.term.Term;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.collect.Sets;

import java.util.Date;
import java.util.Set;

/**
 * 学生
 *
 * @author Liguiqing
 * @since V3.0
 */

public class Student extends Entity {

    private SchoolId schoolId;

    private StudentId studentId;

    private Person person;

    private Set<Identity> ids;

    private Set<Study> studies;

    private Set<BelongToClazz> belongTos;

    public Student(SchoolId schoolId,StudentId studentId, String name,String identity) {
        this(schoolId, studentId, name, identity, Gender.Unknow);
    }

    public Student(SchoolId schoolId,StudentId studentId, String name,String identity,Gender gender) {
        AssertionConcerns.assertArgumentNotNull(schoolId, "请提供学生所在学校");
        AssertionConcerns.assertArgumentNotNull(studentId, "请提供学生唯一标识");
        AssertionConcerns.assertArgumentNotNull(name, "请提供学生姓名");
        AssertionConcerns.assertArgumentLength(name, 1, 32, "学生姓名为1到32个字符");

        this.studentId = studentId;
        this.person = new Person(name,identity,gender);
        this.ids = Sets.newHashSet();
        this.studies = Sets.newHashSet();
        this.belongTos = Sets.newHashSet();
    }

    public void changeManagedClazz(Clazz clazz, Date starts, Date ends){
        BelongToClazz belongTo = new BelongToClazz(clazz, this.studentId, starts, ends);
        this.belongTos.add(belongTo);
    }

    public void addId(Identity sid) {
        AssertionConcerns.assertArgumentNotNull(sid, "请提供学生唯一标识");
        this.ids.add(sid);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(studentId);
    }

    @Override
    public boolean equals(Object o) {
        if (o != null && o instanceof Student) {
            Student that = (Student) o;
            return Objects.equal(this.studentId, that.studentId);
        }
        return false;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this).add("name", this.person).add("id", this.studentId).toString();
    }

}