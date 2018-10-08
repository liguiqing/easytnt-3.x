/*
 * Copyright (c) 2016,2018, easytnt All Rights Reserved. 深圳市易考试乐学测评有限公司 版权所有.
 */

package com.easytnt.exam.domain.exam;

/**
 * 考试范围
 *
 * @author Liguiqing
 * @since V3.0
 */

public enum  ExamScope  {
    School(1),Union(2),United(3);

    private int val;

    ExamScope(int val) {
        this.val = val;
    }

    public static ExamScope typeOf(int val){
        if(val == 3)
            return United;
        if(val == 2)
            return Union;
        return School;
    }
}