/*
 * Copyright (c) 2016,2018, easytnt All Rights Reserved. 深圳市易考试乐学测评有限公司 版权所有.
 */

package com.easytnt.statis.domain.mark.index;

import com.easytnt.statis.domain.mark.ItemStatis;
import com.easytnt.statis.domain.mark.StatisResult;
import com.easytnt.statis.domain.symbol.NoneDataSlashSymbol;
import com.easytnt.statis.domain.symbol.Symbol;

/**
 * 平均分统计
 *
 * @author Liguiqing
 * @since V3.0
 */

public class AvgScoreStatis extends AbstractStatisIndex {

    public AvgScoreStatis() {
        this(new NoneDataSlashSymbol());
    }

    public AvgScoreStatis(Symbol nodataSymbol) {
        super("平均分",nodataSymbol);
    }

    @Override
    protected void computer(ItemStatis target) {
        double avg =  target.getAvgScore();
        target.addStatisResult(new StatisResult(this.getName(),avg,0,percentOf(-1)));
    }

}