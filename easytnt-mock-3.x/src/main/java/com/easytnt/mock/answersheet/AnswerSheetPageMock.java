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
    public String table() {
        return "ps_answer_sheet_page";
    }

    @Override
    public String getIdField() {
        return "answer_sheet_id";
    }

    @Override
    public Object[] getMockValue(String key) {
        switch (key){
            case "answer_sheet_id": return this.repeator.repeatOfGroupOfEachMaxLt(2,2,this.realSize(),this.ids());
            case "paper_type": return this.repeator.repeatOfGroup(this.realSize(),"A3");
            case "sheet": return this.repeator.repeatOfGroup(this.realSize(),1);
            case "page": return this.repeator.repeatOfGroupOfEachLoopMaxLt(2,2,this.realSize(),1,2);
            case "w": return this.repeator.repeatOfGroup(this.realSize(),2308);
            case "h": return this.repeator.repeatOfGroup(this.realSize(),3312);
            case "img_url": return this.repeator.repeatOf(this.realSize(),"");
            case "roate": return this.repeator.repeatOfGroupOfEachLoopMaxLt(2,2,this.realSize(),90,270);
            case "features": return this.repeator.repeatOf(this.realSize(),"");
            case "exam_number_features": return this.repeator.repeatOf(this.realSize(),"");
            case "kg_features": return this.repeator.repeatOf(this.realSize(),"");
            case "zg_features": return this.repeator.repeatOf(this.realSize(),"");
            case "zg_optional_features": return this.repeator.repeatOf(this.realSize(),"");
            case "last_update_time": return this.repeator.repeatOf(this.realSize(),DateUtilWrapper.now());
            case "last_operator_id": return this.repeator.repeatOf(this.realSize(),IdMocker.genId(IdPrefixes.PersonIdPrefix));
            case "last_operator_name": return this.repeator.repeatOf(this.realSize(),"唐伯虎");
            case "is_del": return this.repeator.repeatOf(this.realSize(),0);
            default: return this.repeator.repeatOf(this.realSize(),null);
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