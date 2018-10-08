package com.easytnt.mock.mark.item;

import com.easytnt.mock.AbstractMock;
import com.easytnt.mock.answersheet.AnswerSheetItem;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * @author Liguiqing
 * @since V3.0
 */
@Component
public class MarkItemToSheetItemMock extends AbstractMock {

    @Override
    public String table() {
        return "ps_mark_item_to_sheet_item";
    }

    @Override
    public String getIdField() {
        return "mark_item_id";
    }

    @Override
    public Object[] getMockValue(String key) {
        switch(key){
            case "mark_item_id": return getAnswerSheetItemIds(0);
            case "answer_sheet_item_id": return getAnswerSheetItemIds(1);
            case "mark_item_score_id": return this.repeator.repeatOf(this.size(),null);
            case "is_del": return this.repeator.repeatOf(this.size(),0);
            default: return this.repeator.repeatOf(this.size(),null);
        }
    }

    private Object[] getAnswerSheetItemIds(int from){
        MarkItemMock markItem = getOtherMock(MarkItemMock.class);
        Object[] miids = markItem.ids();
        Object[] purposes = markItem.getValues("purpose");
        Object[] subjectIds = markItem.getValues("subject_id");
        AnswerSheetItem answerSheetItem = getOtherMock(AnswerSheetItem.class);
        Object[] asiIds = answerSheetItem.ids();
        Object[] asiSubjectIds = answerSheetItem.getItemsSubjects();
        Object[] catagory2 = answerSheetItem.getCatagory2();
        ArrayList<Object> asitid = Lists.newArrayList();
        ArrayList<Object> mitid = Lists.newArrayList();
        int length = catagory2.length;
        int length2 = purposes.length;
        for(int i = 0;i < length2;i++){
            int p = (int) purposes[i];
            if(p == 2){
                for(int j = 0;j<length;j++){
                    if(catagory2[j] != null && (catagory2[j].equals(1)||catagory2[j].equals(2) ||catagory2[j].equals(3))){
                        Object sbid = asiSubjectIds[j];
                        if(sbid.equals(subjectIds[i])){

                            asitid.add(asiIds[j]);
                            mitid.add(miids[i]);
                        }
                    }
                }
            }
        }
        if(from == 1)
            return asitid.toArray();
        return mitid.toArray();
    }

    @Override
    public int size() {
        return ids().length;
    }

    @Override
    public String[] ids() {
        return Arrays.stream(this.getAnswerSheetItemIds(0)).map(id->(String)id).collect(Collectors.toList()).toArray(new String[]{});
    }

    @Override
    public int order() {
        return 51;
    }
}