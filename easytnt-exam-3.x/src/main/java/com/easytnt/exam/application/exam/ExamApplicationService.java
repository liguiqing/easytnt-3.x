/*
 * Copyright (c) 2016,2018, easytnt All Rights Reserved. 深圳市易考试乐学测评有限公司 版权所有.
 */

package com.easytnt.exam.application.exam;

import com.easytnt.exam.domain.exam.*;
import com.easytnt.share.domain.common.Period;
import com.easytnt.share.domain.id.exam.ExamId;
import com.easytnt.share.domain.id.exam.ProjectId;
import com.easytnt.share.domain.id.org.TargetOrgId;
import com.easytnt.share.domain.school.Grade;
import com.easytnt.share.domain.school.StudyYear;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 考试应用服务
 *
 * @author Liguiqing
 * @since V3.0
 */

public class ExamApplicationService {
    private static Logger logger = LoggerFactory.getLogger(ExamApplicationService.class);

    private ExamRepository examRepository;

    private MarkingProjectRepository markingProjectRepository;

    public ExamApplicationService(ExamRepository examRepository,MarkingProjectRepository markingProjectRepository) {
        this.examRepository = examRepository;
        this.markingProjectRepository = markingProjectRepository;
    }

    public String newExamWithProject(NewExamCommand command){
        logger.debug("创建考试阅卷项目 {} {} ",command.getGradeName(),command.getName());

        ProjectId projectId = new ProjectId();
        MarkingProject project = new MarkingProject(projectId, command.getName(),command.getCreatorId());
        markingProjectRepository.save(project);

        ExamId examId = new ExamId();
        TargetOrgId orgId = new TargetOrgId(command.getTargetOrgId());
        StudyYear studyYear = StudyYear.newYearsOf(command.getStarts());
        Grade grad = new Grade(command.getGradeName(),studyYear);
        ExamScope scope = ExamScope.typeOf(command.getScope());
        ExamCreator creator = new ExamCreator(command.getCreatorOrgId(),
                CreatorOrgType.typeOf(command.getCreatorOrgType()),command.getCreatorId());

        Exam exam = new Exam(examId,projectId,orgId,grad,scope,creator);
        exam.reName(command.getName());
        exam.updateCategory(command.getCategory());
        Period period = new Period(command.getStarts(), command.getEnds());
        exam.updatePeriod(period);
        examRepository.save(exam);

        return examId.id();
    }

}