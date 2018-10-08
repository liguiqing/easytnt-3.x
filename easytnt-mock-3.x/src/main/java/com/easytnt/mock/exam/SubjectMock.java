package com.easytnt.mock.exam;

import com.easytnt.commons.util.DateUtilWrapper;
import com.easytnt.mock.AbstractMock;
import com.easytnt.mock.IdMocker;
import com.easytnt.share.domain.id.IdPrefixes;
import org.springframework.stereotype.Component;

/**
 * @author Liguiqing
 * @since V3.0
 */
@Component
public class SubjectMock extends AbstractMock {
    private String[] ids = new String[]{
            IdMocker.genId(IdPrefixes.SubjectIdPrefix,"1"),
            IdMocker.genId(IdPrefixes.SubjectIdPrefix,"2"),
            IdMocker.genId(IdPrefixes.SubjectIdPrefix,"3")};

    private String creatorId = IdMocker.genId(IdPrefixes.PersonIdPrefix);

    @Override
    public int order(){
        return 11;
    }

    public String[] ids(){
        return ids;
    }

    @Override
    public String table() {
        return "ps_subject";
    }

    @Override
    public String getIdField() {
        return "subject_id";
    }

    public Object[] getMockValue(String key){
        switch (key){
            case "subject_id": return this.ids;
            case "exam_id": return this.repeator.repeatOf(this.size(),this.getExam().getId(0));
            case "target_subject_id": return this.ids;
            case "target_subject_name": return this.repeator.repeatOfGroup(1,names());
            case "creator_id": return this.repeator.repeatOf(this.size(),creatorId);
            case "name": return this.repeator.repeatOfGroup(1,names());
            case "sheets": return this.repeator.repeatOf(this.size(),1);
            case "score": return new Float[]{150.00f,150.00f,150.00f};
            case "kg_score": return new Float[]{50.00f,60.00f,90.00f};
            case "zg_score": return new Float[]{100.00f,90.00f,60.00f};
            case "status": return this.repeator.repeatOf(this.size(),2);
            case "mark_way": return this.repeator.repeatOf(this.size(),1);
            case "last_update_time": return this.repeator.repeatOf(this.size(),DateUtilWrapper.now());
            case "last_operator_id": return this.repeator.repeatOf(this.size(),creatorId);
            case "last_operator_name": return this.repeator.repeatOf(this.size(),"唐伯虎");
            case "is_del": return this.repeator.repeatOf(this.size(),0);
            default: return this.repeator.repeatOf(this.size(),null);
        }
    }

    public String[] names(){
        return new String[]{"语文", "数学", "英语"};
    }

    @Override
    public int size(){
        return 3;
    }
}