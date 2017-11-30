/*
 * Copyright (c) 2016,2017, easytnt All Rights Reserved. 深圳市易考试乐学测评有限公司 版权所有.
 */

package com.easytnt.ts.application.school;

import com.easytnt.commons.AssertionConcerns;
import com.easytnt.ts.application.school.command.*;
import com.easytnt.ts.domain.model.school.*;
import com.easytnt.ts.domain.model.school.clazz.ClazzRepository;
import com.easytnt.ts.domain.model.school.position.HeadMaster;
import com.easytnt.ts.domain.model.school.position.Teacher;
import com.easytnt.ts.domain.model.school.staff.*;
import com.easytnt.ts.domain.model.school.term.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 学校管理应用服务
 *
 * @author Liguiqing
 * @since V3.0
 */

public class SchoolApplicationService {
    private static Logger logger = LoggerFactory.getLogger(SchoolApplicationService.class);


    private SchoolRepository schoolRepository;

    private ClazzRepository clazzRepository;

    private StaffRepository staffRepository;

    private PostService postService;

    private TermRepository termRepository;

    /**
     * 增加一个学校
     * @param tenantId
     * @param schoolName
     * @param schoolType
     */
    public void newSchool(String tenantId, String schoolName,String schoolType){
        logger.debug("new school {} of {}",schoolName,tenantId);

        TenantId tenant = new TenantId(tenantId);
        SchoolId schoolId = schoolRepository.nextIdentity();
        SchoolType type = SchoolType.valueOf(schoolType);
        School school = new School(tenant,schoolId,schoolName,type);
        schoolRepository.save(school);
    }

    public void addStaff(String schoolId,NewStaffCommand command){

    }

    /**
     * 增加学校校长
     *
     * @param schoolId
     * @param command
     */
    public void newHeadMasterTo(String schoolId,NewHeaderMasterCommand command){
        logger.debug("New school {} master is ",schoolId,command);

        School school = getSchool(schoolId);
        HeadMaster master = school.newHeaderMaster(command.getName(),command.getIdentity(),command.getStarts(),command.getEnds());
        //staffRepository.save(master);
    }


    public void changeHeadMasterPeriod(String schoolId,ChangeHeaderMasterCommand command){
        logger.debug("Chagen headerMaster Period of school {} {} ",schoolId,command);

        HeadMaster master = postService.headMasterFrom(new SchoolId(schoolId),command.getIdentity(), new Period(command.getOldStarts(), command.getOldEnds()));
        //master.changePeriod(new Period(command.getNewStarts(), command.getNewEnds()));
        //staffRepository.save(master);
    }


    public void newTerm(String schoolId,NewTermCommand command){
        logger.debug("New term {} to school {} ",schoolId,command);
        School school = getSchool(schoolId);
        TermId termId = termRepository.nextIdentity();
        Term term = school.newTerm(termId, command.getTermName(),command.getYear(),
                TermOrder.valueOf(command.getTermOrder()), command.getStarts(), command.getEnds());
        termRepository.save(term);
    }

    public void addTeacher(String schoolId,NewTeacherCommand command){
        logger.debug("Teacher {} join school {} ",command,schoolId);

        School school = getSchool(schoolId);
        Teacher teacher = school.join(command.getName(), command.getIdentity(), command.getStarts(), command.getEnds(),
                new Course(command.getCourseName(), command.getSubjectId()));
        //staffRepository.save(teacher);
    }


    protected School getSchool(String schoolId){
        AssertionConcerns.assertArgumentNotNull(schoolId,"请提供学校唯一标识:"+schoolId);
        School school = schoolRepository.loadOfId(new SchoolId(schoolId));
        AssertionConcerns.assertArgumentNotNull(school,"无法读取学校数据:"+schoolId);
        return school;
    }

    public void setSchoolRepository(SchoolRepository schoolRepository) {
        this.schoolRepository = schoolRepository;
    }

    public void setClazzRepository(ClazzRepository clazzRepository) {
        this.clazzRepository = clazzRepository;
    }

    public void setStaffRepository(StaffRepository staffRepository) {
        this.staffRepository = staffRepository;
    }

    public void setPostService(PostService postService) {
        this.postService = postService;
    }

    public void setTermRepository(TermRepository termRepository) {
        this.termRepository = termRepository;
    }
}