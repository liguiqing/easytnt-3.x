/*
 * Copyright (c) 2016,2018, easytnt All Rights Reserved. 深圳市易考试乐学测评有限公司 版权所有.
 */

package com.easytnt.aim.domain.model.sheet;

import com.easytnt.commons.domain.ValueObject;

/**
 * 评题图像的切片规格
 * 所有坐标属性值都是绝对值,即与答题卡原始图像的顶点的相对值
 *
 * @author Liguiqing
 * @since V3.0
 */

public class SliceSpec extends ValueObject {

    private int page;

    private int width;

    private int height;

    private int top;

    private int left;

    private int seq;

}