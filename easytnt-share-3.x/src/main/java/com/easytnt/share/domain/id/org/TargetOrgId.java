/*
 * Copyright (c) 2016,2018, easytnt All Rights Reserved. 深圳市易考试乐学测评有限公司 版权所有.
 */

package com.easytnt.share.domain.id.org;

import com.easytnt.commons.domain.AbstractId;
import com.easytnt.commons.domain.Identities;

import static com.easytnt.share.domain.id.IdPrefixes.TargetOrgIdPrefix;

/**
 * @author Liguiqing
 * @since V3.0
 */

public class TargetOrgId extends AbstractId {

    public TargetOrgId(String anId) {
        super(anId);
    }

    public TargetOrgId() {
        this(Identities.genIdNone(TargetOrgIdPrefix));
    }

}