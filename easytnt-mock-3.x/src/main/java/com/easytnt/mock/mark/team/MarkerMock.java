package com.easytnt.mock.mark.team;

import com.easytnt.mock.AbstractMock;
import com.easytnt.mock.mark.item.MarkItemMock;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author Liguiqing
 * @since V3.0
 */
@Component
public class MarkerMock extends AbstractMock {

    private int times = 3;

    @Override
    public String table() {
        return "ps_marker";
    }

    @Override
    public String getIdField() {
        return "marker_id";
    }

    @Override
    public Object[] getMockValue(String key) {
        switch(key){
            case "marker_id": return ids();
            case "exam_id": return this.repeator.repeatOfGroupOfEach(1,this.size(),this.getExam().ids());
            case "subject_id": return getMarkerSubjectIds();
            case "mark_item_id": return markItemIds();
            case "person_id": return pids();
            case "org_id": return this.repeator.repeatOf(this.size(),null);
            case "name": return getMarkerNames();
            case "role": return this.repeator.repeatOf(this.size(),"Normal");
            case "planned": return this.repeator.repeatOf(this.size(),-0);
            case "finished": return this.repeator.repeatOf(this.size(),-0);
            case "status": return this.repeator.repeatOf(this.size(),1);
            case "online": return this.repeator.repeatOf(this.size(),0);
            case "is_del": return this.repeator.repeatOf(this.size(),0);
            default: return this.repeator.repeatOf(this.size(),null);
        }
    }

    public String[] markerIdsOf(String markItemId) {
        Object[] markItemIds = this.markItemIds();
        int length = markItemIds.length;
        ArrayList<String> al = Lists.newArrayList();
        for (int i = 0; i < length; i++) {
            if (markItemIds[i].equals(markItemId)) {
                al.add(this.ids()[i]);
            }
        }
        return al.toArray(new String[]{});
    }

    private Object[] getMarkerSubjectIds(){
        return this.repeator.repeatOfGroupOfEach(1,times,valuesFromMarkItem("subject_id"));
    }

    private Object[] getMarkerNames(){
        Object[] itemNames = valuesFromMarkItem("name");
        Object[] names =  this.repeator.repeatOfGroupOfEach(1,times,
                Arrays.stream(itemNames).map(name->name+"评卷员").toArray());
        Object[] markItemIds = markItemIds();
        String s = (String)markItemIds[0];
        int k = 1;
        for(int i=0;i<names.length;i++){
            if(s.equals(markItemIds[i])){
                names[i] = names[i]+""+k;
                k++;
            }else{
                k=1;
                s = (String)markItemIds[i];
                names[i] = names[i]+""+k;
                k++;
            }
        }
        return names;
    }

    private Object[] markItemIds(){
        return this.repeator.repeatOfGroupOfEach(1,times,valuesFromMarkItem("mark_item_id"));
    }

    private Object[] valuesFromMarkItem(String key){
        MarkItemMock markItem = getOtherMock(MarkItemMock.class);
        return markItem.purposeValuesOf(1,key);
    }


    public int size(){
        return markItemIds().length;
    }

    @Override
    public String[] ids() {
        String[] suffixes = this.fromTo(1, this.size());
        String[] ids = genIds("MAR","",suffixes);
        return ids;
    }

    private String[] pids() {
        String[] suffixes = this.fromTo(1, this.size());
        String[] ids = genIds("PER","",suffixes);
        return ids;
    }

    @Override
    public int order() {
        return 51;
    }
}