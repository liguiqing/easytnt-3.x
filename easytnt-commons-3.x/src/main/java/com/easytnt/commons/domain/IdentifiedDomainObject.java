/*
 * Copyright (c) 2016,2017, easytnt All Rights Reserved. 深圳市易考试乐学测评有限公司 版权所有.
 */

package com.easytnt.commons.domain;

import java.io.Serializable;

/**
 * 在持久化映射时有主键的对象超类
 *
 * @author Liguiqing
 * @since V3.0
 */

public abstract class IdentifiedDomainObject implements Serializable {
    private static final long serialVersionUID = 1L;

    //持久化ID
    private long id;

    protected IdentifiedDomainObject() {
        super();

        this.setId(-1);
    }

    protected long getId() {
        return this.id;
    }

    protected void setId(long anId) {
        this.id = anId;
    }
}