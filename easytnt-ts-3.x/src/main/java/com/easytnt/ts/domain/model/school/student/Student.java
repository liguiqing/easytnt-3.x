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

    private ClazzId clazzId;

    private Person person;

    private Set<Identity> ids;

    private Set<Study> studies;

    public Student(SchoolId schoolId,ClazzId clazzId,StudentId studentId, String name,String identity,Gender gender) {
        AssertionConcerns.assertArgumentNotNull(schoolId, "请提供学生所在学校");
        AssertionConcerns.assertArgumentNotNull(clazzId, "请提供学生所在班级");
        AssertionConcerns.assertArgumentNotNull(studentId, "请提供学生唯一标识");
        AssertionConcerns.assertArgumentNotNull(name, "请提供学生姓名");
        AssertionConcerns.assertArgumentLength(name, 1, 32, "学生姓名为1到32个字符");

        this.studentId = studentId;
        this.person = new Person(name,identity,gender);
        this.ids = Sets.newHashSet();
        this.studies = Sets.newHashSet();
    }

    public void changeManagedClazz(Clazz clazz){
        AssertionConcerns.assertArgumentNotNull(clazz,"请提供学生的管理班级");
        AssertionConcerns.assertArgumentTrue(clazz.canBeManaged(),"所提供班级不能管理学生");
        AssertionConcerns.assertArgumentNotEquals(clazz.clazzId(),this.clazzId,"学生不能重加入同一个班级");
        DomainEventPublisher.instance().publish(new StudentClazzChanged(this.clazzId,clazz.clazzId(),this));
        this.clazzId = clazz.clazzId();
    }

    public void addId(Identity sid) {
        AssertionConcerns.assertArgumentNotNull(sid, "请提供学生唯一标识");
        this.ids.add(sid);
    }

    public Study studyOn(Grade grade, Clazz clazz,Term term, Course course){
        AssertionConcerns.assertArgumentTrue(clazz.canBeStudyAndTeachIn(),"学生不能在非教学班级里学习");
        return  new Study(this.studentId,this.schoolId,clazz.clazzId(),grade,term.termId(),course,
                term.timeSpan().starts(),term.timeSpan().ends());
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