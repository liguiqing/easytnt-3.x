/*
 * Copyright (c) 2016,2018, easytnt All Rights Reserved. 深圳市易考试乐学测评有限公司 版权所有.
 */

package com.easytnt.exam.domain.exam;

import com.easytnt.commons.domain.DomainEventPublisher;
import com.easytnt.commons.domain.Entity;
import com.easytnt.share.domain.id.exam.ProjectId;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

/**
 * 阅卷项目
 *
 * @author Liguiqing
 * @since V3.0
 */

public class MarkingProject extends Entity {

    private ProjectId projectId;

    private String creatorId;

    private String name;

    private String managerIds;

    private ProjectStatus status;

    public MarkingProject(ProjectId projectId, String creatorId, String name) {
        this.projectId = projectId;
        this.creatorId = creatorId;
        this.name = name;
        this.status = ProjectStatus.Useable;
    }

    public void addManager(String managerId){
        this.managerIds = managerId + ";";
    }

    public void over(){
        this.status = ProjectStatus.Finish;
        DomainEventPublisher.instance().publish(new MarkingProjectFinished(this.projectId));
    }

    public void cancel(){
        this.status = ProjectStatus.Cancel;
        DomainEventPublisher.instance().publish(new MarkingProjectCanceled(this.projectId));
    }

    public ProjectId projectId() {
        return projectId;
    }

    public String creatorId() {
        return creatorId;
    }

    public String name() {
        return name;
    }

    public String managerIds() {
        return managerIds;
    }

    public ProjectStatus status() {
        return status;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("name", name)
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MarkingProject that = (MarkingProject) o;
        return Objects.equal(projectId, that.projectId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(projectId);
    }
}