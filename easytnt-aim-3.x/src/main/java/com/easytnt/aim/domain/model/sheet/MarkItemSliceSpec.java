/*
 * Copyright (c) 2016,2018, easytnt All Rights Reserved. 深圳市易考试乐学测评有限公司 版权所有.
 */

package com.easytnt.aim.domain.model.sheet;

import com.easytnt.commons.domain.ValueObject;

/**
 * 评题图像切片
 * 评题图像组成评题时,以二维方式显示:
 * ---------------------------
 * | (0,0)  | (0,1)   | (0,2) |
 * |        |         |       |
 * |--------------------------|
 * | (1,0)  | (1,1)   | (1,2) |
 * |        |         |       |
 * ----------------------------
 * @author Liguiqing
 * @since V3.0
 */

public class MarkItemSliceSpec extends ValueObject {

    private int axisX;

    private int axisY;

    private SliceSpec sliceSpec;

}