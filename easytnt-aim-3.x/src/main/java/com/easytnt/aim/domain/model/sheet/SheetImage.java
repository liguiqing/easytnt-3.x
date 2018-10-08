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

    private int rotate;

    private Storage storage;

    private String coordinate;//答题卡图片中坐标结构信息,一般为Json或者xml

    public SheetImage(int width, int height, int page, Storage storage) {
        this(width,height,page,0,storage);
    }

    public SheetImage(int width, int height, int page, int rotate, Storage storage) {
        this.width = width;
        this.height = height;
        this.page = page;
        this.rotate = rotate;
        this.storage = storage;
    }

    public void updateCoordinate(String coordinate){
        this.coordinate = coordinate;
    }

    public int width() {
        return width;
    }

    public int height() {
        return height;
    }

    public int page() {
        return page;
    }

    public int rotate() {
        return rotate;
    }

    public Storage storage() {
        return storage;
    }

    public String coordinate() {
        return coordinate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SheetImage that = (SheetImage) o;
        return width == that.width &&
                height == that.height &&
                page == that.page &&
                rotate == that.rotate &&
                Objects.equal(storage, that.storage);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(width, height, page, rotate, storage);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("width", width)
                .add("height", height)
                .add("page", page)
                .add("rotate", rotate)
                .add("storage", storage)
                .toString();
    }

    @Override
    public int compareTo(SheetImage other) {
        return this.page - other.page ;
    }
}