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

    @Override
    protected String table() {
        return "ps_subject_rigister";
    }

    @Override
    protected String getIdField() {
        return "examinee_id";
    }

    @Override
    protected Object[] getValue(String key) {
        switch (key){
            case "exam_id": return repeatOf(this.realSize(),this.getExam().ids()[0]);
            case "examinee_id": return this.examineeIds();
            case "subject_id": return repeatOfGroup(this.realSize(),this.getSubject().ids());
            case "clazz_id": return repeatOfOtheridsGrop(this.realSize(),"CLA","1","2","3");
            case "clazz_name": return repeatOfGroup(this.realSize(),"1班","2班","3班");
            case "attended": return repeatOfMixedRandom(this.realSize(),2,0.05,1);
            case "last_update_time": return repeatOf(this.realSize(),DateUtilWrapper.now());
            case "last_operator_id": return repeatOf(this.realSize(),IdMocker.genId(IdPrefixes.PersonIdPrefix));
            case "last_operator_name": return repeatOf(this.realSize(),"唐伯虎");
            case "is_del": return repeatOf(this.realSize(),0);
            default: return repeatOf(this.realSize(),null);
        }
    }

    @Override
    protected void correct(JdbcTemplate jdbc){
        jdbc.update("update ps_subject_rigister a inner join ps_examinee b on b.examinee_id=a.examinee_id " +
                "set a.clazz_id=b.clazz_id,a.clazz_name = b.clazz_name");
    }


    private String[] examineeIds(){
        return this.getExaminee().examineeIdsRepeatOf(this.getSubject().ids().length);
    }

    public int realSize(){
        return this.size() * this.getSubject().ids().length;
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