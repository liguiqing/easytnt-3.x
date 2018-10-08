/*
 * Copyright (c) 2016,2018, easytnt All Rights Reserved. 深圳市易考试乐学测评有限公司 版权所有.
 */

package com.easytnt.aim.domain.model.mark;

/**
 * 评卷员角色
 * Abitrator:仲裁者,多评无法评出时的裁决者
 * Normal:一般评卷员
 * Error:异常评题处理者
 *
 * @author Liguiqing
 * @since V3.0
 */

public enum MarkerRole {
    Abitrator,Normal,Error

}