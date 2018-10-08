/*
 * Copyright (c) 2016,2018, easytnt All Rights Reserved. 深圳市易考试乐学测评有限公司 版权所有.
 */

package com.easytnt.aim.domain.model.sheet;

import com.easytnt.commons.domain.ValueObject;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

/**
 * 答题卡图像存储
 *
 * @author Liguiqing
 * @since V3.0
 */

public class Storage extends ValueObject {

    private String relativeUrl;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Storage storage = (Storage) o;
        return Objects.equal(relativeUrl, storage.relativeUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(relativeUrl);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this).add("url",this.relativeUrl)
                .toString();
    }
}