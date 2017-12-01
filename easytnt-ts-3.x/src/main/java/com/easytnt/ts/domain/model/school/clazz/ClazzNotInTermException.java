/*
 * Copyright (c) 2016,2017, easytnt All Rights Reserved. 深圳市易考试乐学测评有限公司 版权所有.
 */

package com.easytnt.ts.domain.model.school.clazz;

/**
 *
 * @author Liguiqing
 * @since V3.0
 */

public class ClazzNotInTermException extends RuntimeException {


    public ClazzNotInTermException(String message) {
        super(message);
    }
}