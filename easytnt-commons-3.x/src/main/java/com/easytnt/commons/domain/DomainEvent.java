/*
 * Copyright (c) 2016,2017, easytnt All Rights Reserved. 深圳市易考试乐学测评有限公司 版权所有.
 */

package com.easytnt.commons.domain;

import java.util.Date;

/**
 * 领域事件
 *
 * @author Liguiqing
 * @since V3.0
 */

public interface DomainEvent {
    /**
     * 事件版本号
     * @return
     */
    public int eventVersion();

    /**
     * 事件发生时间
     * @return
     */
    public Date occurredOn();
}