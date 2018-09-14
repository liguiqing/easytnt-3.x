/*
 * Copyright (c) 2016,2018, easytnt All Rights Reserved. 深圳市易考试乐学测评有限公司 版权所有.
 */

package com.easytnt.statis.domain.mark;

import com.easytnt.commons.domain.Identity;
import com.easytnt.share.domain.id.mark.MarkItemId;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.collect.Lists;

import java.util.Collections;
import java.util.List;

/**
 * 评题统计
 *
 * @author Liguiqing
 * @since V3.0
 */

public abstract class ItemStatis {
    private MarkItemId markItemId;

    private String itemName; //评题名称

    private String targetName; //统计目标名称

    private Identity targetId; //统计目标唯一标识

    private int timesRequired;//评次

    private double fullScore;//满分

    private double error;  //误差

    private int totalRequired = -1; //预计完成量，-1表示无预计完成量

    private int total;//完成总量（所有评过的数量，含提交的问题卷）

    private int errors;//问题卷总量

    private double totalScore = 0d; //总分

    private double totalSpend = 0d; //总耗时

    private List<Score> scores = Lists.newArrayList();

    private List<StatisResult> statisResults = Lists.newArrayList();

    public ItemStatis(MarkItemId markItemId, String itemName, int timesRequired, double fullScore, double error) {
        this.markItemId = markItemId;
        this.itemName = itemName;
        this.timesRequired = timesRequired;
        this.fullScore = fullScore;
        this.error = error;
        this.init();
    }

    public ItemStatis(MarkItemId markItemId, String itemName, int timesRequired, double fullScore, double error,
                      int totalRequired) {
        this(markItemId, itemName, timesRequired, fullScore, error);
        this.totalRequired = totalRequired;
    }

    public boolean supports(MarkItemId markItemId){
        return this.markItemId.equals(markItemId);
    }

    public Score getAndIncrement(MarkScore markScore){
        if(!markScore.isSameOf(this.targetId) || markScore.isOutOf(this.fullScore)){
            return null;
        }

        Score score = getScore(markScore);

        this.totalIncreament();

        if(markScore.isErrors()) {
            this.errorsIncreament();
        }else{
            this.sumTotalScore(markScore.getScore());
            this.sumTotalSpend(markScore.spend());
        }

        return score;
    }

    public void addStatisResult(StatisResult result){
        if(this.statisResults.contains(result))
            this.statisResults.remove(result);
        this.statisResults.add(result);
    }

    private Score getScore(MarkScore markScore){
        Score newScore = null;
        for(Score score:this.scores){
            if(score.sameOf(markScore.getScore())) {
                newScore = score;
                break;
            }
        }

        if(newScore == null){
            newScore = new Score(markScore.getScore());
            this.scores.add(newScore);
        }

        newScore.addTimes(markScore);
        return newScore;
    }

    private void init(){
         this.scores = Lists.newArrayList();
         this.statisResults = Lists.newArrayList();
    }

    protected void targetId(Identity targetId) {
        this.targetId = targetId;
    }

    protected void targetName(String targetName) {
        this.targetName = targetName;
    }

    private void sumTotalScore(double score){
        this.totalScore += score;
    }

    private void sumTotalSpend(double spend){
        this.totalSpend += spend;
    }

    public double getAvgScore(){
        return this.totalScore/(this.getValids());
    }

    public boolean hasTotalRequired(){
        return this.totalRequired > -1;
    }

    private void totalIncreament(){
        this.total++;
    }

    private void errorsIncreament(){
        this.errors++;
    }

    public MarkItemId getMarkItemId() {
        return markItemId;
    }

    public String getItemName() {
        return itemName;
    }

    public int getTotalRequired() {
        return totalRequired;
    }

    public int getTimesRequired() {
        return timesRequired;
    }

    public double getFullScore() {
        return fullScore;
    }

    public double getError() {
        return error;
    }

    public int getTotal() {
        return total;
    }

    public int getErrors() {
        return errors;
    }

    public int getValids() {
        return this.total - this.errors;
    }

    public String getName() {
        return targetName;
    }

    public Identity getId() {
        return targetId;
    }

    public double getTotalSpend() {
        return totalSpend;
    }

    public double getTotalScore() {
        return totalScore;
    }

    public List<StatisResult> getStatisResults() {
        return statisResults;
    }

    public List<Score> getScores() {
        return Collections.unmodifiableList(scores);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemStatis statis = (ItemStatis) o;
        return Objects.equal(markItemId, statis.markItemId) &&
                Objects.equal(targetId, statis.targetId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(markItemId, targetId);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("markItemId", markItemId)
                .add("itemName", itemName)
                .add("targetName", targetName)
                .add("targetId", targetId)
                .toString();
    }
}