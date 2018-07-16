package com.easytnt.mock.answersheet;

import com.easytnt.commons.spring.SpringContextUtil;
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
public class AnswerSheetPageMock extends AbstractMock {

    @Override
    public int size(){
        return 2;
    }

    @Override
    protected String table() {
        return "ps_answer_sheet_page";
    }

    @Override
    protected String getIdField() {
        return "answer_sheet_id";
    }

    @Override
    protected Object[] getValue(String key) {
        switch (key){
            case "answer_sheet_id": return repeatOfGroup(this.size(),this.ids());
            case "paper_type": return repeatOfGroup(this.realSize(),"A3");
            case "sheet": return repeatOfGroup(this.realSize(),1);
            case "page": return repeatOfGroup(this.ids().length,1,2);
            case "w": return repeatOfGroup(this.realSize(),2308);
            case "h": return repeatOfGroup(this.realSize(),3312);
            case "img_url": return repeatOf(this.realSize(),"");
            case "roate": return repeatOfGroup(this.ids().length,90,270);
            case "features": return repeatOf(this.realSize(),"");
            case "exam_number_features": return repeatOf(this.realSize(),"");
            case "kg_features": return repeatOf(this.realSize(),"");
            case "zg_features": return repeatOf(this.realSize(),"");
            case "zg_optional_features": return repeatOf(this.realSize(),"");
            case "last_update_time": return repeatOf(this.realSize(),DateUtilWrapper.now());
            case "last_operator_id": return repeatOf(this.realSize(),IdMocker.genId(IdPrefixes.PersonIdPrefix));
            case "last_operator_name": return repeatOf(this.realSize(),"唐伯虎");
            case "is_del": return repeatOf(this.realSize(),0);
            default: return repeatOf(this.realSize(),null);
        }
    }

    private int realSize(){
        return this.size()*this.ids().length;
    }

    @Override
    public String[] ids() {
        AnswerSheetMock answerSheet =  SpringContextUtil.getBean(AnswerSheetMock.class);
        return answerSheet.ids();
    }

    @Override
    public int order() {
        return 41;
    }

}