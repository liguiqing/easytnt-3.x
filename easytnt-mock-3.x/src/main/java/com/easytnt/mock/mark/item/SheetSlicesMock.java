package com.easytnt.mock.mark.item;

import com.easytnt.mock.AbstractMock;
import org.springframework.stereotype.Component;

/**
 * @author Liguiqing
 * @since V3.0
 */
@Component
public class SheetSlicesMock extends AbstractMock {


    @Override
    public String table() {
        return "ps_sheet_slices";
    }

    @Override
    public String getIdField() {
        return "sheet_slices_id";
    }

    @Override
    public Object[] getMockValue(String key) {
        switch(key){
            case "sheet_slices_id": return ids();
            case "answer_sheet_id": return aswerSheetIds();
            case "page": return this.repeator.repeatOfGroup(this.size(),1);
            case "sheet": return this.repeator.repeatOfGroup(this.size(),1);
            case "x": return this.repeator.repeatOfGroup(this.size(),100);
            case "y": return this.repeator.repeatOfGroup(this.size(),100);
            case "w": return this.repeator.repeatOfGroup(this.size(),100);
            case "h": return this.repeator.repeatOfGroup(this.size(),100);
            case "roate": return this.repeator.repeatOfGroupOfEachLoopMaxLt(this.size(),2,15,90,270);
            case "purpose": return new Object[]{2,3,1,1,1,2,3,1,1,1,2,3,1,1,1};
            case "repeatable": return this.repeator.repeatOfGroup(this.size(),0);
            case "is_del": return this.repeator.repeatOfGroup(this.size(),1);
            default: return this.repeator.repeatOf(this.size(),null);
        }
    }

    private String[] aswerSheetIds(){
        return  this.repeator.repeatOfGroupOfEach(1,5,this.getAnswerSheet().ids());
    }

    @Override
    public int size(){
        return 15;
    }

    @Override
    public String[] ids() {
        String[] suffixes = this.fromTo(1, this.size());
        String[] ids = genIds("SSL","",suffixes);
        return ids;
    }

    @Override
    public int order() {
        return 50;
    }
}