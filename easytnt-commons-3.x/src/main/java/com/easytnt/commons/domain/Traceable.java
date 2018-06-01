/*
 * Copyright (c) 2016,2018, easytnt All Rights Reserved. 深圳市易考试乐学测评有限公司 版权所有.
 */

package com.easytnt.commons.domain;

/**
 * 可追踪对象接口
 *
 * @author Liguiqing
 * @since V3.0
 */

public interface Traceable {

    public void trace(Tracer tracer);
}