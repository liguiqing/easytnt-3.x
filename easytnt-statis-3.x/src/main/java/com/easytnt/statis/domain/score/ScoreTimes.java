/*
 * Copyright (c) 2016,2018, easytnt All Rights Reserved. 深圳市易考试乐学测评有限公司 版权所有.
 */

package com.easytnt.statis.domain.score;

import java.util.Date;

/**
 * 分数的评次
 *
 * @author Liguiqing
 * @since V3.0
 */

public class ScoreTimes {
    private float speed = 0f; //完成耗时

    private boolean valid = true; //是否有次的评次

    protected ScoreTimes(MarkScore markScore){
        computerSpeed(markScore.getFetchTime(),markScore.getSubmitTime());
        computerValid(markScore.getTimesRequired(),markScore.getError(),markScore.getTotalTimes(),
                markScore.getCurTimes(),markScore.getScore(),markScore.getFinalScore());
    }

    public float speed(){
        return this.speed;
    }

    private void computerSpeed(Date fetchTime, Date submitTime){
        this.speed = (submitTime.getTime() - fetchTime.getTime())/1000;
    }

    private void computerValid(int timesRequired,double error,int totalTimes,
                               int curTimes,double score,double finalScore){

    }

    public boolean isValid(){
        return this.valid;
    }

}