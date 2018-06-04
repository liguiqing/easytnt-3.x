/*
 * Copyright (c) 2016,2018, easytnt All Rights Reserved. 深圳市易考试乐学测评有限公司 版权所有.
 */

package com.easytnt.share.domain.id.exam;

import com.easytnt.commons.domain.AbstractId;
import com.easytnt.commons.domain.Identities;

import static com.easytnt.share.domain.id.IdPrefixes.ProjectIdPrefix;

/**
 * 阅卷项目唯一标识
 *
 * @author Liguiqing
 * @since V3.0
 */

public class ProjectId extends AbstractId {

    public ProjectId(String anId) {
        super(anId);
    }

    public ProjectId() {
        super(Identities.genIdNone(ProjectIdPrefix));
    }


}