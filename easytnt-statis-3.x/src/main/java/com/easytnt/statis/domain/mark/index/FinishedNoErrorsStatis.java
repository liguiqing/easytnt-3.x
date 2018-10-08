/*
 * Copyright (c) 2016,2018, easytnt All Rights Reserved. 深圳市易考试乐学测评有限公司 版权所有.
 */

package com.easytnt.statis.domain.mark.index;

import com.easytnt.commons.util.NumberUtilWrapper;
import com.easytnt.statis.domain.mark.ItemStatis;
import com.easytnt.statis.domain.mark.StatisResult;
import com.easytnt.statis.domain.symbol.NoneDataSlashSymbol;
import com.easytnt.statis.domain.symbol.Symbol;

/**
 * 完成量统计，不含问题卷
 *
 * @author Liguiqing
 * @since V3.0
 */

public class FinishedNoErrorsStatis extends AbstractStatisIndex {

    public FinishedNoErrorsStatis() {
        this(new NoneDataSlashSymbol());
    }

    public FinishedNoErrorsStatis(Symbol nodataSymbol) {
        super("有效完成量",nodataSymbol);
    }

    @Override
    protected void computer(ItemStatis target) {
        int total =  target.getValids();
        double totalRate = -1d;
        if(target.hasTotalRequired()){
            totalRate = NumberUtilWrapper.rate(total,target.getTotalRequired());
        }
        target.addStatisResult(new StatisResult(this.getName(),total,totalRate,percentOf(totalRate)));
    }
}