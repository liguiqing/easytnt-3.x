/*
 * Copyright (c) 2016,2017, easytnt All Rights Reserved. 深圳市易考试乐学测评有限公司 版权所有.
 */

package com.easytnt.ts.domain.model.school;

import com.easytnt.commons.domain.AbstractId;

/**
 * 租户ID,一个学校属于一租房管理
 *
 * @author Liguiqing
 * @since V3.0
 */

public class TenantId extends AbstractId {
    public TenantId(String id){
        super(id);
    }

    protected  TenantId(){
        super();
    }
}