/*
 * Copyright (c) 2016,2018, easytnt All Rights Reserved. 深圳市易考试乐学测评有限公司 版权所有.
 */

package com.easytnt.aim.domain.model.exam;

import com.easytnt.commons.domain.ValueObject;

/**
 * 考试用试卷
 *
 * @author Liguiqing
 * @since V3.0
 */

public class ExamPaper extends ValueObject {

    private PaperId paperId;

    private String type; //一个考试科目使用多份等值试卷时使用,一般值为A卷,B卷,只使用一张试卷的值为空

    private String name;
}