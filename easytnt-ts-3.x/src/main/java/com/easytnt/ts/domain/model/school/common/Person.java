/*
 * Copyright (c) 2016,2017, easytnt All Rights Reserved. 深圳市易考试乐学测评有限公司 版权所有.
 */

package com.easytnt.ts.domain.model.school.common;

import com.easytnt.commons.domain.ValueObject;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

/**
 * 人
 *
 * @author Liguiqing
 * @since V3.0
 */

public class Person extends ValueObject {
    private String name;

    private String identity; //唯一身份标识

    private Gender gender;

    public Person(String name, String identity) {
        this.name = name;
        this.identity = identity;
        this.gender = Gender.Unknow;
    }

    public Person(String name, String identity,Gender gender) {
        this.name = name;
        this.identity = identity;
        this.gender = gender;
    }

    public String name() {
        return name;
    }

    public String identity() {
        return identity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equal(identity, person.identity);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(identity);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("name", name)
                .add("identity", identity)
                .toString();
    }
}