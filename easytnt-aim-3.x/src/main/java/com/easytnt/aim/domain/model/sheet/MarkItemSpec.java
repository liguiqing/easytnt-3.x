/*
 * Copyright (c) 2016,2018, easytnt All Rights Reserved. 深圳市易考试乐学测评有限公司 版权所有.
 */

package com.easytnt.aim.domain.model.sheet;

import com.easytnt.aim.domain.model.exam.ExamSubjectId;
import com.easytnt.commons.domain.Entity;

/**
 * 评题规格
 *
 * @author Liguiqing
 * @since V3.0
 */

public abstract class MarkItemSpec extends Entity {

    private MarkItemSpecId markItemSpecId;

    private SheetSpecId sheetSpecId;

    private ExamSubjectId examSubjectId;

    private String title;

    private MarkTimes markTimes; //评次

    private MarkItemSliceSpec[][] sliceSpecs;


    public MarkItemSpecId markItemSpecId() {
        return markItemSpecId;
    }

    public SheetSpecId sheetSpecId() {
        return sheetSpecId;
    }

    public ExamSubjectId examSubjectId() {
        return examSubjectId;
    }

    public String title() {
        return title;
    }

    public MarkTimes markTimes() {
        return markTimes;
    }

    public MarkItemSliceSpec[][] sliceSpecs() {
        return sliceSpecs;
    }
}