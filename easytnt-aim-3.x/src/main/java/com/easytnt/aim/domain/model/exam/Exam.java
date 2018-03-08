/*
 * Copyright (c) 2016,2018, easytnt All Rights Reserved. 深圳市易考试乐学测评有限公司 版权所有.
 */

package com.easytnt.aim.domain.model.exam;

import com.easytnt.commons.domain.Entity;

import java.util.Date;

/**
 * 考试
 *
 * @author Liguiqing
 * @since V3.0
 */

public class Exam extends Entity {
    private ExamId examId;

    private String name;

    private Date startsTime;

    private Date endsTime;

}