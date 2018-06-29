/*
 * Copyright (c) 2016,2018, easytnt All Rights Reserved. 深圳市易考试乐学测评有限公司 版权所有.
 */

package com.easytnt.statis.domain.mark;

import com.google.common.primitives.Doubles;

import java.util.Date;

/**
 * 评分
 *
 * @author Liguiqing
 * @since V3.0
 */

public class MarkScore {
    private int timesRequired = 1; //评题评次

    private double error = 0d; //评题误差

    private int totalTimes = 1;//考生评题已完成的评次

    private int curTimes = 1; //考生评题当前评次

    private double score = 0d; //考生评题给分

    private double finalScore = 0d; //当次评次对应考生评题的是最终得分

    private Date fetchTime;

    private Date submitTime;

    private ScoreMode mode = ScoreMode.Normal;

    private MarkScore(){

    }

    /**
     * 本次评卷过程耗时(秒)
     * @return
     */
    public double spend(){
        if(this.fetchTime == null || this.submitTime == null)
            return 0d;

        return (this.submitTime.getTime() - this.fetchTime.getTime())/1000;
    }

    public boolean isModeOf(ScoreMode mode){
        return this.mode.equals(mode);
    }

    /**
     * 是否问题卷，分值=-1则为问题卷
     *
     * @return
     */
    public boolean isErrors(){
        return this.score == -1;
    }

    /**
     * 是否大于某个分数
     *
     * @param score
     * @return
     */
    public boolean isOutOf(double score){
        return score >= this.score;
    }

    /**
     * 给分与最终得是否在误差范围外
     *
     * @return
     */
    public boolean isOutOfError(){
        //没有出分，不存在误差
        if(this.finalScore == -1)
            return false;
        //只有多评情况才存在误差
        if(this.timesRequired >= 3 && (this.totalTimes >= 2)){
            return Doubles.compare(this.error,Math.abs(this.finalScore - this.score)) <= 0;
        }
        return false;
    }

    /**
     * 当前评是否有效
     * 当前评次小于等于时有效
     *
     * @return
     */
    public boolean isTimesValid(){
        return this.curTimes <= this.timesRequired;
    }

    /**
     * 最大评次为timesRequired，一般多评时使用
     *
     * @param timesRequired
     * @return
     */
    public boolean isTimesRequiredOf(int timesRequired){
        return this.timesRequired == timesRequired;
    }

    /**
     * 已经完成的总评次totalTimes
     *
     * @param totalTimes
     * @return
     */
    public boolean isTimesTotalOf(int totalTimes){
        return this.totalTimes == totalTimes;
    }

    /**
     * 多评时总评次为totalTimes时仍未出分
     * 一评时返回false
     * @param totalTimes
     * @return
     */
    public boolean isNoFinalScoreAndTimesTotalOf(int totalTimes){
        return (this.timesRequired >= 3) && (this.totalTimes <= totalTimes ) && this.finalScore == -1;
    }

    /**
     * 总评次为totalTimes评次已经出分
     *
     * @param totalTimes
     * @return
     */
    public boolean hasFinalScoreAndTotalTimesOf(int totalTimes){
        return (this.totalTimes <= totalTimes ) && this.finalScore > -1;
    }

    /**
     * 是否已经完成totalTimes评分
     *
     * @param totalTimes
     * @return
     */
    public boolean isFinishedTimesOf(int totalTimes){
        return this.totalTimes >= totalTimes;
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
            this.markScore.finalScore = finalScore;
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

        public Builder mode(ScoreMode mode){
            this.markScore.mode = mode;
            return this;
        }

        public MarkScore build(){
            if(this.markScore.mode == null)
                this.markScore.mode = ScoreMode.Normal;
            return this.markScore;
        }
    }

}