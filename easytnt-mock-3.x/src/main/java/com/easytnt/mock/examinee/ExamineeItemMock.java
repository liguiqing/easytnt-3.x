package com.easytnt.mock.examinee;

import com.easytnt.commons.spring.SpringContextUtil;
import com.easytnt.mock.AbstractMock;
import com.easytnt.mock.mark.item.MarkItemMock;
import org.springframework.stereotype.Component;

import java.util.stream.Stream;

/**
 * @author Liguiqing
 * @since V3.0
 */
@Component
public class ExamineeItemMock extends AbstractMock {

    @Override
    public String table() {
        return "ps_examinee_item";
    }

    @Override
    public String getIdField() {
        return "examinee_item_id";
    }

    @Override
    public Object[] getMockValue(String key) {
        switch(key){
            case "examinee_item_id": return ids();
            case "exam_id": return this.repeator.repeatOf(this.size(),this.getExam().ids()[0]);
            case "subject_id": return valuesFrom("subject_id",1);
            case "mark_item_id": return valuesFrom("mark_item_id",1);
            case "batch_id": return valuesFrom("batch_id",0);
            case "crypt_code": return  valuesFrom("crypt_code",0);
            case "sheet_key": return valuesFrom("sheet_key",0);
            case "purpose": return valuesFrom("purpose",1);
            case "is_del": return this.repeator.repeatOf(this.size(),0);
            default: return this.repeator.repeatOf(this.size(),null);
        }
    }

    private Object[] valuesFrom(String key,int from){
        ExamineeSheetMock examineeSheet = SpringContextUtil.getBean(ExamineeSheetMock.class);
        MarkItemMock markItem = SpringContextUtil.getBean(MarkItemMock.class);
        Object[] keyValues = null;
        if(from == 1)
            keyValues = markItem.getValues(key);
        else
            keyValues = examineeSheet.getValues(key);
        Object[] markables = examineeSheet.getValues("markable");
        Object[] subjectIds = examineeSheet.getValues("subject_id");
        int length = this.size();
        Object[] values = new Object[length];

        Object[] markItemSubjectIds = markItem.getValues("subject_id");
        int length2 = markables.length;
        int length3 = markItemSubjectIds.length;
        int k = 0;
        for(int i=0;i<length2;i++){
            if((int)markables[i] == 1){
                for(int j=0;j<length3;j++){
                    if(markItemSubjectIds[j].equals(subjectIds[i])){
                        if(from == 1){
                            values[k++] = keyValues[j];
                        }else{
                            values[k++] = keyValues[i];
                        }
                    }
                }

            }
        }
        return values;
    }

    public Object[] valuesOf(String key,int purpose){
        Object[] purposes = getValues("purpose");
        Object[] os = new Object[sizeOf(purpose)];
        Object[] values = getValues(key);
        int length = purposes.length;
        int k = 0;
        for(int i=0;i<length;i++){
            if((int)purposes[i] == purpose){
                os[k++] = values[i];
            }
        }
        return os;//Arrays.stream(os).filter(v->v!=null).toArray();
    }

    public int sizeOf(int purpose){
        Object[] purposes = getValues("purpose");
        return Stream.of(purposes).filter(p->{return (int)p==purpose;}).toArray().length;
    }


    @Override
    public int size(){
        MarkItemMock markItem = SpringContextUtil.getBean(MarkItemMock.class);
        ExamineeSheetMock examineeSheet = SpringContextUtil.getBean(ExamineeSheetMock.class);
        String[] subjectIds = this.getSubject().ids();
        int count = 0;
        for(int i = 0;i<subjectIds.length;i++){
            count += examineeSheet.countMarkablesOf(subjectIds[i]) * markItem.sizeOf(subjectIds[i]);
        }

        return count;
    }


    @Override
    public String[] ids() {
        String[] suffixes = this.fromTo(1, this.size());
        String[] ids = genIds("EIM","",suffixes);
        return ids;
    }

    @Override
    public int order() {
        return 36;
    }
}