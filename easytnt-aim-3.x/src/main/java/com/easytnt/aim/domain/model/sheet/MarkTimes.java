/*
 * Copyright (c) 2016,2018, easytnt All Rights Reserved. 深圳市易考试乐学测评有限公司 版权所有.
 */

package com.easytnt.aim.domain.model.sheet;

import com.easytnt.commons.domain.ValueObject;

/**
 * 评次
 *
 * @author Liguiqing
 * @since V3.0
 */

public class MarkTimes extends ValueObject {

    private int requiredTimes; //必须完成的评次

    private int maxTimes; //最大完成的评次,一般情况下,maxTimes=requiredTimes+1;
}