/*
 * Copyright (c) 2016,2017, easytnt All Rights Reserved. 深圳市易考试乐学测评有限公司 版权所有.
 */

package com.easytnt.ts.application.school;

import com.easytnt.commons.AssertionConcerns;
import com.easytnt.ts.application.school.command.NewClazzCommand;
import com.easytnt.ts.application.school.command.NewClazzMasterCommand;
import com.easytnt.ts.application.school.command.NewClazzTeacherCommand;
import com.easytnt.ts.application.school.command.NewStudentCommand;
import com.easytnt.ts.domain.model.school.*;
import com.easytnt.ts.domain.model.school.clazz.*;
import com.easytnt.ts.domain.model.school.common.Period;
import com.easytnt.ts.domain.model.school.common.WLType;
import com.easytnt.ts.domain.model.school.staff.*;
import com.easytnt.ts.domain.model.school.term.Term;
import com.easytnt.ts.domain.model.school.term.TermId;
import com.easytnt.ts.domain.model.school.term.TermRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 班级应用服务
 *
 * @author Liguiqing
 * @since V3.0
 */

public class ClazzApplicationService {
    private static Logger logger = LoggerFactory.getLogger(ClazzApplicationService.class);

    private SchoolRepository schoolRepository;

    private ClazzRepository clazzRepository;

    private SchoolApplicationService schoolApplicationService;

    private StaffRepository staffRepository;

    private TermRepository termRepository;


    public void newClazz(String schoolId, NewClazzCommand command){
        logger.debug("New Clazz {} for school{}",command,schoolId);

        School school = schoolApplicationService.getSchool(schoolId);
        ClazzId clazzId = clazzRepository.nextIdentity();
        Grade grade = new Grade(command.getGradeName(),GradeLevel.fromName(command.getGradeLevel()),
                new StudyYear(command.getYear()));

        WLType wl = WLType.fromName(command.getWlType());
        Term term = termRepository.loadOfId(new TermId(command.getTermId()));

        Clazz newClazz = ClazzFactory.newClazz(school.schoolId(),clazzId,command.getName(),command.getClazzNo(),
                command.getCreateDate(),grade,command.getType(),wl,term);
        clazzRepository.save(newClazz);
    }

    public void addClassMaster(NewClazzMasterCommand command){
        logger.debug("ClazzMaster {} for clazz {}",command.getName(),command.getClazzId());

        Clazz clazz = clazzRepository.loadOfId(new ClazzId(command.getClazzId()));
        AssertionConcerns.assertArgumentNotNull(clazz,"请提供班级");

        schoolApplicationService.staffActToAndSave(new StaffId(command.getIdentity()),new ActClazzMaster(clazz),
                new Period(command.getStarts(),command.getEnds()));
    }

    public void addClassTeacher(final NewClazzTeacherCommand command){
        logger.debug("Clazzteacher {} for clazz {}",command.getName(),command.getClazzId());

        Clazz clazz = clazzRepository.loadOfId(new ClazzId(command.getClazzId()));
        AssertionConcerns.assertArgumentNotNull(clazz,"请提供班级");

        Staff staff = staffRepository.loadOfId(new StaffId(command.getIdentity()));
        AssertionConcerns.assertArgumentNotNull(staff,"请提供老师");

        Position position = staff.positionOf(new PositionFilter() {
            @Override
            public boolean isSatisfied(Position position) {
                if(position instanceof Teacher){
                    Teacher teacher = (Teacher) position;
                    return teacher.canTeach(new Course(command.getCourseName(),command.getSubjectId()));
                }
                return false;
            }
        });

        AssertionConcerns.assertArgumentNotNull(position,command.getName()+"老师没有教授" +
                command.getCourseName());
        Teacher teacher = (Teacher)position;
        Period period = new Period(command.getStarts(), command.getEnds());
        Grade grade = clazz.periodGrade(period);
        AssertionConcerns.assertArgumentNotNull(grade,period.formatString() +"没有找到" +
                clazz.name());

        ClazzTeacher clazzTeacher = teacher.transfer(new TeacherToClazzTeacherTranslater(clazz,
                new Period(command.getStarts(),command.getEnds()),grade));

        staff.addPosition(clazzTeacher);
        staffRepository.save(staff);
    }

    public void addStudent(String schoolId,NewStudentCommand command){

    }
}