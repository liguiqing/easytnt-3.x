/*
 * Copyright (c) 2016,2018, easytnt All Rights Reserved. 深圳市易考试乐学测评有限公司 版权所有.
 */

package com.easytnt.statis.domain.mark;

/**
 * 统计指标
 *
 * @author Liguiqing
 * @since V3.0
 */

public interface StatisIndex {

    void statis(ItemStatis target); //统计

    Number getValue();

    double getRate();

    String getPercent(); //取统计的百分比值

    String getName(); //统计指标名称

    StatisIndex append(StatisIndex next);

    StatisIndex  getNext();

    boolean hasNext();
}