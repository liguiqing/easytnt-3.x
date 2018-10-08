/*
 * Copyright (c) 2016,2018, easytnt All Rights Reserved. 深圳市易考试乐学测评有限公司 版权所有.
 */

package com.easytnt.aim.domain.model.sheet;

import com.easytnt.aim.domain.model.exam.ExamSubjectId;
import com.easytnt.commons.domain.Entity;

/**
 * 评题
 *
 * @author Liguiqing
 * @since V3.0
 */

public class MarkItem extends Entity {
    private MarkItemId itemId;

    private MarkItemSpecId itemSpecId;

    private ExamSubjectId subjectId;

}