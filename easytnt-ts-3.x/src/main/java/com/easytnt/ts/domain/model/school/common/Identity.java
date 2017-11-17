/*
 * Copyright (c) 2016,2017, easytnt All Rights Reserved. 深圳市易考试乐学测评有限公司 版权所有.
 */

package com.easytnt.ts.domain.model.school.common;

import com.easytnt.commons.domain.IdentifiedValueObject;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

/**
 * 人员身份标识，如学籍号，考号，身份证号
 *
 * @author Liguiqing
 * @since V3.0
 */

public class Identity extends IdentifiedValueObject{
    private IdentityType type;

    private String value;

    public Identity(IdentityType type, String value) {
        this.type = type;
        this.value = value;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.type, this.value);
    }

    @Override
    public boolean equals(Object o) {
        if (o != null && o instanceof Identity) {
            Identity that = (Identity) o;
            return Objects.equal(this.type, that.type) && Objects.equal(this.value, that.value);
        }
        return false;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this).add("type", this.type).add("value", this.value).toString();
    }

    public IdentityType type() {
        return type;
    }

    public String value() {
        return value;
    }
}