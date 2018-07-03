package com.easytnt.statis.domain.mark;

import com.easytnt.commons.util.DateUtilWrapper;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

/**
 * Copyright (c) 2016,$today.year, 深圳市易考试乐学测评有限公司
 **/
public class ScoreTimesTest {

    @Test
    public void speed() {

        Date fetchTime  = DateUtilWrapper.now();
        Date submitTime  = DateUtilWrapper.addSecondTo(fetchTime,10);

        MarkScore score = new MarkScore.Builder()
                .score(10d)
                .curTimes(1)
                .error(0d)
                .finalScore(10d)
                .fetchTime(fetchTime)
                .submitTime(submitTime)
                .timesRequired(1)
                .totalTimes(1)
                .build();
        ScoreTimes st = new ScoreTimes(score);
        assertEquals(10,st.spend(),0);

        fetchTime  = DateUtilWrapper.now();
        submitTime  = DateUtilWrapper.addSecondTo(fetchTime,0);

        score = new MarkScore.Builder()
                .score(10d)
                .curTimes(1)
                .error(0d)
                .finalScore(10d)
                .fetchTime(fetchTime)
                .submitTime(submitTime)
                .timesRequired(1)
                .totalTimes(1)
                .build();
        st = new ScoreTimes(score);
        assertEquals(0,st.spend(),0);

        fetchTime  = DateUtilWrapper.now();
        submitTime  = DateUtilWrapper.addSecondTo(fetchTime,1);

        score = new MarkScore.Builder()
                .score(10d)
                .curTimes(1)
                .error(0d)
                .finalScore(10d)
                .fetchTime(fetchTime)
                .submitTime(submitTime)
                .timesRequired(1)
                .totalTimes(1)
                .build();
        st = new ScoreTimes(score);
        assertEquals(1,st.spend(),0);
    }

    @Test
    public void isValid() {
        Date fetchTime  = DateUtilWrapper.now();
        Date submitTime  = DateUtilWrapper.addSecondTo(fetchTime,10);

        MarkScore score = new MarkScore.Builder()
                .score(10d)
                .curTimes(1)
                .error(0d)
                .finalScore(10d)
                .fetchTime(fetchTime)
                .submitTime(submitTime)
                .timesRequired(1)
                .totalTimes(1)
                .build();
        ScoreTimes st = new ScoreTimes(score);
        assertTrue(st.isValid());

        score = new MarkScore.Builder()
                .score(10d)
                .curTimes(1)
                .error(3d)
                .finalScore(-1d)
                .fetchTime(fetchTime)
                .submitTime(submitTime)
                .timesRequired(3)
                .totalTimes(1)
                .build();
        st = new ScoreTimes(score);
        assertTrue(st.isValid());

        score = new MarkScore.Builder()
                .score(10d)
                .curTimes(2)
                .error(3d)
                .finalScore(-1d)
                .fetchTime(fetchTime)
                .submitTime(submitTime)
                .timesRequired(3)
                .totalTimes(2)
                .build();
        st = new ScoreTimes(score);
        assertFalse(st.isValid());

        score = new MarkScore.Builder()
                .score(10d)
                .curTimes(2)
                .error(3d)
                .finalScore(8d)
                .fetchTime(fetchTime)
                .submitTime(submitTime)
                .timesRequired(3)
                .totalTimes(2)
                .build();
        st = new ScoreTimes(score);
        assertTrue(st.isValid());

        score = new MarkScore.Builder()
                .score(10d)
                .curTimes(2)
                .error(3d)
                .finalScore(7d)
                .fetchTime(fetchTime)
                .submitTime(submitTime)
                .timesRequired(3)
                .totalTimes(2)
                .build();
        st = new ScoreTimes(score);
        assertFalse(st.isValid());
    }
}