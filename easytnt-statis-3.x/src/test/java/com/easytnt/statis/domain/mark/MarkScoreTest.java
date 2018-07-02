package com.easytnt.statis.domain.mark;

import com.easytnt.commons.util.DateUtilWrapper;
import com.easytnt.statis.domain.mark.MarkScore;
import org.junit.Test;

import static org.junit.Assert.*;

import java.util.Date;

/**
 * @author Liguiqing
 * @since V3.0
 */

public class MarkScoreTest {

    @Test
    public void testIsTimesValid()throws Exception{
        Date fetchTime  = DateUtilWrapper.now();
        Date submitTime = DateUtilWrapper.addSecondTo(fetchTime, 10);
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
        assertTrue(score.isTimesValid());
        assertTrue(score.isFinishedTimesOf(1));
        assertEquals(10,score.spend(),0d);
        assertTrue(score.isTimesRequiredOf(1));
        assertTrue(score.isTimesTotalOf(1));
    }

    @Test
    public void testSpend()throws Exception{
        Date fetchTime  = DateUtilWrapper.now();
        Date submitTime = DateUtilWrapper.addSecondTo(fetchTime, 10);
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
        assertEquals(10,score.spend(),0d);
    }

    @Test
    public void testIsErrors()throws Exception{
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

        assertFalse(score.isErrors());

        score = new MarkScore.Builder()
                .score(20d)
                .curTimes(2)
                .error(3d)
                .finalScore(-1d)
                .fetchTime(fetchTime)
                .submitTime(submitTime)
                .timesRequired(3)
                .totalTimes(2)
                .build();
        assertFalse(score.isErrors());

        score = new MarkScore.Builder()
                .score(10d)
                .curTimes(1)
                .error(3d)
                .finalScore(-1d)
                .fetchTime(fetchTime)
                .submitTime(submitTime)
                .timesRequired(3)
                .totalTimes(2)
                .build();
        assertFalse(score.isErrors());

        score = new MarkScore.Builder()
                .score(10d)
                .curTimes(1)
                .error(0d)
                .finalScore(-1d)
                .fetchTime(fetchTime)
                .submitTime(submitTime)
                .timesRequired(1)
                .totalTimes(1)
                .build();
        assertFalse(score.isErrors());

        score = new MarkScore.Builder()
                .score(-1d)
                .curTimes(1)
                .error(0d)
                .finalScore(-1d)
                .fetchTime(fetchTime)
                .submitTime(submitTime)
                .timesRequired(1)
                .totalTimes(1)
                .build();
        assertTrue(score.isErrors());
    }

    @Test
    public void testHasFinalScoreAndTotalTimesOf()throws Exception{
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

        assertTrue(score.hasFinalScoreAndTotalTimesOf(1));
        assertTrue(score.hasFinalScoreAndTotalTimesOf(2));
        assertFalse(score.hasFinalScoreAndTotalTimesOf(0));

        score = new MarkScore.Builder()
                .score(20d)
                .curTimes(2)
                .error(3d)
                .finalScore(-1d)
                .fetchTime(fetchTime)
                .submitTime(submitTime)
                .timesRequired(3)
                .totalTimes(2)
                .build();
        assertFalse(score.hasFinalScoreAndTotalTimesOf(1));
        assertFalse(score.hasFinalScoreAndTotalTimesOf(2));
        assertFalse(score.hasFinalScoreAndTotalTimesOf(0));

        score = new MarkScore.Builder()
                .score(20d)
                .curTimes(1)
                .error(3d)
                .finalScore(18.5d)
                .fetchTime(fetchTime)
                .submitTime(submitTime)
                .timesRequired(3)
                .totalTimes(2)
                .build();
        assertFalse(score.hasFinalScoreAndTotalTimesOf(1));
        assertTrue(score.hasFinalScoreAndTotalTimesOf(2));
        assertFalse(score.hasFinalScoreAndTotalTimesOf(0));

        score = new MarkScore.Builder()
                .score(20d)
                .curTimes(1)
                .error(3d)
                .finalScore(18.5d)
                .fetchTime(fetchTime)
                .submitTime(submitTime)
                .timesRequired(3)
                .totalTimes(2)
                .build();
        assertFalse(score.hasFinalScoreAndTotalTimesOf(1));
        assertTrue(score.hasFinalScoreAndTotalTimesOf(2));
        assertFalse(score.hasFinalScoreAndTotalTimesOf(0));

        score = new MarkScore.Builder()
                .score(20d)
                .curTimes(1)
                .error(3d)
                .finalScore(18.5d)
                .fetchTime(fetchTime)
                .submitTime(submitTime)
                .timesRequired(4)
                .totalTimes(2)
                .build();
        assertFalse(score.hasFinalScoreAndTotalTimesOf(0));
        assertFalse(score.hasFinalScoreAndTotalTimesOf(1));
        assertTrue(score.hasFinalScoreAndTotalTimesOf(2));

        score = new MarkScore.Builder()
                .score(20d)
                .curTimes(2)
                .error(3d)
                .finalScore(18.5d)
                .fetchTime(fetchTime)
                .submitTime(submitTime)
                .timesRequired(4)
                .totalTimes(2)
                .build();
        assertFalse(score.hasFinalScoreAndTotalTimesOf(0));
        assertFalse(score.hasFinalScoreAndTotalTimesOf(1));
        assertTrue(score.hasFinalScoreAndTotalTimesOf(2));

        score = new MarkScore.Builder()
                .score(20d)
                .curTimes(3)
                .error(3d)
                .finalScore(18.5d)
                .fetchTime(fetchTime)
                .submitTime(submitTime)
                .timesRequired(4)
                .totalTimes(3)
                .build();
        assertFalse(score.hasFinalScoreAndTotalTimesOf(0));
        assertFalse(score.hasFinalScoreAndTotalTimesOf(1));
        assertFalse(score.hasFinalScoreAndTotalTimesOf(2));
        assertTrue(score.hasFinalScoreAndTotalTimesOf(3));

        score = new MarkScore.Builder()
                .score(20d)
                .curTimes(3)
                .error(3d)
                .finalScore(-1d)
                .fetchTime(fetchTime)
                .submitTime(submitTime)
                .timesRequired(4)
                .totalTimes(3)
                .build();
        assertFalse(score.hasFinalScoreAndTotalTimesOf(0));
        assertFalse(score.hasFinalScoreAndTotalTimesOf(1));
        assertFalse(score.hasFinalScoreAndTotalTimesOf(2));
        assertFalse(score.hasFinalScoreAndTotalTimesOf(3));
    }


    @Test
    public void testIsNoFinalScoreAndTimesTotalOf()throws Exception{
        Date fetchTime  = DateUtilWrapper.now();
        Date submitTime  = DateUtilWrapper.addSecondTo(fetchTime,10);

        MarkScore score = new MarkScore.Builder()
                .score(-1d)
                .curTimes(1)
                .error(0d)
                .finalScore(-1d)
                .fetchTime(fetchTime)
                .submitTime(submitTime)
                .timesRequired(1)
                .totalTimes(1)
                .build();

        assertFalse(score.isNoFinalScoreAndTimesTotalOf(1));
        assertFalse(score.isNoFinalScoreAndTimesTotalOf(2));
        assertFalse(score.isNoFinalScoreAndTimesTotalOf(0));

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

        assertFalse(score.isNoFinalScoreAndTimesTotalOf(1));
        assertFalse(score.isNoFinalScoreAndTimesTotalOf(2));
        assertFalse(score.isNoFinalScoreAndTimesTotalOf(0));
    }


    @Test
    public void testIsFinishedTimesOf()throws Exception{
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

        assertTrue(score.isFinishedTimesOf(1));
        assertFalse(score.isFinishedTimesOf(2));
        assertTrue(score.isFinishedTimesOf(0));

        score = new MarkScore.Builder()
                .score(20d)
                .curTimes(2)
                .error(3d)
                .finalScore(-1d)
                .fetchTime(fetchTime)
                .submitTime(submitTime)
                .timesRequired(3)
                .totalTimes(2)
                .build();
        assertTrue(score.isFinishedTimesOf(1));
        assertTrue(score.isFinishedTimesOf(2));
        assertTrue(score.isFinishedTimesOf(0));
        assertFalse(score.isFinishedTimesOf(3));


        score = new MarkScore.Builder()
                .score(20d)
                .curTimes(2)
                .error(3d)
                .finalScore(18.5d)
                .fetchTime(fetchTime)
                .submitTime(submitTime)
                .timesRequired(4)
                .totalTimes(2)
                .build();
        assertTrue(score.isFinishedTimesOf(1));
        assertTrue(score.isFinishedTimesOf(2));
        assertTrue(score.isFinishedTimesOf(0));
        assertFalse(score.isFinishedTimesOf(3));

        score = new MarkScore.Builder()
                .score(20d)
                .curTimes(3)
                .error(3d)
                .finalScore(18.5d)
                .fetchTime(fetchTime)
                .submitTime(submitTime)
                .timesRequired(4)
                .totalTimes(3)
                .build();
        assertTrue(score.isFinishedTimesOf(1));
        assertTrue(score.isFinishedTimesOf(2));
        assertTrue(score.isFinishedTimesOf(0));
        assertTrue(score.isFinishedTimesOf(3));
        assertFalse(score.isFinishedTimesOf(4));

    }

    @Test
    public void testIsOutOfError()throws Exception{
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

        assertFalse(score.isOutOfError());

        score = new MarkScore.Builder()
                .score(10d)
                .curTimes(1)
                .error(0d)
                .fetchTime(fetchTime)
                .submitTime(submitTime)
                .timesRequired(1)
                .totalTimes(1)
                .build();
        assertFalse(score.isOutOfError());

        score = new MarkScore.Builder()
                .score(10d)
                .curTimes(1)
                .error(3d)
                .finalScore(10d)
                .fetchTime(fetchTime)
                .submitTime(submitTime)
                .timesRequired(3)
                .totalTimes(2)
                .build();

        assertFalse(score.isOutOfError());

        score = new MarkScore.Builder()
                .score(15d)
                .curTimes(1)
                .error(3d)
                .finalScore(10d)
                .fetchTime(fetchTime)
                .submitTime(submitTime)
                .timesRequired(3)
                .totalTimes(2)
                .build();

        assertTrue(score.isOutOfError());

        score = new MarkScore.Builder()
                .score(15d)
                .curTimes(2)
                .error(3d)
                .fetchTime(fetchTime)
                .submitTime(submitTime)
                .timesRequired(3)
                .totalTimes(2)
                .build();

        assertTrue(score.isOutOfError());

        score = new MarkScore.Builder()
                .score(15d)
                .curTimes(2)
                .error(3d)
                .finalScore(10d)
                .fetchTime(fetchTime)
                .submitTime(submitTime)
                .timesRequired(3)
                .totalTimes(2)
                .build();

        assertTrue(score.isOutOfError());

        score = new MarkScore.Builder()
                .score(15d)
                .curTimes(3)
                .error(3d)
                .finalScore(10d)
                .fetchTime(fetchTime)
                .submitTime(submitTime)
                .timesRequired(4)
                .totalTimes(3)
                .build();

        assertTrue(score.isOutOfError());

        score = new MarkScore.Builder()
                .score(15d)
                .curTimes(1)
                .error(3d)
                .finalScore(10d)
                .fetchTime(fetchTime)
                .submitTime(submitTime)
                .timesRequired(4)
                .totalTimes(3)
                .build();

        assertTrue(score.isOutOfError());

        score = new MarkScore.Builder()
                .score(15d)
                .curTimes(2)
                .error(3d)
                .finalScore(10d)
                .fetchTime(fetchTime)
                .submitTime(submitTime)
                .timesRequired(4)
                .totalTimes(3)
                .build();

        assertTrue(score.isOutOfError());

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
        assertTrue(score.isOutOfError());

    }
}