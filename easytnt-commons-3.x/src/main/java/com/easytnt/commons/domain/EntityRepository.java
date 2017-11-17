/*
 * Copyright (c) 2016,2017, easytnt All Rights Reserved. 深圳市易考试乐学测评有限公司 版权所有.
 */

package com.easytnt.commons.domain;

/**
 * 实体仓储
 *
 * @author Liguiqing
 * @since V3.0
 */

public interface EntityRepository<E extends Entity,ID extends AbstractId> {
    public ID nextIdentity();

    public E loadOfId(ID id);

    public void save(E e);
}