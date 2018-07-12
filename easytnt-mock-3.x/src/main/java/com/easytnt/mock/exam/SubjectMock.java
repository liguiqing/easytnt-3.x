package com.easytnt.mock.exam;

import com.easytnt.commons.util.DateUtilWrapper;
import com.easytnt.mock.AbstractMock;
import com.easytnt.mock.IdMocker;
import com.easytnt.mock.Mock;
import com.easytnt.share.domain.id.IdPrefixes;
import com.easytnt.share.domain.id.subject.SubjectId;

import java.util.Date;
import java.util.List;

/**
 * @author Liguiqing
 * @since V3.0
 */

public class SubjectMock extends AbstractMock {
    private SubjectId[] ids = new SubjectId[]{new SubjectId(IdMocker.genId(IdPrefixes.SubjectIdPrefix,"1")),
            new SubjectId(IdMocker.genId(IdPrefixes.SubjectIdPrefix,"2")),
            new SubjectId(IdMocker.genId(IdPrefixes.SubjectIdPrefix,"3"))};

    private String creatorId = IdMocker.genId(IdPrefixes.PersonIdPrefix);

    private ExamMock exam;

    @Override
    public int order(){
        return 3;
    }

    public SubjectId[] ids(){
        return ids;
    }

    public void userMocks(List<Mock> mocks){
        for(Mock mock:mocks){
            if(mock instanceof  ExamMock){
                this.exam = (ExamMock) mock;
                break;
            }
        }
    }

    @Override
    protected String table() {
        return "ps_subject";
    }

    @Override
    protected String getIdField() {
        return "subject_id";
    }

    protected Object[] getValue(String key){
        switch (key){
            case "subject_id": return new String[]{ids[0].id(),ids[1].id(),ids[2].id()};
            case "exam_id": return new String[]{exam.getId(0),exam.getId(0),exam.getId(0)};
            case "target_subject_id": return new String[]{ids[0].id(),ids[1].id(),ids[2].id()};
            case "target_subject_name": return new String[]{"语文","数学","英语"};
            case "creator_id": return new String[]{creatorId,creatorId,creatorId};
            case "name": return  new String[]{"语文","数学","英语"};
            case "sheets": return new Integer[]{1,1,1};
            case "score": return new Float[]{150.00f,150.00f,150.00f};
            case "kg_score": return new Float[]{50.00f,60.00f,90.00f};
            case "zg_score": return new Float[]{100.00f,90.00f,60.00f};
            case "status": return new Integer[]{2,2,2};
            case "mark_way": return new Integer[]{1,1,1};
            case "last_update_time": return new Date[]{DateUtilWrapper.now(),DateUtilWrapper.now(),DateUtilWrapper.now()};
            case "last_operator_id": return new String[]{creatorId,creatorId,creatorId};
            case "last_operator_name": return new String[]{"唐伯虎","唐伯虎","唐伯虎"};
            case "is_del": return new Integer[]{0,0,0};
            default: return new Object[]{null,null,null};
        }
    }

    @Override
    protected String getFields() {
        return "subject_id,exam_id,target_subject_id,target_subject_name,creator_id,name,sheets,score," +
                "kg_score,zg_score,status,mark_way,last_update_time,last_operator_id,last_operator_name,is_del";
    }

}