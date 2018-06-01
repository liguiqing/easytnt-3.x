/*
 * Copyright (c) 2016,2018, easytnt All Rights Reserved. 深圳市易考试乐学测评有限公司 版权所有.
 */

package com.easytnt.exam.domain.exam;

import com.easytnt.commons.domain.AbstractId;
import com.easytnt.commons.domain.Identities;

/**
 * @author Liguiqing
 * @since V3.0
 */

public class TargetOrgId extends AbstractId {

    public TargetOrgId(String anId) {
        super(anId);
    }

    public TargetOrgId() {
        this(Identities.genIdDNone("TOG"));
    }

}