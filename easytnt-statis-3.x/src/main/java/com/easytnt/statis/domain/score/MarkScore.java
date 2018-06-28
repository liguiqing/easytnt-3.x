/*
 * Copyright (c) 2016,2018, easytnt All Rights Reserved. 深圳市易考试乐学测评有限公司 版权所有.
 */

package com.easytnt.statis.domain.score;

import com.google.common.primitives.Doubles;

import java.util.Date;

/**
 * 评分
 *
 * @author Liguiqing
 * @since V3.0
 */

public class MarkScore {
    private int timesRequired; //评题评次

    private double error; //评题误差

    private int totalTimes;

    private int curTimes; //当前评次

    private double score; //给分

    private double finalScore; //当次评次对应考生评题的是最终得分

    private Date fetchTime;

    private Date submitTime;

    private MarkScore(){

    }

    public boolean isErros(){//是否问题卷，分值=-1则为问题卷
        return this.score == -1;
    }

    public boolean isOutOf(double score){
        return score >= this.score;
    }

    /**
     * 给分与最终得是否在误差范围外
     *
     * @return
     */
    public boolean isOutOfError(){
        if(this.timesRequired >= 3 ){
            if(this.totalTimes >= 2 && this.finalScore > -1)
                return Doubles.compare(this.error,Math.abs(this.finalScore - this.score)) <= 0;
        }
        return false;
    }

    public boolean isTimesValid(){
        return this.curTimes <= this.timesRequired;
    }

    public boolean isTimesRequiredOf(int timesRequired){
        return this.timesRequired == timesRequired;
    }

    public boolean isTimesTotalOf(int totalTimes){
        return this.totalTimes == totalTimes;
    }

    public boolean isNoFinalScoreAndTimesTotalOf(int totalTimes){
        return (this.timesRequired >= 3) && (this.totalTimes <= totalTimes ) && this.finalScore == -1;
    }

    public boolean isFinalScoreAndTotalTimesOf(int totalTimes){
        return (this.totalTimes <= totalTimes ) && this.finalScore >= -1;
    }

    public boolean isFinishedTimesOf(int totalTimes){
        return this.totalTimes == totalTimes;
    }

    public int getTimesRequired() {
        return timesRequired;
    }

    public double getError() {
        return error;
    }

    public int getTotalTimes() {
        return totalTimes;
    }

    public int getCurTimes() {
        return curTimes;
    }

    public double getScore() {
        return score;
    }

    public double getFinalScore() {
        return finalScore;
    }

    public Date getFetchTime() {
        return fetchTime;
    }

    public Date getSubmitTime() {
        return submitTime;
    }

    public static class Builder{
        private MarkScore markScore;

        public Builder(){
            this.markScore = new MarkScore();
        }

        public Builder timesRequired(int timesRequired){
            this.markScore.timesRequired = timesRequired;
            return this;
        }

        public Builder error(double error){
            this.markScore.error = error;
            return this;
        }

        public Builder totalTimes(int totalTimes){
            this.markScore.totalTimes = totalTimes;
            return this;
        }

        public Builder curTimes(int curTimes){
            this.markScore.curTimes = curTimes;
            return this;
        }


        public Builder score(double score){
            this.markScore.score = score;
            return this;
        }

        public Builder finalScore(double finalScore){
            this.markScore.score = finalScore;
            return this;
        }

        public Builder fetchTime(Date fetchTime){
            this.markScore.fetchTime = fetchTime;
            return this;
        }

        public Builder submitTime(Date submitTime){
            this.markScore.submitTime = submitTime;
            return this;
        }

        public MarkScore build(){
            return this.markScore;
        }
    }

}