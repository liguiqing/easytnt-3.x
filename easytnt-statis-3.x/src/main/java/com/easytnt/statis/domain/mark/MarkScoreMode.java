/*
 * Copyright (c) 2016,2018, easytnt All Rights Reserved. 深圳市易考试乐学测评有限公司 版权所有.
 */

package com.easytnt.statis.domain.mark;

/**
 * 评分方式
 * Normal:正常评分;Errored：处理问题卷;Arbitrate:仲裁给分；Sampling：抽查给分
 * @author Liguiqing
 * @since V3.0
 */

public enum MarkScoreMode {
    Normal(1),Errored(2),Arbitrate(3),Sampling(4);

    private int way;

    private MarkScoreMode(int way){
        this.way = way;
    }
}