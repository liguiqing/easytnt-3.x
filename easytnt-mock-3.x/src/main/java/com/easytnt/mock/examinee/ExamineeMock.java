package com.easytnt.mock.examinee;

import com.easytnt.commons.domain.Identity;
import com.easytnt.commons.util.DateUtilWrapper;
import com.easytnt.mock.AbstractMock;
import com.easytnt.mock.IdMocker;
import com.easytnt.mock.Mock;
import com.easytnt.mock.exam.ExamMock;
import com.easytnt.share.domain.id.IdPrefixes;
import com.easytnt.share.domain.id.examinee.ExamineeId;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.easytnt.share.domain.id.IdPrefixes.ExamineeIdPrefix;

/**
 * @author Liguiqing
 * @since V3.0
 */

public class ExamineeMock extends AbstractMock {

    private ExamMock exam;

    private int size = 100;

    public void userMocks(List<Mock> mocks){
        for(Mock mock:mocks){
            if(mock instanceof  ExamMock){
                this.exam = (ExamMock) mock;
                break;
            }
        }
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
            case "examinee_id": return examineeids();
            case "exam_id": return repeatOf(this.size,this.exam.ids()[0].id());
            case "person_id": return otherids(this.size,ExamineeIdPrefix,"stu");
            case "school_id": return repeatOfOtherids(this.size,"SCH","1");
            case "school_name": return repeatOf(this.size,"乐学校");
            case "clazz_id": return repeatOfOtheridsGrop(this.size,"CLA","1","2","3");
            case "clazz_name": return repeatOfGroup(this.size,"1班","2班","3班");
            case "name": return repeatOfGroup(this.size,fromTo(1,30,"学生"));
            case "student_no": return this.examineenos();
            case "exam_number": return this.examineenos();
            case "point": return repeatOfGroup(this.size,"1考点");
            case "room": return repeatOfGroup(this.size,"1考场","2考场","3考场","4考场");
            case "seat": return repeatOfGroup(this.size,fromTo(1,30));
            case "last_update_time": return repeatOf(this.size,DateUtilWrapper.now());
            case "last_operator_id": return repeatOf(this.size,IdMocker.genId(IdPrefixes.PersonIdPrefix));
            case "last_operator_name": return repeatOf(this.size,"唐伯虎");
            case "is_del": return repeatOf(this.size,0);
            default: return repeatOf(this.size,null);
        }
    }

    public Integer[] examineenos(){
        Integer[] nos = new Integer[this.size];
        for(int i=0;i<this.size;i++){
            nos[i] = 101001001+i;
        }
        return nos;
    }

    private String[] examineeids(){
        return  Stream.of(ids()).map(Identity::id).collect(Collectors.toList()).toArray(new String[]{});
    }

    @Override
    protected String getFields() {
        return "examinee_id, exam_id, person_id, school_id, school_name, clazz_id, clazz_name, " +
                "name, student_no, exam_number, point, room, seat, last_update_time, last_operator_id, last_operator_name, is_del";
    }

    @Override
    public Identity[] ids() {
        ExamineeId[] ids = new ExamineeId[100];
        for(int i=0;i<100;i++){
            ids[i] = new ExamineeId(IdMocker.genId(ExamineeIdPrefix, ""+i));
        }
        return ids;
    }

    @Override
    public int order() {
        return 4;
    }
}