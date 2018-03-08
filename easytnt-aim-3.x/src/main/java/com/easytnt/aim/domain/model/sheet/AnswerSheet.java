/*
 * Copyright (c) 2016,2018, easytnt All Rights Reserved. 深圳市易考试乐学测评有限公司 版权所有.
 */

package com.easytnt.aim.domain.model.sheet;

import com.easytnt.aim.domain.model.exam.ExamId;
import com.easytnt.aim.domain.model.exam.ExamSubjectId;
import com.easytnt.commons.domain.Entity;

import java.util.Set;

/**
 * 答题卡
 *
 * @author Liguiqing
 * @since V3.0
 */

public abstract class AnswerSheet extends Entity {

    private AnswerSheetId answerSheetId;

    private ExamId examId;

    private ExamSubjectId examSubjectId;

    private Set<SheetImage> images;

}