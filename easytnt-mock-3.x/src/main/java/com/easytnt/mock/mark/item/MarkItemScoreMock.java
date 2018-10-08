package com.easytnt.mock.mark.item;

import com.easytnt.commons.util.NumberUtilWrapper;
import com.easytnt.mock.AbstractMock;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Liguiqing
 * @since V3.0
 */
@Component
public class MarkItemScoreMock extends AbstractMock {


    @Override
    public String table() {
        return "ps_mark_item_score";
    }

    @Override
    public String getIdField() {
        return "mark_item_score_id";
    }

    @Override
    public Object[] getMockValue(String key) {
        switch(key){
            case "mark_item_score_id": return ids();
            case "mark_item_id": return getMarkItemIds();
            case "parent_score_id": return this.repeator.repeatOf(this.size(),null);
            case "name": return getMarkItemNames();
            case "seq": return this.repeator.repeatOf(this.size(),1);
            case "score": return getMarkItemScores();
            case "score_limite": return getScoreLinear();
            case "score_linear": return this.repeator.repeatOf(this.size(),1);
            case "error": return new Object[]{-1d,-1d,10d,-1d,-1d,5d,-1d,-1d,-1d,};
            case "scope": return this.repeator.repeatOf(this.size(),1);
            case "person_id": return this.repeator.repeatOf(this.size(),null);
            case "is_del": return this.repeator.repeatOf(this.size(),0);
            default: return this.repeator.repeatOf(this.size(),null);
        }
    }

    public Double[] getMarkItemScoreLimit(String markItemId){
        String scoreLimit = (String)this.valuesOf("mark_item_id", "score_limite", markItemId);
        return Stream.of(scoreLimit.split(";")).map(s->Double.valueOf(s)).collect(Collectors.toList()).toArray(new Double[]{});
    }

    public Double getError(String markItemId){
        return (Double)this.valuesOf("mark_item_id", "error", markItemId);

    }

    private Object[] getMarkItemIds(){
        return markItemValues("mark_item_id");
    }

    private Object[] getMarkItemNames(){
        return markItemValues("name");
    }

    private Object[] getMarkItemScores(){
        return markItemValues("score");
    }

    private Object[] getScoreLinear(){
        Object[] scores = getMarkItemScores();
        return Stream.of(scores).map(s->{
            return NumberUtilWrapper.toLinearString((Double)s,0.5,2,";");
        }).collect(Collectors.toList()).toArray();
    }

    private Object[] markItemValues(String key){
        MarkItemMock markItem = getOtherMock(MarkItemMock.class);
        Object[] os = markItem.getMockValue(key);
        os[0] = null;
        os[1] = null;
        os[5] = null;
        os[6] = null;
        os[10] = null;
        os[11] = null;
        return Stream.of(os).filter(id->id!=null).collect(Collectors.toList()).toArray();
    }

    @Override
    public int size(){
        return 9;
    }

    @Override
    public String[] ids() {
        String[] suffixes = this.fromTo(1, this.size());
        String[] ids = genIds("MIS","",suffixes);
        return ids;
    }

    @Override
    public int order() {
        return 55;
    }
}