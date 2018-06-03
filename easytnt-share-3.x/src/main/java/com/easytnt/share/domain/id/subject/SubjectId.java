/*
 * Copyright (c) 2016,2018, easytnt All Rights Reserved. 深圳市易考试乐学测评有限公司 版权所有.
 */

package com.easytnt.share.domain.id.subject;

import com.easytnt.commons.domain.AbstractId;
import com.easytnt.commons.domain.Identities;

/**
 * 阅卷考试科目唯一标识
 *
 * @author Liguiqing
 * @since V3.0
 */

public class SubjectId extends AbstractId {
    private static String prefix = "SUB";

    public SubjectId(String anId) {
        super(anId);
    }

    public SubjectId() {
        super(Identities.genIdDNone(prefix));
    }

    public static  String prefix(){
        return prefix;
    }
}