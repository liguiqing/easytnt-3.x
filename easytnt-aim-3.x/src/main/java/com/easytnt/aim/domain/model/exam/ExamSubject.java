/*
 * Copyright (c) 2016,2018, easytnt All Rights Reserved. 深圳市易考试乐学测评有限公司 版权所有.
 */

package com.easytnt.aim.domain.model.exam;

import com.easytnt.commons.domain.Entity;

import java.util.Set;

/**
 * 考试科目
 *
 * @author Liguiqing
 * @since V3.0
 */

public class ExamSubject extends Entity {

    private ExamId examId;

    private ExamSubjectId examSubjectId;

    private Set<ExamPaper> papers;

    private String name;

    private float fullScore; //试卷满分

    private float zgScore; //主观题满分

    private float kgScore; //客观题满分

    private MarkStatus status;

    private ExamSubjectStatistics statistics;
}