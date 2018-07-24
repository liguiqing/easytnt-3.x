package com.easytnt.mock.mark.item.scoring;

import com.easytnt.mock.AbstractMock;
import com.easytnt.mock.examinee.ExamineeItemMock;
import com.easytnt.mock.mark.item.MarkItemMock;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

/**
 * @author Liguiqing
 * @since V3.0
 */
@Component
public class ScoringMarkMock extends AbstractMock {


    @Override
    public String table() {
        return "ps_scoring_mark";
    }

    @Override
    public String getIdField() {
        return "mark_id";
    }

    @Override
    public Object[] getMockValue(String key) {
        switch(key){
            case "mark_id": return ids();
            case "exam_id": return valuesFromExamineeItem("exam_id");
            case "subject_id": return valuesFromExamineeItem("subject_id");
            case "batch_id": return valuesFromExamineeItem("batch_id");
            case "mark_item_id": return valuesFromExamineeItem("mark_item_id");
            case "examinee_item_id": return valuesFromExamineeItem("examinee_item_id");
            case "school_id": return this.repeator.repeatOf(this.size(),null);
            case "clazz_id": return this.repeator.repeatOf(this.size(),null);
            case "purpose": return this.repeator.repeatOf(this.size(),"Formal");
            case "point": return this.repeator.repeatOf(this.size(),null);
            case "room": return this.repeator.repeatOf(this.size(),null);
            case "crypt_code": return valuesFromExamineeItem("crypt_code");
            case "group_no": return this.repeator.repeatOf(this.size(),null);
            case "fetch_seq": return fromTo(1,this.size());
            case "required": return valuesFromMarkItem("required_times");
            case "times": return this.repeator.repeatOf(this.size(),0);
            case "arbiter": return this.repeator.repeatOf(this.size(),0);
            case "fetchsign": return this.repeator.repeatOf(this.size(),0);
            case "unabled": return this.repeator.repeatOf(this.size(),0);
            case "formal_marker_ids": return this.repeator.repeatOf(this.size(),null);
            case "score": return this.repeator.repeatOf(this.size(),0d);
            case "is_del": return this.repeator.repeatOf(this.size(),0);
            default: return this.repeator.repeatOf(this.size(),null);
        }
    }

    public Object getValueForMarkId(String markId,String key){
        String[] ids = ids();
        int i = 0;
        for(String id:ids){
            if(id.equals(markId)){
                return this.getValues(key)[i];
            }
        }
        return null;
    }

    private Object[] valuesFromExamineeItem(String key){
        ExamineeItemMock examineeItem = getOtherMock(ExamineeItemMock.class);
        Object[] values = examineeItem.valuesOf(key,1);
        return values;
    }

    private Object[] valuesFromMarkItem(String key){
        MarkItemMock markItem = getOtherMock(MarkItemMock.class);
        return valuesFromOtherMock(key, "mark_item_id",true, markItem);
    }

    @Override
    public int size(){
        ExamineeItemMock examineeItem = getOtherMock(ExamineeItemMock.class);
        return examineeItem.sizeOf(1);
    }


    @Override
    public String[] ids() {
        String[] suffixes = this.fromTo(1, this.size());
        String[] ids = genIds("SMA","",suffixes);
        return ids;
    }

    @Override
    public int order() {
        return 60;
    }

    @Override
    public void correct(JdbcTemplate jdbc){
        String sql1 = "update ps_scoring_mark a inner join ps_examinee_sheet b on a.crypt_code=b.crypt_code inner join ps_examinee c on c.exam_number=b.exam_number " +
                "set a.point=c.point, a.room=c.room,a.school_id=c.school_id ,a.clazz_id=c.clazz_id "+
                "where a.point is null and a.exam_id=?";
        jdbc.update(sql1,new Object[]{this.getValues("exam_id")[0]});

        String sql2 = "update ps_scoring_mark a inner join ps_mark_item b on a.mark_item_id=b.mark_item_id set a.required=b.required_times where a.exam_id=?";
        jdbc.update(sql2,this.getValues("exam_id")[0]);
    }
}