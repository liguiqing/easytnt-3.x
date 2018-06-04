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

    private boolean examNumberDoubtsDone;//本批次中考号疑似错误是扫描时已经处理过,0－未处理；1－已处理;同一扫描批次中必须全部处理完成才算完成

    protected BatchScanDoubts(int examNumberDoubts, int kgDoubts, int zgOptionalDoubts,boolean examNumberDoubtsDone) {
        this.examNumberDoubts = examNumberDoubts;
        this.kgDoubts = kgDoubts;
        this.zgOptionalDoubts = zgOptionalDoubts;
        this.examNumberDoubtsDone = examNumberDoubtsDone;
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

    public boolean examNumberDoubtsDone() {
        return examNumberDoubtsDone;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("examNumberDoubts", examNumberDoubts)
                .add("kgDoubts", kgDoubts)
                .add("zgOptionalDoubts", zgOptionalDoubts)
                .add("examNumberDoubtsDone", examNumberDoubtsDone)
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BatchScanDoubts that = (BatchScanDoubts) o;
        return examNumberDoubts == that.examNumberDoubts &&
                kgDoubts == that.kgDoubts &&
                zgOptionalDoubts == that.zgOptionalDoubts &&
                examNumberDoubtsDone == that.examNumberDoubtsDone;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(examNumberDoubts, kgDoubts, zgOptionalDoubts, examNumberDoubtsDone);
    }

    protected BatchScanDoubts(){}
}