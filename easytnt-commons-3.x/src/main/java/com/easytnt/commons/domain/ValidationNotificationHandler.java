/*
 * Copyright (c) 2016,2017, easytnt All Rights Reserved. 深圳市易考试乐学测评有限公司 版权所有.
 */

package com.easytnt.commons.domain;

/**
 * 验证通知处理器
 *
 * @author Liguiqing
 * @since V3.0
 */

public interface ValidationNotificationHandler {

    public void handleError(String aNotificationMessage);

    public void handleError(String aNotification, Object anObject);

    public void handleInfo(String aNotificationMessage);

    public void handleInfo(String aNotification, Object anObject);

    public void handleWarning(String aNotificationMessage);

    public void handleWarning(String aNotification, Object anObject);
}