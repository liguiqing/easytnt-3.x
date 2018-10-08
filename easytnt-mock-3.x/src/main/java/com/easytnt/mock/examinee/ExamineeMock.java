package com.easytnt.mock.examinee;

import com.easytnt.commons.util.DateUtilWrapper;
import com.easytnt.mock.AbstractMock;
import com.easytnt.mock.IdMocker;
import com.easytnt.share.domain.id.IdPrefixes;
import org.springframework.stereotype.Component;

import static com.easytnt.share.domain.id.IdPrefixes.ExamineeIdPrefix;

/**
 * @author Liguiqing
 * @since V3.0
 */
@Component
public class ExamineeMock extends AbstractMock {

    @Override
    public int order(){
        return 30;
    }

    @Override
    public int size(){
        return 100;
    }

    @Override
    public String table() {
        return "ps_examinee";
    }

    @Override
    public String getIdField() {
        return "examinee_id";
    }

    @Override
    public Object[] getMockValue(String key) {
        switch (key){
            case "examinee_id": return ids();
            case "exam_id": return this.repeator.repeatOf(this.size(),this.getExam().ids()[0]);
            case "person_id": return genIds(ExamineeIdPrefix,"stu",fromTo(1,this.size()));
            case "school_id": return this.repeator.repeatOfGroup(this.size(),genIds("SCH","",fromTo(1,1)));
            case "school_name": return this.repeator.repeatOf(this.size(),"乐学校");
            case "clazz_id": return this.examClazzIdsRepeatOf(1);
            case "clazz_name": return this.examClazzNamesRepeatOf(1);
            case "name": return this.repeator.repeatOfGroup(1,fromTo("学生",1,100));
            case "student_no": return this.examineenos();
            case "exam_number": return this.examineenos();
            case "point": return this.repeator.repeatOfGroup(this.size(),"1考点");
            case "room": return this.repeator.repeatOfGroupOfEachMaxLt(1,30,this.size(),fromTo(1,4,"考场"));
            case "seat": return this.repeator.repeatOfGroupOfEachLoopMaxLt(1,4,this.size(),fromTo(1,30));
            case "last_update_time": return this.repeator.repeatOf(this.size(),DateUtilWrapper.now());
            case "last_operator_id": return this.repeator.repeatOf(this.size(),IdMocker.genId(IdPrefixes.PersonIdPrefix));
            case "last_operator_name": return this.repeator.repeatOf(this.size(),"唐伯虎");
            case "is_del": return this.repeator.repeatOf(this.size(),0);
            default: return this.repeator.repeatOf(this.size(),null);
        }
    }

    public Integer[] examineenos(){
        Integer[] nos = new Integer[this.size()];
        for(int i=0;i<this.size();i++){
            nos[i] = 101001001+i;
        }
        return nos;
    }


    public String[] examineeIdsRepeatOf(int size){
        return this.repeator.repeatOfGroup(size,this.ids());
    }

    public String[] examClazzIdsRepeatOf(int size){
        String[] clazzes = this.repeator.repeatOfGroupOfEachMaxLt(2,30,this.size(),genIds("CLA","",fromTo(1,3)));
        return this.repeator.repeatOfGroup(size, clazzes);
    }

    public String[] examClazzNamesRepeatOf(int size){
        String[] clazzes = this.repeator.repeatOfGroupOfEachMaxLt(2,30,this.size(),fromTo(1,3,"班"));
        return this.repeator.repeatOfGroup(size, clazzes);
    }

    @Override
    public String[] ids() {
        String[] suffixes = this.fromTo(1, this.size());
        String[] ids = genIds(ExamineeIdPrefix,"",suffixes);
        return ids;
    }
}