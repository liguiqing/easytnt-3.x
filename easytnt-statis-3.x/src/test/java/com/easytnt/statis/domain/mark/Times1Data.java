package com.easytnt.statis.domain.mark;

import com.easytnt.commons.domain.Identity;
import com.easytnt.commons.util.DateUtilWrapper;
import com.easytnt.commons.util.NumberUtilWrapper;
import com.easytnt.share.domain.id.mark.MarkItemId;
import com.google.common.collect.Lists;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Liguiqing
 * @since V3.0
 */

public class Times1Data {

    public List<MarkScore> scoreList = Lists.newArrayList();

    public MarkItemId markItemId = new MarkItemId("MarkerItemId9527");

    public String itemName = "Item1"; //评题名称

    public int timesRequired = 1;//评次

    public double fullScore = 10;//满分

    public int totalSpend = 0;

    public double totalScore = 0;

    private int[] counts = {18632,25321,29354,32118,36132,40125,48564,51624,49524,55698,58975,65632,72135,78223,
            78563,80265,85632,85231,71235,60531,42310};

    private double[] scores = {0,0.5,1,1.5,2,2.5,3,3.5,4,4.5,5,5.5,6,6.5,7,7.5,8,8.5,9,9.5,10};



    private Date timeStart = DateUtilWrapper.yestoday();

    private Date timeEnd = DateUtilWrapper.tomorrow();

    public Times1Data(){

    }

    //完成量
    public int getTotal(){
        int sum = 0;
        for(int i = 0;i<this.counts.length;i++){
            sum += this.counts[i];
        }
        return sum;
    }

    // score=0-10;
    public int getCountOf(double score){
        return counts[(int)(score*2)];
    }


    public double[] getScores(){
        return this.scores;
    }

    public List<MarkScore> toMarkScores(){
        List<MarkScore> mss = new ArrayList<>(getTotal());
        for(int si=0;si<this.scores.length;si++){
            double score = this.scores[si];
            int count = this.getCountOf(score);
            for(int i=1;i<=count;i++){
                Date fetchTime = getFetchTime();
                int difference = NumberUtilWrapper.randomBetween(3, 30);
                Date submitTime  = DateUtilWrapper.addSecondTo(fetchTime,difference);
                MarkScore ms = new MarkScore.Builder().curTimes(1).score(score)
                        .finalScore(score).fetchTime(fetchTime).submitTime(submitTime).build();
                mss.add(ms);
                this.totalSpend += ms.spend();
                this.totalScore += score;
            }
        }

        return mss;
    }

    public List<MarkScore> getMarkScore(int start,int size){
        List<MarkScore> mss = new ArrayList<>(size);
        int end = start * size;
        if(end > this.scores.length){
            end = this.scores.length;
        }
        for(int si=start;si<end;si++){
            double score = this.scores[si];
            int count = this.getCountOf(score);
            for(int i=1;i<=count;i++){
                Date fetchTime = getFetchTime();
                int difference = NumberUtilWrapper.randomBetween(3, 30);
                Date submitTime  = DateUtilWrapper.addSecondTo(fetchTime,difference);
                MarkScore ms = new MarkScore.Builder().curTimes(1).score(score)
                        .finalScore(score).fetchTime(fetchTime).submitTime(submitTime).build();
                mss.add(ms);
                this.totalSpend += ms.spend();
                this.totalScore += score;
            }
        }

        return mss;
    }

    public List<Score> toScores(){
        List<Score> scores = new ArrayList<>();
        for(int si=0;si<this.scores.length;si++) {
            double score = this.scores[si];
            int count = this.getCountOf(score);
            Score score1 = new Score(score);
            for (int i = 1; i <= count; i++) {
                Date fetchTime = getFetchTime();
                int difference = NumberUtilWrapper.randomBetween(3, 30);
                Date submitTime = DateUtilWrapper.addSecondTo(fetchTime, difference);

                MarkScore ms = new MarkScore.Builder().curTimes(1).score(score)
                        .finalScore(score).fetchTime(fetchTime).submitTime(submitTime).build();
                score1.addTimes(ms);
            }
            scores.add(score1);
        }
        return scores;
    }

    //标准差
    public double getSd(){
        int n = this.getTotal();
        double M = this.totalScore/n;
        double sum2 = 0d;
        for(int si=0;si<this.scores.length;si++){
            double x = this.scores[si];
            int count = this.getCountOf(x);
            sum2 += Math.pow(x-M,2)*count;
        }
        double sd2 = Math.sqrt(sum2/(n-1));
        return sd2;
    }

    protected Date getFetchTime(){
        long time = NumberUtilWrapper.randomBetween(timeStart.getTime(), timeEnd.getTime());
        return new Date(time);
    }
}