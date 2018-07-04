/*
 * Copyright (c) 2016,2018, easytnt All Rights Reserved. 深圳市易考试乐学测评有限公司 版权所有.
 */

package com.easytnt.statis.domain.mark.index;

import com.easytnt.commons.util.NumberUtilWrapper;
import com.easytnt.statis.domain.mark.ItemStatis;
import com.easytnt.statis.domain.mark.StatisIndex;
import com.easytnt.statis.domain.symbol.NoneDataSlashSymbol;
import com.easytnt.statis.domain.symbol.Symbol;

/**
 * @author Liguiqing
 * @since V3.0
 */

public abstract class AbstractStatisIndex implements StatisIndex {
    private String name;

    private StatisIndex next;

    private Symbol nodataSymbol ; 

    public AbstractStatisIndex(String name,Symbol nodataSymbol) {
        this.nodataSymbol=(nodataSymbol==null?new NoneDataSlashSymbol():nodataSymbol);
        this.name = name;
    }

    public StatisIndex append(StatisIndex next){
        if(this.next != null){
            this.next.append(next);
        }else{
            this.next = next;
        }
        return this;
    }

    protected abstract  void computer(ItemStatis target);

    protected String percentOf(double decimal) {
        if(decimal != -1)
            return NumberUtilWrapper.formattedDecimalToPercentage(decimal, 2);
        return this.getNodataSymbol();
    }

    public StatisIndex  getNext(){
        return this.next;
    }

    @Override
    public void statis(ItemStatis target) {
        computer(target);
        if(this.hasNext()){
            this.next.statis(target);
        }
    }

    @Override
    public boolean hasNext() {
        return this.next != null;
    }

    @Override
    public String getName() {
        return this.name;
    }

    public  String getNodataSymbol(){
        return this.nodataSymbol.getSymbol();
    }
}