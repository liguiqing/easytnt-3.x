package com.easytnt.mock.mark.team;

import com.easytnt.mock.AbstractMock;
import com.easytnt.mock.mark.item.MarkItemMock;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @author Liguiqing
 * @since V3.0
 */
@Component
public class MarkTeamMock extends AbstractMock {


    @Override
    public String table() {
        return "ps_mark_team";
    }

    @Override
    public String getIdField() {
        return "team_id";
    }

    @Override
    public Object[] getMockValue(String key) {
        switch(key){
            case "team_id": return ids();
            case "parent_team_id": return this.repeator.repeatOf(this.size(),null);
            case "exam_id": return this.repeator.repeatOfGroupOfEach(1,this.size(),this.getExam().ids());
            case "subject_id": return getTeamSubjectIds();
            case "org_id": return this.repeator.repeatOf(this.size(),null);
            case "mark_item_id": return markItemIds();
            case "name": return getTeamNames();
            case "planned": return this.repeator.repeatOf(this.size(),-1);
            case "finished": return this.repeator.repeatOf(this.size(),-1);
            case "level": return this.repeator.repeatOf(this.size(),1);
            case "is_del": return this.repeator.repeatOf(this.size(),0);
            default: return this.repeator.repeatOf(this.size(),null);
        }
    }

    private Object[] getTeamSubjectIds(){
        return valuesFromMarkItem("subject_id");
    }

    private Object[] getTeamNames(){
        Object[] itemNames = valuesFromMarkItem("name");
        return Arrays.stream(itemNames).map(name->name+"组1").toArray();
    }

    private Object[] markItemIds(){
        return valuesFromMarkItem("mark_item_id");
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
        String[] ids = genIds("MTE","",suffixes);
        return ids;
    }

    @Override
    public int order() {
        return 56;
    }
}