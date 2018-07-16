package com.easytnt.mock.answersheet;

import com.easytnt.commons.util.DateUtilWrapper;
import com.easytnt.mock.AbstractMock;
import com.easytnt.mock.IdMocker;
import com.easytnt.share.domain.id.IdPrefixes;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

/**
 * @author Liguiqing
 * @since V3.0
 */
@Component
public class AnswerSheetMock extends AbstractMock {
    private String[] ids =new String[]{
            IdMocker.genId(IdPrefixes.AnswerSheetIdPrefix,"1"),
            IdMocker.genId(IdPrefixes.AnswerSheetIdPrefix,"2"),
            IdMocker.genId(IdPrefixes.AnswerSheetIdPrefix,"3")};


    @Override
    public int size(){
        return 3;
    }

    @Override
    protected String table() {
        return "ps_answer_sheet";
    }

    @Override
    protected String getIdField() {
        return "answer_sheet_id";
    }

    @Override
    protected Object[] getValue(String key) {
        switch (key){
            case "answer_sheet_id": return this.ids;
            case "exam_id": return repeatOf(this.size(),this.getExam().ids()[0]);
            case "subject_id": return repeatOfGroup(this.size(),this.getSubject().ids());
            case "designer_id": return repeatOf(this.size(),null);
            case "paper_id": return repeatOf(this.size(),null);
            case "name": return  new String[]{"语文答题卡","数学答题卡","英语答题卡"};
            case "catagory": return repeatOf(this.size(),"");
            case "sheets": return repeatOf(this.size(),1);
            case "pages": return repeatOf(this.size(),2);
            case "ossPath": return repeatOf(this.size(),"");
            case "last_update_time": return repeatOf(this.size(),DateUtilWrapper.now());
            case "last_operator_id": return repeatOf(this.size(),IdMocker.genId(IdPrefixes.PersonIdPrefix));
            case "last_operator_name": return repeatOf(this.size(),"唐伯虎");
            case "is_del": return repeatOf(this.size(),0);
            default: return repeatOf(this.size(),null);
        }
    }

    @Override
    protected void correct(JdbcTemplate jdbc){
        jdbc.update("update ps_answer_sheet a set a.ossPath=concat('"+getOssServer()+"',a.answer_sheet_id)");
    }

    @Override
    public String[] ids() {
        return this.ids;
    }

    @Override
    public int order() {
        return 40;
    }
}