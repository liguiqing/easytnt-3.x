package com.easytnt.mock.exam;

import com.easytnt.commons.spring.SpringContextUtil;
import com.easytnt.commons.util.DateUtilWrapper;
import com.easytnt.mock.AbstractMock;
import com.easytnt.mock.IdMocker;
import com.easytnt.share.domain.id.IdPrefixes;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author Liguiqing
 * @since V3.0
 */
@Component
public class ExamMock extends AbstractMock {
    private String[] ids =new String[]{ IdMocker.genId(IdPrefixes.ExamIdPrefix)};

    private String creatorId = IdMocker.genId(IdPrefixes.PersonIdPrefix);

    private String orgId = IdMocker.genId(IdPrefixes.TargetOrgIdPrefix);

    @Override
    public int order(){
        return 10;
    }

    @Override
    public String[] ids() {
        return ids;
    }


    @Override
    public String table() {
        return "ps_exam";
    }

    @Override
    public String getIdField() {
        return "exam_id";
    }

    public Object[] getMockValue(String key){
        switch (key){
            case "exam_id": return this.ids;
            case "project_id": return new String[]{getProject().ids()[0]};
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

    private ProjectMock getProject(){
        return SpringContextUtil.getBean(ProjectMock.class);
    }

}