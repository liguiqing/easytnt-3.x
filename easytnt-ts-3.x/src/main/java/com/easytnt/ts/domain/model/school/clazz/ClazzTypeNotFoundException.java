/*
 * Copyright (c) 2016,2017, easytnt All Rights Reserved. 深圳市易考试乐学测评有限公司 版权所有.
 */

package com.easytnt.ts.domain.model.school.clazz;

import com.easytnt.commons.lang.BusinessException;

/**
 * @author Liguiqing
 * @since V3.0
 */

public class ClazzTypeNotFoundException extends BusinessException {

    public ClazzTypeNotFoundException() {
    }

    public ClazzTypeNotFoundException(String message) {
        super(message);
    }

    public ClazzTypeNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ClazzTypeNotFoundException(Throwable cause) {
        super(cause);
    }
}