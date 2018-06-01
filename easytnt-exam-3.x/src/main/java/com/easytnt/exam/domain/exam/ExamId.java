/*
 * Copyright (c) 2016,2018, easytnt All Rights Reserved. 深圳市易考试乐学测评有限公司 版权所有.
 */

package com.easytnt.exam.domain.exam;

import com.easytnt.commons.domain.AbstractId;
import com.easytnt.commons.domain.Identities;

/**
 * 阅卷考试唯一标识
 *
 * @author Liguiqing
 * @since V3.0
 */

public class ExamId extends AbstractId {
    private static String prefix = "EXM";

    public ExamId(String anId) {
        super(anId);
    }

    public ExamId() {
        super(Identities.genIdDNone(prefix));
    }

    public static  String prefix(){
        return prefix;
    }
}