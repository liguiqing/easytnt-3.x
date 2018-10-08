/*
 * Copyright (c) 2016,2018, easytnt All Rights Reserved. 深圳市易考试乐学测评有限公司 版权所有.
 */

package com.easytnt.aim.domain.model.examinee;

import com.easytnt.commons.domain.ValueObject;

/**
 * 考生报名信息
 *
 * @author Liguiqing
 * @since V3.0
 */

public abstract class  RegisterInfo extends ValueObject{

    private String name;

    private String examNo;
}