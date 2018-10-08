package com.easytnt.mock.answersheet;

import com.easytnt.commons.util.DateUtilWrapper;
import com.easytnt.commons.util.StringUtilWrapper;
import com.easytnt.commons.util.StringUtilWrapper.Word;
import com.easytnt.mock.AbstractMock;
import com.easytnt.mock.IdMocker;
import com.easytnt.share.domain.id.IdPrefixes;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.mutable.MutableInt;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Liguiqing
 * @since V3.0
 */
@Component
public class AnswerSheetItem extends AbstractMock {
    private String[] content;

    @Override
    public String table() {
        return "ps_answer_sheet_item";
    }

    @Override
    public String getIdField() {
        return "answer_sheet_item_id";
    }

    @Override
    public Object[] getMockValue(String key) {
        switch (key){
            case "answer_sheet_item_id": return ids();
            case "parent_item_id": return getSheetItemParentIds();
            case "exam_id": return this.repeator.repeatOf(this.size(),this.getExam().ids()[0]);
            case "subject_id": return getItemsSubjects();
            case "answer_sheet_id": return getItemsAswerSheets();
            case "target_subject_id": return getItemsSubjects();
            case "target_subject_name": return getItemsSubjectNames();
            case "origin_item_id": return this.repeator.repeatOf(this.size(),null);
            case "name": return getItemNames();
            case "catagory1": return getCatagory1();
            case "catagory2": return getCatagory2();
            case "score": return getScore();
            case "required": return this.repeator.repeatOf(this.size(),1);
            case "num": return this.repeator.repeatOf(this.size(),null);
            case "content": return getContent();
            case "st_type": return this.repeator.repeatOf(this.size(),1);
            case "readonly": return this.repeator.repeatOf(this.size(),1);
            case "rule": return this.repeator.repeatOf(this.size(),1);
            case "last_update_time": return this.repeator.repeatOf(this.size(),DateUtilWrapper.now());
            case "last_operator_id": return this.repeator.repeatOf(this.size(),IdMocker.genId(IdPrefixes.PersonIdPrefix));
            case "last_operator_name": return this.repeator.repeatOf(this.size(),"唐伯虎");
            case "is_del": return this.repeator.repeatOf(this.size(),0);
            default: return this.repeator.repeatOf(this.size(),null);
        }
    }

    public int getItemsCount(){
        return this.ids().length;
    }

    public int getZgItemsCount(){
        return Stream.of(this.getValues("catagory2")).filter( c->(int)c >=4).collect(Collectors.toList()).toArray().length;
    }

    public String[] getItemNames(){
        String[] subjectNames = getItemsSubjectNames();
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

    public String[] getContent(){
        if(this.content == null) {
            String[] ids = ids();
            String[] contents = new String[ids.length];
            setArray(contents, 1, 8, 5d, this::content);
            setArray(contents, 21, 28, 5d, this::content);
            setArray(contents, 42, 101, 1.5d, this::content);
            this.content = contents;
        }
        return this.content;
    }

    private String content(double score){
        String s = StringUtilWrapper.randomOf(Word.A, Word.B, Word.C, Word.D);
        return "{\""+s+"\":"+score+"}";
    }

    public Double[] getScore(){
        String[] ids = ids();

        Double[] scores = new Double[ids.length];
        scores[0] = 40d;
        setArray(5d, scores, 1, 8);
        scores[9] = 110d;
        setArray(5d, scores, 10, 13);
        setArray(6d, scores, 14, 18);
        scores[19] = 60d;

        scores[20] = 40d;
        setArray(5d, scores, 21, 28);
        scores[29] = 110d;
        setArray(5d, scores, 30, 33);
        setArray(12d, scores, 34, 38);
        setArray(15d, scores, 39, 40);

        scores[41] = 90d;
        setArray(1.5d, scores, 42, 101);
        scores[102] = 60d;
        setArray(1d, scores, 103, 112);
        setArray(2d, scores, 113, 122);
        scores[ids.length-1] = 30d;
        return scores;
    }

    public Integer[] getCatagory1(){
        String[] ids = ids();

        Integer[] catagory1 = new Integer[ids.length];
        catagory1[0] = 1;
        catagory1[9] = 2;

        catagory1[20] = 1;
        catagory1[29] = 2;

        catagory1[41] = 1;
        catagory1[101] = 2;
        return catagory1;
    }

    public Integer[] getCatagory2(){
        String[] ids = ids();

        Integer[] catagory = new Integer[ids.length];
        setArray(1, catagory, 1, 8);
        setArray(4, catagory, 10, 13);
        setArray(5, catagory, 14, 18);
        setArray(7, catagory, 19, 19);

        setArray(1, catagory, 21, 28);
        setArray(4, catagory, 30, 33);
        setArray(5, catagory, 34, 40);

        setArray(1, catagory, 42, 101);
        setArray(4, catagory, 103, ids.length-2);
        catagory[ids.length-1] = 6;
        return catagory;
    }

    public String[] getSheetItemParentIds(){
        String[] ids = ids();

        //语文1+8+1+10
        String[] pids = new String[ids.length];
        setArray(ids[0], pids, 1, 8);
        setArray(ids[9], pids, 10, 19);

        //数学1+8+1+4+5+2
        setArray(ids[20], pids, 21, 28);
        setArray(ids[29], pids, 30, 40);

        //英语1+60+1+10+10+1
        setArray(ids[41], pids, 42, 101);
        setArray(ids[102], pids, 103, ids.length-1);
        return pids;
    }


    public String[] getItemsSubjectNames(){
        String[] yw = this.repeator.repeatOfGroupOfEach(1,20,this.getSubject().names()[0]);
        String[] sx = this.repeator.repeatOfGroupOfEach(1,21,this.getSubject().names()[1]);
        String[] yy = this.repeator.repeatOfGroupOfEach(1,83,this.getSubject().names()[2]);
        String[] ss = new String[yw.length + sx.length + yy.length];
        System.arraycopy(yw,0,ss,0,yw.length);
        System.arraycopy(sx,0,ss,yw.length,sx.length);
        System.arraycopy(yy,0,ss,yw.length+sx.length,yy.length);
        return ss;
    }


    public String[] getItemsAswerSheets(){
        String[] yw = this.repeator.repeatOfGroupOfEach(1,20,this.getAnswerSheet().ids()[0]);
        String[] sx = this.repeator.repeatOfGroupOfEach(1,21,this.getAnswerSheet().ids()[1]);
        String[] yy = this.repeator.repeatOfGroupOfEach(1,83,this.getAnswerSheet().ids()[2]);
        String[] ss = new String[yw.length + sx.length + yy.length];
        System.arraycopy(yw,0,ss,0,yw.length);
        System.arraycopy(sx,0,ss,yw.length,sx.length);
        System.arraycopy(yy,0,ss,yw.length+sx.length,yy.length);
        return ss;
    }

    public String[] getItemsSubjects(){
        String[] yw = this.repeator.repeatOfGroupOfEach(1,20,this.getSubject().ids()[0]);
        String[] sx = this.repeator.repeatOfGroupOfEach(1,21,this.getSubject().ids()[1]);
        String[] yy = this.repeator.repeatOfGroupOfEach(1,83,this.getSubject().ids()[2]);
        String[] ss = new String[yw.length + sx.length + yy.length];
        System.arraycopy(yw,0,ss,0,yw.length);
        System.arraycopy(sx,0,ss,yw.length,sx.length);
        System.arraycopy(yy,0,ss,yw.length+sx.length,yy.length);
        return ss;
    }

    public List<String> getKgOptionsOfAswerSheet(String aswerSheetId){
        Object[] aswerSheetIds = this.getValues("answer_sheet_id");
        Object[] category2 = getCatagory2();
        ArrayList<String> aswerSheetKgOptions = Lists.newArrayList();
        int length = aswerSheetIds.length;
        for(int i = 0;i < length;i++){
            if(aswerSheetIds[i].equals(aswerSheetId) && category2[i] != null && (category2[i].equals(1)||category2[i].equals(2)||category2[i].equals(3))){
                aswerSheetKgOptions.add(this.getContent()[i]);
            }
        }
        return aswerSheetKgOptions;
    }


    @Override
    public int size(){
        return 20+21+83;
    }

    @Override
    public String[] ids() {
        String[] suffixes = this.fromTo(1, this.size());
        String[] ids = genIds("ASI","",suffixes);
        return ids;
    }
}