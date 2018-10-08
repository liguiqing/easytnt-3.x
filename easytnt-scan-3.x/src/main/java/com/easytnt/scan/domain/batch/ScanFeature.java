package com.easytnt.scan.domain.batch;

import com.easytnt.commons.domain.ValueObject;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

/**
 * 扫描卡图像处理特征串
 *
 * @author Liguiqing
 * @since V3.0
 */

public class ScanFeature extends ValueObject{

    private String feature;

    public ScanFeature(String feature) {
        this.feature = feature;
    }

    public String feature() {
        return feature;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("feature", feature)
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ScanFeature that = (ScanFeature) o;
        return Objects.equal(feature, that.feature);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(feature);
    }

    //Only 4 persistence
    protected ScanFeature(){}
}