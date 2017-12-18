/*
 * Copyright (c) 2016,2017, easytnt All Rights Reserved. 深圳市易考试乐学测评有限公司 版权所有.
 */

package com.easytnt.ts.domain.model.school.common;

import com.easytnt.commons.lang.BusinessException;

/**
 * @author Liguiqing
 * @since V3.0
 */

public class WLTypeNotFondException extends BusinessException {

    public WLTypeNotFondException() {
    }

    public WLTypeNotFondException(String message) {
        super(message);
    }

    public WLTypeNotFondException(String message, Throwable cause) {
        super(message, cause);
    }
}