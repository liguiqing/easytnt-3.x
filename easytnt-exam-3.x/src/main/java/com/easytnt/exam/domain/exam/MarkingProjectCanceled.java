/*
 * Copyright (c) 2016,2018, easytnt All Rights Reserved. 深圳市易考试乐学测评有限公司 版权所有.
 */

package com.easytnt.exam.domain.exam;

import com.easytnt.commons.domain.AbstractDomainEvent;

/**
 * @author Liguiqing
 * @since V3.0
 */

public class MarkingProjectCanceled extends AbstractDomainEvent {

    private ProjectId projectId;

    public MarkingProjectCanceled(ProjectId projectId) {
        super();
        this.projectId = projectId;
    }

    public ProjectId projectId() {
        return projectId;
    }
}