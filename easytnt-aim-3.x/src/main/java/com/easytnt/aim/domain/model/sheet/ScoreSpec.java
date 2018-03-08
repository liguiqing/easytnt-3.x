/*
 * Copyright (c) 2016,2018, easytnt All Rights Reserved. 深圳市易考试乐学测评有限公司 版权所有.
 */

package com.easytnt.aim.domain.model.sheet;

import com.easytnt.commons.domain.ValueObject;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

/**
 * 评分规则
 *
 * @author Liguiqing
 * @since V3.0
 */

public class ScoreSpec extends ValueObject {
    private float score;

    private float allowedError;

    public ScoreSpec(float score, float allowedError) {
        this.score = score;
        this.allowedError = allowedError;
    }

    public float score() {
        return score;
    }

    public float allowedError() {
        return allowedError;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ScoreSpec scoreSpec = (ScoreSpec) o;
        return Float.compare(scoreSpec.score, score) == 0 &&
                Float.compare(scoreSpec.allowedError, allowedError) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(score, allowedError);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("score", score)
                .add("allowedError", allowedError)
                .toString();
    }
}