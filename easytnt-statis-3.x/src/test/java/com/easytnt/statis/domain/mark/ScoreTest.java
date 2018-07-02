package com.easytnt.statis.domain.mark;

import com.easytnt.commons.util.DateUtilWrapper;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

/**
 * Copyright (c) 2016,$today.year, 深圳市易考试乐学测评有限公司
 **/
public class ScoreTest {

    @Test
    public void addTimes() {
        Score score = new Score(11d);
        Date fetchTime  = DateUtilWrapper.now();
        Date submitTime  = DateUtilWrapper.addSecondTo(fetchTime,10);

        MarkScore mscore = new MarkScore.Builder()
                .score(10d)
                .curTimes(1)
                .error(0d)
                .finalScore(10d)
                .fetchTime(fetchTime)
                .submitTime(submitTime)
                .timesRequired(1)
                .totalTimes(1)
                .build();

        score.addTimes(mscore);
        assertEquals(score.getTotalSpend(),0,0);

        mscore = new MarkScore.Builder()
                .score(11d)
                .curTimes(1)
                .error(0d)
                .finalScore(10d)
                .fetchTime(fetchTime)
                .submitTime(submitTime)
                .timesRequired(1)
                .totalTimes(1)
                .build();

        score.addTimes(mscore);
        assertEquals(score.getTotalSpend(),10,0);
    }

    @Test
    public void sameOf() {
        Score score = new Score(11d);
        assertTrue(score.sameOf(11d));
        assertFalse(score.sameOf(10d));
        assertFalse(score.sameOf(12d));
        assertFalse(score.sameOf(112d));
    }

    @Test
    public void quadraticSum() {
        Score score = new Score(11d);
        Date fetchTime  = DateUtilWrapper.now();
        Date submitTime  = DateUtilWrapper.addSecondTo(fetchTime,10);

        MarkScore mscore = new MarkScore.Builder()
                .score(10d)
                .curTimes(1)
                .error(0d)
                .finalScore(10d)
                .fetchTime(fetchTime)
                .submitTime(submitTime)
                .timesRequired(1)
                .totalTimes(1)
                .build();

        score.addTimes(mscore);
        mscore = new MarkScore.Builder()
                .score(11d)
                .curTimes(1)
                .error(0d)
                .finalScore(10d)
                .fetchTime(fetchTime)
                .submitTime(submitTime)
                .timesRequired(1)
                .totalTimes(1)
                .build();

        score.addTimes(mscore);
        assertEquals(0,score.quadraticSum(11),0);
        mscore = new MarkScore.Builder()
                .score(11d)
                .curTimes(1)
                .error(0d)
                .finalScore(10d)
                .fetchTime(fetchTime)
                .submitTime(submitTime)
                .timesRequired(1)
                .totalTimes(1)
                .build();

        score.addTimes(mscore);
        assertEquals(2,score.quadraticSum(10),0);
        assertEquals(2,score.quadraticSum(12),0);
        assertEquals(8,score.quadraticSum(9),0);
        assertEquals(8,score.quadraticSum(13),0);
    }
}