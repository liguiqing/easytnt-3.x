/*
 * Copyright (c) 2016,2018, easytnt All Rights Reserved. 深圳市易考试乐学测评有限公司 版权所有.
 */

package com.easytnt.exam.project;

import com.easytnt.commons.domain.AbstractDomainEvent;
import com.easytnt.share.domain.id.exam.ProjectId;

/**
 * @author Liguiqing
 * @since V3.0
 */

public class MarkingProjectFinished extends AbstractDomainEvent {

    private ProjectId projectId;

    public MarkingProjectFinished(ProjectId projectId) {
        super();
        this.projectId = projectId;
    }

    public ProjectId projectId() {
        return projectId;
    }
}