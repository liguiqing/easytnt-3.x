/*
 * Copyright (c) 2016,2018, easytnt All Rights Reserved. 深圳市易考试乐学测评有限公司 版权所有.
 */

package com.easytnt.statis.domain.mark.index;

import com.easytnt.statis.domain.mark.ItemStatis;
import com.easytnt.statis.domain.symbol.Symbol;

/**
 * 评卷速度统计
 *
 * @author Liguiqing
 * @since V3.0
 */

public class AvgSpeedStatis extends AbstractStatisIndex {

    private double avg = -1d;

    public AvgSpeedStatis(Symbol nodataSymbol) {
        super("平均速度",nodataSymbol);
    }

    @Override
    protected void computer(ItemStatis target) {
        this.avg =  target.getTotalSpend()/target.getValids();
    }

    @Override
    public Number getValue() {
        return this.avg;
    }

}