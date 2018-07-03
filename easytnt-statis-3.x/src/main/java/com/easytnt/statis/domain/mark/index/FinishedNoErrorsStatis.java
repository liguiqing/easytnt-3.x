/*
 * Copyright (c) 2016,2018, easytnt All Rights Reserved. 深圳市易考试乐学测评有限公司 版权所有.
 */

package com.easytnt.statis.domain.mark.index;

import com.easytnt.commons.util.NumberUtilWrapper;
import com.easytnt.statis.domain.mark.ItemStatis;
import com.easytnt.statis.domain.symbol.NoneDataSlashSymbol;
import com.easytnt.statis.domain.symbol.Symbol;

/**
 * 完成量统计，不含问题卷
 *
 * @author Liguiqing
 * @since V3.0
 */

public class FinishedNoErrorsStatis extends AbstractStatisIndex {

    private int total;

    private double totalRate = -1d;

    public FinishedNoErrorsStatis() {
        this(new NoneDataSlashSymbol());
    }

    public FinishedNoErrorsStatis(Symbol nodataSymbol) {
        super("有效完成量",nodataSymbol);
    }

    @Override
    protected void computer(ItemStatis target) {
        this.total =  target.getValids();
        if(target.hasTotalRequired()){
            this.totalRate = NumberUtilWrapper.rate(this.total,target.getTotalRequired());
        }
    }

    @Override
    public Number getValue() {
        return this.total;
    }

    @Override
    public double getRate() {
        return this.totalRate;
    }

    @Override
    public String getPercent() {
        if(this.totalRate != -1)
            return NumberUtilWrapper.formattedDecimalToPercentage(this.totalRate, 2);
        return super.getNodataSymbol();
    }
}