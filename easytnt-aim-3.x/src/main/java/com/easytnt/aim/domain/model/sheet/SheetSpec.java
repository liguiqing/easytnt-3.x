/*
 * Copyright (c) 2016,2018, easytnt All Rights Reserved. 深圳市易考试乐学测评有限公司 版权所有.
 */

package com.easytnt.aim.domain.model.sheet;

import com.easytnt.aim.domain.model.exam.PaperId;
import com.easytnt.commons.domain.Entity;

/**
 * @author Liguiqing
 * @since V3.0
 */

public class SheetSpec extends Entity{

    private SheetSpecId sheetSpecId;

    private PaperId paperId;

    private String title;

    private float fullScore;

    private float zgScore;

    private float kgScore;

    private int pages;



}