package com.easytnt.mock.examinee;

import com.easytnt.commons.util.DateUtilWrapper;
import com.easytnt.mock.AbstractMock;
import com.easytnt.mock.IdMocker;
import com.easytnt.share.domain.id.IdPrefixes;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

/**
 * @author Liguiqing
 * @since V3.0
 */
@Component
public class SubjectRegisterMock extends AbstractMock {

    private Integer[] attendeds;

    @Override
    public String table() {
        return "ps_subject_rigister";
    }

    @Override
    public String getIdField() {
        return "examinee_id";
    }

    @Override
    public Object[] getMockValue(String key) {
        switch (key){
            case "exam_id": return this.repeator.repeatOf(this.realSize(),this.getExam().ids()[0]);
            case "examinee_id": return this.examineeIds();
            case "subject_id": return this.getExamineesRegisterSubjects();
            case "clazz_id": return this.getExaminee().examClazzIdsRepeatOf(this.getRegisterSubjects());
            case "clazz_name": return this.getExaminee().examClazzNamesRepeatOf(this.getRegisterSubjects());
            case "attended": return attendeds();
            case "last_update_time": return this.repeator.repeatOf(this.realSize(),DateUtilWrapper.now());
            case "last_operator_id": return this.repeator.repeatOf(this.realSize(),IdMocker.genId(IdPrefixes.PersonIdPrefix));
            case "last_operator_name": return this.repeator.repeatOf(this.realSize(),"唐伯虎");
            case "is_del": return this.repeator.repeatOf(this.realSize(),0);
            default: return this.repeator.repeatOf(this.realSize(),null);
        }
    }

    private Integer[] attendeds(){
        if(this.attendeds == null)
            this.attendeds = this.repeator.repeatOfMixedRandom(this.realSize(),2,0.05,1);
        return this.attendeds;
    }

    public int getRegisterSubjects(){
        return this.getSubject().ids().length;
    }

    public int getRegisterExaminees(){
        return this.examineeIds().length;
    }

    public String[] getExamineesRegisterSubjects(){
        String[] ss = this.repeator.repeatOfGroupOfEach(1,size(),this.getSubject().ids());
        return ss;
    }

    @Override
    protected void correct(JdbcTemplate jdbc){
        //.update("update ps_subject_rigister a inner join ps_examinee b on b.examinee_id=a.examinee_id " +
        //        "set a.clazz_id=b.clazz_id,a.clazz_name = b.clazz_name");
    }


    private String[] examineeIds(){
        return this.getExaminee().examineeIdsRepeatOf(this.getRegisterSubjects());
    }

    public int realSize(){
        return this.size() * this.getRegisterSubjects();
    }

    @Override
    public int size(){
        return 100;
    }


    @Override
    public String[] ids() {
        return this.examineeIds();
    }

    @Override
    public int order() {
        return 31;
    }
}