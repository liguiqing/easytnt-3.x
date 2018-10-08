package com.easytnt.mock.examinee;

import com.easytnt.commons.util.NumberUtilWrapper;
import com.easytnt.mock.AbstractMock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.stream.Stream;

/**
 * @author Liguiqing
 * @since V3.0
 */
@Component
public class ExamineeSheetMock extends AbstractMock {

    private String[] cryptCode;

    private Integer[]markable;

    @Autowired
    private SubjectRegisterMock register;

    @Override
    public String table() {
        return "ps_examinee_sheet";
    }

    @Override
    public String getIdField() {
        return "sheet_id";
    }

    @Override
    public Object[] getMockValue(String key) {
        switch(key){
            case "sheet_id": return this.ids();
            case "answer_sheet_id": return this.repeator.repeatOfGroupOfEach(1,size(),this.getAnswerSheet().ids());
            case "exam_id": return this.repeator.repeatOf(this.realSize(),this.getExam().ids()[0]);
            case "subject_id": return this.repeator.repeatOfGroupOfEach(1,size(),this.getSubject().ids());
            case "batch_id": return this.repeator.repeatOf(this.realSize(),null);
            case "sheet_key": return getCryptCode();
            case "catagory": return this.repeator.repeatOf(this.realSize(),null);
            case "exam_number": return this.getExamineenos();
            case "seq": return this.repeator.repeatOf(this.realSize(),1);
            case "crypt_code": return getCryptCode();
            case "scoredable": return this.repeator.repeatOf(this.realSize(),1);
            case "markable": return getMarkable();
            case "score": return this.repeator.repeatOf(this.realSize(),null);
            case "kg_score": return this.repeator.repeatOf(this.realSize(),null);
            case "zg_score": return this.repeator.repeatOf(this.realSize(),null);
            case "is_del": return this.repeator.repeatOf(this.realSize(),0);
            default: return this.repeator.repeatOf(this.realSize(),null);
        }
    }

    public int countMarkables(){
        return Stream.of(this.getValues("markable")).filter(m->(int)m==1).toArray().length;
    }

    public int countMarkablesOf(String subjectId){
        int length = this.realSize();
        Object[] subjectIds = this.getValues("subject_id");
        Object[] markables = this.getValues("markable");
        int count = 0;
        for(int i=0;i<length;i++){
            if(subjectIds[i].equals(subjectId) && (int)markables[i] == 1)
                count++;
        }
        return count;
    }

    @Override
    protected void correct(JdbcTemplate jdbc){
        String sql = "update ps_examinee_sheet set scoredable=markable where exam_id=?";
        jdbc.update(sql, this.getExam().ids()[0]);
    }

    private Integer[] getExamineenos(){
        return this.repeator.repeatOfGroup(register.getRegisterSubjects(), this.getExaminee().examineenos());
    }

    private Integer[] getMarkable(){
        if(this.markable == null)
            this.markable = this.repeator.repeatOfMixedRandom(this.realSize(),0,0.03,1);
        return this.markable;
    }

    private String[] getCryptCode(){
        if(this.cryptCode != null)
            return this.cryptCode;
        this.cryptCode = new String[this.realSize()];
        for(int i=0;i<this.realSize();i++){
            fillWithRandom(this.cryptCode,i);
        }
        return this.cryptCode;
    }

    private void fillWithRandom(String[] ss,int i){
        String s = NumberUtilWrapper.randomBetween(10000000,99999999)+"";
        Object[] os = Stream.of(ss).filter(s1 -> s.equals(s1)).toArray();
        if(os.length>0)
            fillWithRandom(ss,i);
        else
            ss[i] = s;
    }

    @Override
    public int size(){
        return 100;
    }

    public int realSize(){
        return 100 * register.getRegisterSubjects();
    }

    @Override
    public String[] ids() {
        String[] suffixes = this.fromTo(1, this.realSize());
        String[] ids = genIds("ESH","",suffixes);
        return ids;
    }

    @Override
    public int order() {
        return 35;
    }
}