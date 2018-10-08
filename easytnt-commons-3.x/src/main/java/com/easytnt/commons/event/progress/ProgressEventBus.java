/*
 * Copyright (c) 2016,2018, easytnt All Rights Reserved. 深圳市易考试乐学测评有限公司 版权所有.
 */

package com.easytnt.commons.event.progress;

import com.google.common.eventbus.EventBus;

/**
 * @author Liguiqing
 * @since V3.0
 */

public class ProgressEventBus {

    static EventBus eventBus = new EventBus("ProgressEventBuss");

    public static String register(ProgressListener listener){
        //eventBus.register(listener);
        return listener.toString();
    }

}