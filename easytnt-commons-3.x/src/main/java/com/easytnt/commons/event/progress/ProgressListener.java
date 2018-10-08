/*
 * Copyright (c) 2016,2018, easytnt All Rights Reserved. 深圳市易考试乐学测评有限公司 版权所有.
 */

package com.easytnt.commons.event.progress;

import java.util.EventListener;

/**
 * 进度监听
 *
 * @author Liguiqing
 * @since V3.0
 */

public interface ProgressListener extends EventListener{

    void update(ProgressEvent event);

    void destry();
}