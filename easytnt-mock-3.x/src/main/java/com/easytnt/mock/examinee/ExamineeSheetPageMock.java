package com.easytnt.mock.examinee;

import com.easytnt.commons.spring.SpringContextUtil;
import com.easytnt.mock.AbstractMock;
import org.springframework.stereotype.Component;

/**
 * @author Liguiqing
 * @since V3.0
 */
@Component
public class ExamineeSheetPageMock extends AbstractMock {
    @Override
    public int order() {
        return 36;
    }

    @Override
    public String table() {
        return "ps_examinee_sheet_page";
    }

    @Override
    public String getIdField() {
        return "sheet_id";
    }

    @Override
    public Object[] getMockValue(String key) {
        switch(key){
            case "sheet_id": return ids();
            case "sheet": return this.repeator.repeatOfGroup(this.realSize(),1);
            case "page": return this.repeator.repeatOfGroupOfEachLoopMaxLt(this.size(),2,this.realSize(),1,2);
            case "img_url": return this.repeator.repeatOf(this.realSize(),"");
            case "roate": return this.repeator.repeatOfGroupOfEachLoopMaxLt(this.size(),2,this.realSize(),90,270);
            case "is_del": return this.repeator.repeatOf(this.realSize(),0);
            default: return this.repeator.repeatOf(this.realSize(),null);
        }
    }

    public int size(){
        return getExamineeSheet().realSize();
    }

    public int realSize(){
        return getExamineeSheet().realSize()*2;
    }

    @Override
    public String[] ids() {
        return this.repeator.repeatOfGroupOfEachMaxLt(1,2,this.realSize(),this.getExamineeSheet().ids());
    }

    public ExamineeSheetMock getExamineeSheet(){
        return SpringContextUtil.getBean(ExamineeSheetMock.class);
    }
}