package com.easytnt.mock.exam;

import com.easytnt.commons.domain.Identity;
import com.easytnt.mock.AbstractMock;
import com.easytnt.mock.IdMocker;
import com.easytnt.share.domain.id.IdPrefixes;
import com.easytnt.share.domain.id.exam.ProjectId;

/**
 * @author Liguiqing
 * @since V3.0
 */

public class ProjectMock extends AbstractMock {
    private ProjectId[] ids  = new ProjectId[]{ new ProjectId(IdMocker.genId(IdPrefixes.ProjectIdPrefix))};

    private String creatorId = IdMocker.genId(IdPrefixes.PersonIdPrefix);

    @Override
    public int order(){
        return 1;
    }

    @Override
    public Identity[] ids() {
        return ids;
    }

    @Override
    protected String table() {
        return "ps_marking_project";
    }

    @Override
    protected String getIdField() {
        return "project_id";
    }

    @Override
    protected String getFields() {
        return "project_id,creator_id,manager_ids,name,status,is_del";
    }

    @Override
    protected Object[] getValue(String key) {
        switch (key){
            case "project_id": return new String[]{ids[0].id()};
            case "creator_id": return new String[]{creatorId};
            case "manager_ids": return new String[]{creatorId};
            case "name": return new String[]{"Mock Project"};
            case "status": return new Integer[]{1};
            case "is_del": return new Integer[]{0};
            default: return new Object[]{null};
        }
    }

}