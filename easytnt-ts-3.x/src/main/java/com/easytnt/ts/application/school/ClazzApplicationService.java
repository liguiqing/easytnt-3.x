/*
 * Copyright (c) 2016,2017, easytnt All Rights Reserved. 深圳市易考试乐学测评有限公司 版权所有.
 */

package com.easytnt.ts.application.school;

import com.easytnt.ts.application.school.command.NewClazzCommand;
import com.easytnt.ts.domain.model.school.*;
import com.easytnt.ts.domain.model.school.clazz.Clazz;
import com.easytnt.ts.domain.model.school.clazz.ClazzAdiminType;
import com.easytnt.ts.domain.model.school.clazz.ClazzId;
import com.easytnt.ts.domain.model.school.clazz.ClazzRepository;
import com.easytnt.ts.domain.model.school.term.TermId;
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

    public void newClazz(String schoolId, NewClazzCommand command){
        logger.debug("New Clazz {} for school{}",command,schoolId);
        School school = schoolApplicationService.getSchool(schoolId);
        ClazzId clazzId = clazzRepository.nextIdentity();
        Grade grade = new Grade(command.getGradeName(),GradeLevel.fromName(command.getGradeLevel()),
                new StudyYear(command.getYear()));
        ClazzAdiminType type = ClazzAdiminType.fromName(command.getType());
        WLType wl = WLType.fromName(command.getWlType());
        TermId termId = new TermId(command.getTermId());
        Clazz newClazz = new Clazz(school.schoolId(),clazzId,command.getName(),command.getClazzNo(),
                command.getCreateDate(),grade,type,wl,termId);
        clazzRepository.save(newClazz);
    }
}