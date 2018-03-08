/*
 * Copyright (c) 2016,2018, easytnt All Rights Reserved. 深圳市易考试乐学测评有限公司 版权所有.
 */

package com.easytnt.aim.domain.model.sheet;

import com.easytnt.commons.domain.ValueObject;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

/**
 * 答题卡图像
 *
 * @author Liguiqing
 * @since V3.0
 */

public class SheetImage extends ValueObject implements  Comparable<SheetImage>{

    private int width;

    private int height;

    private int page;

    private int roate;

    private Storage storage;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SheetImage that = (SheetImage) o;
        return width == that.width &&
                height == that.height &&
                page == that.page &&
                roate == that.roate &&
                Objects.equal(storage, that.storage);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(width, height, page, roate, storage);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("width", width)
                .add("height", height)
                .add("page", page)
                .add("roate", roate)
                .add("storage", storage)
                .toString();
    }

    @Override
    public int compareTo(SheetImage other) {
        return this.page - other.page ;
    }
}