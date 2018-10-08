/*
 * Copyright (c) 2016,2018, easytnt All Rights Reserved. 深圳市易考试乐学测评有限公司 版权所有.
 */

package com.easytnt.aim.domain.model.sheet;

import com.easytnt.commons.domain.ValueObject;

/**
 * 给分点
 *
 * @author Liguiqing
 * @since V3.0
 */

public abstract class ScorePoint extends ValueObject {

    private String title;

    private ScoreSpec scoreSpec;

    private float[] validScores;

    private int seq;


}