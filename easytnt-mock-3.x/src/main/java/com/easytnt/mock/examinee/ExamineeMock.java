package com.easytnt.mock.examinee;

import com.easytnt.commons.util.DateUtilWrapper;
import com.easytnt.mock.AbstractMock;
import com.easytnt.mock.IdMocker;
import com.easytnt.mock.exam.ExamMock;
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
    protected String table() {
        return "ps_examinee";
    }

    @Override
    protected String getIdField() {
        return "examinee_id";
    }

    @Override
    protected Object[] getValue(String key) {
        switch (key){
            case "examinee_id": return ids();
            case "exam_id": return repeatOf(this.size(),this.getExam().ids()[0]);
            case "person_id": return otherids(this.size(),ExamineeIdPrefix,"stu");
            case "school_id": return repeatOfOtherids(this.size(),"SCH","1");
            case "school_name": return repeatOf(this.size(),"乐学校");
            case "clazz_id": return this.repeatOfOtheridsGropOfEachMaxLt(40,3,this.size(),"CLA","1","2","3");
            case "clazz_name": return repeatOfOtheridsGropOfEachMaxLt(40,3,this.size(),"1班","2班","3班");
            case "name": return repeatOfGroup(this.size(),fromTo(1,30,"学生"));
            case "student_no": return this.examineenos();
            case "exam_number": return this.examineenos();
            case "point": return repeatOfGroup(this.size(),"1考点");
            case "room": return this.repeator.repeatOfGroupOfEachMaxLt(30,3,this.size(),"1考场","2考场","3考场","4考场");
            case "seat": return repeatOfGroup(this.size(),fromTo(1,30));
            case "last_update_time": return repeatOf(this.size(),DateUtilWrapper.now());
            case "last_operator_id": return repeatOf(this.size(),IdMocker.genId(IdPrefixes.PersonIdPrefix));
            case "last_operator_name": return repeatOf(this.size(),"唐伯虎");
            case "is_del": return repeatOf(this.size(),0);
            default: return repeatOf(this.size(),null);
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
        String[] examineeids = this.ids();
        int length = examineeids.length;
        int newLength = length*size;
        String[] newids = new String[newLength];
        int j = 0;
        for(int i=0;i<newLength;i++){
            newids[i] = examineeids[j++];
            if(j == length) {
                j = 0;
            }
        }
        return newids;
    }

    @Override
    public String[] ids() {
        String[] ids = new String[this.size()];
        for(int i=0;i<this.size();i++){
            ids[i] = IdMocker.genId(ExamineeIdPrefix, ""+i);
        }
        return ids;
    }
}