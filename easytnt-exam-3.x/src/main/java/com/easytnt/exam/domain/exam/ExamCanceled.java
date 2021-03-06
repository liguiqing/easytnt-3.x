/*
 * Copyright (c) 2016,2018, easytnt All Rights Reserved. 深圳市易考试乐学测评有限公司 版权所有.
 */

package com.easytnt.exam.domain.exam;

import com.easytnt.commons.domain.AbstractDomainEvent;
import com.easytnt.share.domain.id.exam.ExamId;


/**
 * @author Liguiqing
 * @since V3.0
 */

public class ExamCanceled extends AbstractDomainEvent {

    private ExamId examId;

    public ExamCanceled(ExamId examId) {
        super();
        this.examId = examId;
    }

    public ExamId examId() {
        return examId;
    }

}