/*
 * Copyright (c) 2016,2018, easytnt All Rights Reserved. 深圳市易考试乐学测评有限公司 版权所有.
 */

package com.easytnt.exam.application.exam;

import com.easytnt.commons.util.DateUtilWrapper;
import com.easytnt.exam.domain.exam.ExamRepository;
import com.easytnt.exam.project.MarkingProjectRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.Date;

import static com.easytnt.share.domain.id.IdPrefixes.ExamIdPrefix;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
/**
 * @author Liguiqing
 * @since V3.0
 */

@RunWith(PowerMockRunner.class)
public class ExamApplicationServiceTest {

    private ExamRepository examRepository;

    private MarkingProjectRepository markingProjectRepository;

    @Before
    public void before()throws Exception{
        this.examRepository = mock(ExamRepository.class);
        this.markingProjectRepository = mock(MarkingProjectRepository.class);
    }

    @Test
    public void testNewExamWithProject()throws Exception{
        assertNotNull(examRepository);
        assertNotNull(markingProjectRepository);

        ExamApplicationService examApplicationService = new ExamApplicationService(this.examRepository,
                this.markingProjectRepository);
        Date starts = DateUtilWrapper.toDate("2018-06-01","yyyy-MM-dd");
        Date ends = DateUtilWrapper.toDate("2018-06-02","yyyy-MM-dd");
        NewExamCommand command = new NewExamCommand("examName","一年级",starts,ends,1,1,
        "targetOrgId","creatorOrgId",1,"creatorId");

        String examId = examApplicationService.newExamWithProject(command);
        assertNotNull(examId);
        assertTrue(examId.startsWith(ExamIdPrefix));
    }

}