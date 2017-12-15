/*
 * Copyright (c) 2016,2017, easytnt All Rights Reserved. 深圳市易考试乐学测评有限公司 版权所有.
 */

package com.easytnt.ts.application.school;

import com.easytnt.commons.AssertionConcerns;
import com.easytnt.ts.application.school.command.*;
import com.easytnt.ts.domain.model.school.*;
import com.easytnt.ts.domain.model.school.clazz.ClazzRepository;
import com.easytnt.ts.domain.model.school.common.Identity;
import com.easytnt.ts.domain.model.school.common.IdentityType;
import com.easytnt.ts.domain.model.school.common.Period;
import com.easytnt.ts.domain.model.school.position.HeadMaster;
import com.easytnt.ts.domain.model.school.position.Position;
import com.easytnt.ts.domain.model.school.position.PositionFilter;
import com.easytnt.ts.domain.model.school.position.Teacher;
import com.easytnt.ts.domain.model.school.staff.Staff;
import com.easytnt.ts.domain.model.school.staff.StaffId;
import com.easytnt.ts.domain.model.school.staff.StaffRepository;
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

    /**
     * 给学校增加新的教职工
     * @param schoolId
     * @param command
     */
    public void addStaffTo(String schoolId,NewStaffCommand command){
        logger.debug("Add Staff {} to school {}  ",schoolId,command);

        Staff staff = new Staff(new SchoolId(schoolId),staffRepository.nextIdentity(),command.getName(),
                new Identity(IdentityType.EduID.Other,command.getIdentity()),
                new Period(command.getStarts(), command.getEnds()));
        staffRepository.save(staff);
    }

    /**
     * 增加学校校长
     *
     * @param schoolId
     * @param command
     */
    public void headMasterTo(String schoolId,NewHeaderMasterCommand command){
        logger.debug("New school {} master is ",schoolId,command);

        HeadMaster master = new HeadMaster(new SchoolId(schoolId),command.getName(),command.getIdentity(),new Period(command.getStarts(),command.getEnds()));
        Staff staff = staffRepository.loadOfId(new StaffId(command.getIdentity()));
        staff.addPosition(master);
        staffRepository.save(staff);
    }


    public void changeHeadMasterPeriod(final String schoolId,final ChangeHeaderMasterCommand command){
        logger.debug("Chage headerMaster Period of school {} {} ",schoolId,command);

        Staff staff = staffRepository.loadOfId(new StaffId(command.getIdentity()));
        staff.renewOfPosition(new Period(command.getNewStarts(),command.getNewEnds()),new PositionFilter() {

            @Override
            public boolean isSatisfied(Position position) {
                if(position instanceof HeadMaster){
                    HeadMaster headMaster = (HeadMaster) position;
                    if(headMaster.sameSchoolOf(new SchoolId(schoolId))){
                        return true;
                    }
                }
                return false;
            }
        });
        staffRepository.save(staff);
    }


    public void newTerm(String schoolId,NewTermCommand command){
        logger.debug("New term {} to school {} ",schoolId,command);

        TermId termId = termRepository.nextIdentity();
        Term term = new Term(termId, command.getTermName(),new StudyYear(command.getYear()),
                TermOrder.valueOf(command.getTermOrder()), new TimeSpan(command.getStarts(),
                command.getEnds()),new SchoolId(schoolId));
        termRepository.save(term);
    }

    public void addTeacher(String schoolId,NewTeacherCommand command){
        logger.debug("Teacher {} join school {} ",command,schoolId);

        Staff staff = staffRepository.loadOfId(new StaffId(command.getIdentity()));
        SchoolId schoolId1 = new SchoolId(schoolId);
        if(staff == null){
            staff = new Staff(schoolId1,staffRepository.nextIdentity(),command.getName(),
                        new Identity(IdentityType.EduID.Other,command.getIdentity()),
                        new Period(command.getStarts(), command.getEnds()));
        }

        Teacher teacher = new Teacher(schoolId1,command.getName(), command.getIdentity(), new Period(command.getStarts(), command.getEnds()),
                new Course(command.getCourseName(), command.getSubjectId()));
        staff.addPosition(teacher);
        staffRepository.save(staff);
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

    public void setTermRepository(TermRepository termRepository) {
        this.termRepository = termRepository;
    }
}