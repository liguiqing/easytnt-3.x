package com.easytnt.mock.exam;

import com.easytnt.commons.domain.Identity;
import com.easytnt.commons.util.DateUtilWrapper;
import com.easytnt.mock.AbstractMock;
import com.easytnt.mock.IdMocker;
import com.easytnt.mock.Mock;
import com.easytnt.share.domain.id.IdPrefixes;
import com.easytnt.share.domain.id.exam.ExamId;
import com.easytnt.share.domain.id.exam.ProjectId;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.zip.DataFormatException;

/**
 * @author Liguiqing
 * @since V3.0
 */

public class ExamMock extends AbstractMock {
    private ExamId[] ids =new ExamId[]{ new ExamId(IdMocker.genId(IdPrefixes.ExamIdPrefix))};
    private String creatorId = IdMocker.genId(IdPrefixes.PersonIdPrefix);
    private String orgId = IdMocker.genId(IdPrefixes.TargetOrgIdPrefix);
    private ProjectMock project;

    @Override
    public int order(){
        return 2;
    }

    @Override
    public Identity[] ids() {
        return ids;
    }

    public void userMocks(List<Mock> mocks){
        for(Mock mock:mocks){
            if(mock instanceof  ProjectMock){
                this.project = (ProjectMock) mock;
                break;
            }
        }
    }

    @Override
    protected String table() {
        return "ps_exam";
    }

    @Override
    protected String getIdField() {
        return "project_id";
    }

    protected Object[] getValue(String key){
        switch (key){
            case "exam_id": return new String[]{ids[0].id()};
            case "project_id": return new String[]{project.ids()[0].id()};
            case "target_org_id": return new String[]{orgId};
            case "creator_org_id": return new String[]{orgId};
            case "target_org_type": return new Integer[]{1};
            case "creator_id": return new String[]{creatorId};
            case "name": return new String[]{"Mock Exam"};
            case "grade_name": return new String[]{"高一"};
            case "study_starts_year": return new Integer[]{2018};
            case "study_ends_year": return new Integer[]{2019};
            case "starts": return new Date[]{DateUtilWrapper.today()};
            case "ends": return new Date[]{DateUtilWrapper.tomorrow()};
            case "status": return new Integer[]{1};
            case "catagory": return new Integer[]{1};
            case "scope": return new Integer[]{1};
            case "create_time": return new Date[]{DateUtilWrapper.now()};
            case "last_update_time": return new Date[]{DateUtilWrapper.now()};
            case "last_operator_id": return new String[]{creatorId};
            case "last_operator_name": return new String[]{"Mock Mgr"};
            case "is_del": return new Integer[]{0};
            default: return new Object[]{null};
        }
    }

    @Override
    protected String getFields() {
        return "exam_id,project_id,target_org_id,creator_org_id,target_org_type,creator_id,name," +
                    "grade_name,study_starts_year,study_ends_year,starts,ends,status,catagory,scope,create_time," +
                    "last_update_time,last_operator_id,last_operator_name,is_del";
    }

}