/*
 * Copyright (c) 2016,2017, easytnt All Rights Reserved. 深圳市易考试乐学测评有限公司 版权所有.
 */

package com.easytnt.ts.domain.model.school;


import com.easytnt.commons.domain.AbstractId;

/**
 * 学校唯一标识
 *
 * @author Liguiqing
 * @since V3.0
 */

public class SchoolId extends AbstractId {
    public SchoolId(String id){
        super(id);
    }

    protected  SchoolId(){
        super();
    }
}