/*
 * Copyright (c) 2016,2018, easytnt All Rights Reserved. 深圳市易考试乐学测评有限公司 版权所有.
 */

package com.easytnt.exam.domain.exam;

/**
 * @author Liguiqing
 * @since V3.0
 */

public enum  ExamStatus {
    Useable(1),Finish(9),Cancel(-1);

    private int val;

    ExamStatus(int val) {
        this.val = val;
    }
}