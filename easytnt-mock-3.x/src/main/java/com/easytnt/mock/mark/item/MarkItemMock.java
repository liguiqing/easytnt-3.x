package com.easytnt.mock.mark.item;

import com.easytnt.mock.AbstractMock;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.mutable.MutableInt;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Liguiqing
 * @since V3.0
 */
@Component
public class MarkItemMock extends AbstractMock {


    @Override
    public String table() {
        return "ps_mark_item";
    }

    @Override
    public String getIdField() {
        return "mark_item_id";
    }

    @Override
    public Object[] getMockValue(String key) {
        switch(key){
            case "mark_item_id": return ids();
            case "answer_sheet_id": return aswerSheetIds();
            case "exam_id": return examIds();
            case "subject_id": return subjectIds();
            case "name": return getItemNames();
            case "seq": return new Object[]{1,2,3,4,5,1,2,3,4,5,1,2,3,4,5};
            case "score": return new Object[]{null,null,20d,30d,60d,null,null,20d,60d,30d,null,null,10d,20d,30d};
            case "purpose": return new Object[]{2,3,1,1,1,2,3,1,1,1,2,3,1,1,1};
            case "options": return this.repeator.repeatOf(this.size(),null);
            case "groups": return this.repeator.repeatOf(this.size(),null);
            case "required_times": return new Object[]{1,1,1,1,3,1,1,1,1,3,1,1,1,1,1};
            case "status": return this.repeator.repeatOf(this.size(),1);
            case "is_del": return this.repeator.repeatOf(this.size(),0);
            default: return this.repeator.repeatOf(this.size(),null);
        }
    }

    public String getAnswerSheetId(String markItemId){
        return (String)valuesOf("mark_item_id","answer_sheet_id",markItemId);
    }

    private String[] getItemNames(){
        String[] subjectNames = getSubjectNames();
        MutableInt i = new MutableInt();
        StringBuffer sb = new StringBuffer();
        String[] itemNames = Stream.of(subjectNames).map(s->{
            int ii = i.getValue();
            i.setValue(ii+1);
            if(!s.equals(sb.toString())){
                sb.delete(0,sb.length());
                sb.append(s);
                i.setValue(1);
            }
            return s+"题"+i.getValue();
        }).collect(Collectors.toList()).toArray(new String[]{});
        return itemNames;
    }

    private String[] getSubjectNames(){
        String[] yw = this.repeator.repeatOfGroupOfEach(1,5,this.getSubject().names()[0]);
        String[] sx = this.repeator.repeatOfGroupOfEach(1,5,this.getSubject().names()[1]);
        String[] yy = this.repeator.repeatOfGroupOfEach(1,5,this.getSubject().names()[2]);
        String[] ss = new String[yw.length + sx.length + yy.length];
        System.arraycopy(yw,0,ss,0,yw.length);
        System.arraycopy(sx,0,ss,yw.length,sx.length);
        System.arraycopy(yy,0,ss,yw.length+sx.length,yy.length);
        return ss;
    }

    private String[] aswerSheetIds(){
        return  this.repeator.repeatOfGroupOfEach(1,5,this.getAnswerSheet().ids());
    }

    private String[] examIds(){
        return  this.repeator.repeatOfGroupOfEach(1,15,this.getExam().ids());
    }

    private String[] subjectIds(){
        return  this.repeator.repeatOfGroupOfEach(1,5,this.getSubject().ids());
    }

    @Override
    public int size(){
        return 15;
    }

    public int sizeOf(String subjectId){
        int size = 0;
        String[] subjectIds = subjectIds();
        for(String s:subjectIds){
            if(subjectId.equals(s))
                size++;
        }
        return size;
    }

    public Object[] purposeValuesOf(int purpose,String key){
        int[] purposes = Arrays.stream(this.getValues("purpose")).mapToInt(s->(int)s).toArray();
        int length = purposes.length;
        ArrayList<Object> purposeIds = Lists.newArrayList();
        Object[] values = this.getValues(key);
        for(int i = 0;i<length;i++){
            if(purposes[i] == purpose){
                purposeIds.add(values[i]);
            }
        }
        return purposeIds.toArray();
    }



    @Override
    public String[] ids() {
        String[] suffixes = this.fromTo(1, this.size());
        String[] ids = genIds("MIT","",suffixes);
        return ids;
    }

    @Override
    public int order() {
        return 51;
    }
}