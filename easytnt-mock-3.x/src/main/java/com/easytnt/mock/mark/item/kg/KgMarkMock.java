package com.easytnt.mock.mark.item.kg;

import com.easytnt.commons.util.StringUtilWrapper;
import com.easytnt.mock.AbstractMock;
import com.easytnt.mock.answersheet.AnswerSheetItem;
import com.easytnt.mock.examinee.ExamineeItemMock;
import com.easytnt.mock.mark.item.MarkItemMock;
import com.easytnt.mock.mark.item.MarkItemToSheetItemMock;
import com.google.common.collect.Lists;
import com.google.gson.Gson;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * @author Liguiqing
 * @since V3.0
 */
@Component
public class KgMarkMock extends AbstractMock {
    private String[] optionals = null;

    private String[] scores = null;

    private String[] rightOptions = null;

    @Override
    public String table() {
        return "ps_kg_mark";
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
            case "crypt_code": return valuesFromExamineeItem("crypt_code");
            case "fetch_seq": return fromTo(1,this.size());
            case "required": return valuesFromMarkItem("mark_item_id","required_times");
            case "times": return this.repeator.repeatOf(this.size(),0);
            case "arbiter": return this.repeator.repeatOf(this.size(),0);
            case "fetchsign": return this.repeator.repeatOf(this.size(),0);
            case "unabled": return this.repeator.repeatOf(this.size(),0);
            case "score": return getScore();
            case "scores": return  getScores();
            case "optional": return getOptionals();
            case "doubt": return this.repeator.repeatOfMixedRandom(this.size(),1,0.1,0);
            case "doubt_done": return this.repeator.repeatOf(this.size(),1);
            case "is_del": return this.repeator.repeatOf(this.size(),0);
            default: return this.repeator.repeatOf(this.size(),null);
        }
    }

    private Double[] getScore(){
        String[] scores = this.getScores();
        int length = scores.length;
        Double[] score = new Double[length];
        for(int i=0;i<length;i++){
            double sum = Arrays.stream(scores[i].split(";")).mapToDouble(s->Double.valueOf(s)).sum();
            score[i] = sum;
        }
        return score;
    }

    private String[] getScores(){
        if(this.scores == null) {
            Object[] optionals = this.getValues("optional");
            Object[] markItemIds = this.getValues("mark_item_id");
            int length = optionals.length;
            this.scores = new String[length];
            MarkItemMock markItem = getOtherMock(MarkItemMock.class);
            AnswerSheetItem sheetItem = getOtherMock(AnswerSheetItem.class);
            for (int i = 0; i < length; i++) {
                String answerSheetId = markItem.getAnswerSheetId((String)markItemIds[i]);
                List kgOtions = sheetItem.getKgOptionsOfAswerSheet(answerSheetId);
                String s = (String)optionals[i];
                String[] sp = s.split(";");
                int length2 = sp.length;
                StringBuffer sb = new StringBuffer();
                for(int k=0;k<length2;k++){
                    sb.append(countSocre(kgOtions.get(k)+"",sp[k])+"").append(";");
                }
                sb.delete(sb.length() - 1, sb.length());
                this.scores[i] = sb.toString();
            }
        }

        return this.scores;
    }

    private double countSocre(String optionSt,String option){
        if(option.equals("#"))
            return 0f;

        Gson gson = new Gson();
        Map<String,Double> m = gson.fromJson(optionSt,Map.class);
        Iterator<String> keys = m.keySet().iterator();

        while(keys.hasNext()){
            String key = keys.next();
            if(key.equals(option))
                return m.get(key);
        }

        return 0f;
    }

    private String[] getOptionals(){

        if(this.optionals == null) {
            this.optionals = new String[this.size()];
            Object[] markItemIds = this.getMockValue("mark_item_id");
            MarkItemToSheetItemMock asItem = getOtherMock(MarkItemToSheetItemMock.class);
            AnswerSheetItem sheetItem = getOtherMock(AnswerSheetItem.class);
            Object[] sheetItemIds = asItem.getValues("answer_sheet_item_id");
            Object[] markItemIds2 = asItem.getValues("mark_item_id");
            Object[] sheetItemIds2 = sheetItem.getValues("answer_sheet_item_id");
            Object[] category2 = sheetItem.getValues("catagory2");
            int length = sheetItemIds.length;
            int length2 = sheetItemIds2.length;
            for(int a = 0;a<optionals.length;a++) {
                String markItemId = (String)markItemIds[a];
                ArrayList<String> options = Lists.newArrayList();
                for (int i = 0; i < length; i++) {
                    if(markItemId.equals(markItemIds2[i])) {
                        for (int j = 0; j < length2; j++) {
                            if (sheetItemIds[i].equals(sheetItemIds2[j])) {
                                if (category2[j].equals(1) || category2[j].equals(2) || category2[j].equals(3)) {
                                    String o = StringUtilWrapper.randomOf(StringUtilWrapper.Word.A, StringUtilWrapper.Word.B, StringUtilWrapper.Word.C, StringUtilWrapper.Word.D);
                                    options.add(o);
                                }
                            }
                        }
                    }
                }
                String[] ops = options.toArray(new String[]{});
                ops = this.repeator.repeatOfGroupMixedRandom(1, new String[]{"#"}, 0.1, ops);
                String optional = String.join(";", ops);
                optionals[a] = optional;
            }
        }
        return this.optionals;
    }

    private Object[] valuesFromExamineeItem(String key){
        ExamineeItemMock examineeItem = getOtherMock(ExamineeItemMock.class);
        Object[] values = examineeItem.valuesOf(key,2);
        return values;
    }

    private Object[] valuesFromMarkItem(String key,String otherKey){
        MarkItemMock markItem = getOtherMock(MarkItemMock.class);
        return valuesFromOtherMock(key,"mark_item_id", otherKey,true, markItem);
    }

    @Override
    public int size(){
        ExamineeItemMock examineeItem = getOtherMock(ExamineeItemMock.class);
        return examineeItem.sizeOf(2);
    }


    @Override
    public String[] ids() {
        String[] suffixes = this.fromTo(1, this.size());
        String[] ids = genIds("KMA","",suffixes);
        return ids;
    }

    @Override
    public int order() {
        return 61;
    }

    @Override
    public void correct(JdbcTemplate jdbc){

    }
}