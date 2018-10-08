/*
 * Copyright (c) 2016,2018, easytnt All Rights Reserved. 深圳市易考试乐学测评有限公司 版权所有.
 */

package com.easytnt.aim.domain.model.sheet;

import com.easytnt.aim.domain.model.share.Scoredable;

/**
 * 可评分的评题
 * 两种:
 * 1. 客观题  {@link KgMarkItemSpec}
 * 2. 主观题 {@link ZgMarkItemSpec}
 *
 * @author Liguiqing
 * @since V3.0
 */

public abstract class ScoredableMarkItemSpec extends MarkItemSpec implements Scoredable {

    private int seq;//评题顺序

    private ScoreSpec scoreSpec;
}