package com.easytnt.scan.domain.batch;

import com.easytnt.commons.domain.ValueObject;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

/**
 * 扫描怀疑
 *
 * @author Liguiqing
 * @since V3.0
 */

public class BatchScanDoubts extends ValueObject {
    private int examNumberDoubts ;//本批次中考号疑似错误卡数量

    private int kgDoubts;//本批次中客观题疑似错误卡数量

    private int zgOptionalDoubts;//本批次中选做题疑似错误卡数量

    protected BatchScanDoubts(int examNumberDoubts, int kgDoubts, int zgOptionalDoubts) {
        this.examNumberDoubts = examNumberDoubts;
        this.kgDoubts = kgDoubts;
        this.zgOptionalDoubts = zgOptionalDoubts;
    }

    public int examNumberDoubts() {
        return examNumberDoubts;
    }

    public int kgDoubts() {
        return kgDoubts;
    }

    public int zgOptionalDoubts() {
        return zgOptionalDoubts;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("examNumberDoubts", examNumberDoubts)
                .add("kgDoubts", kgDoubts)
                .add("zgOptionalDoubts", zgOptionalDoubts)
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BatchScanDoubts that = (BatchScanDoubts) o;
        return examNumberDoubts == that.examNumberDoubts &&
                kgDoubts == that.kgDoubts &&
                zgOptionalDoubts == that.zgOptionalDoubts;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(examNumberDoubts, kgDoubts, zgOptionalDoubts);
    }

    protected BatchScanDoubts(){}
}