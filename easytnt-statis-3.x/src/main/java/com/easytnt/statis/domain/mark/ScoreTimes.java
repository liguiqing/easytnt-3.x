/*
 * Copyright (c) 2016,2018, easytnt All Rights Reserved. 深圳市易考试乐学测评有限公司 版权所有.
 */

package com.easytnt.statis.domain.mark;

/**
 * 分数的评次
 *
 * @author Liguiqing
 * @since V3.0
 */

public class ScoreTimes {
    private double spend = 0f; //完成耗时

    private boolean valid = true; //是否有次的评次

    protected ScoreTimes(MarkScore markScore){
        this.spend = markScore.spend();
        this.valid = !markScore.isOutOfError();
    }

    public double spend(){
        return this.spend;
    }

    public boolean isValid(){
        return this.valid;
    }

}