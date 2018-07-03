/*
 * Copyright (c) 2016,2018, easytnt All Rights Reserved. 深圳市易考试乐学测评有限公司 版权所有.
 */

package com.easytnt.statis.domain.mark;

import com.google.common.base.Objects;
import com.google.common.collect.Lists;

import java.util.List;

/**
 * 评分分值
 *
 * @author Liguiqing
 * @since V3.0
 */

public class Score {
    private double value;

    private List<ScoreTimes> times = Lists.newArrayList();

    private double totalSpend = 0;


    protected Score(double value) {
        this.value = value;
    }

    protected void addTimes(MarkScore markScore){
        if(!markScore.isScoreOf(this.value))
            return;

        ScoreTimes times = new ScoreTimes(markScore);
        this.times.add(times);
        this.totalSpend += times.spend();
    }



    protected boolean sameOf(double value){
        return this.value == value;
    }

    public int size(){
        return this.times.size();
    }

    public double getTotalSpend() {
        return totalSpend;
    }

    public double getValue() {
        return this.value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Score score = (Score) o;
        return Double.compare(score.value, value) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }
}