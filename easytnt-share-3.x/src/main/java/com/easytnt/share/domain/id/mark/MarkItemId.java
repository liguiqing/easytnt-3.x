/*
 * Copyright (c) 2016,2018, easytnt All Rights Reserved. 深圳市易考试乐学测评有限公司 版权所有.
 */

package com.easytnt.share.domain.id.mark;

import com.easytnt.commons.domain.AbstractId;
import com.easytnt.commons.domain.Identities;

import static com.easytnt.share.domain.id.IdPrefixes.MarkItemIdPrefix;

/**
 * @author Liguiqing
 * @since V3.0
 */

public class MarkItemId extends AbstractId {

    public MarkItemId(String anId) {
        super(anId);
    }

    public MarkItemId() {
        super(Identities.genIdNone(MarkItemIdPrefix));
    }
}