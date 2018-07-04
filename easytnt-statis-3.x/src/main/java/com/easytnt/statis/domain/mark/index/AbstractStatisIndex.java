/*
 * Copyright (c) 2016,2018, easytnt All Rights Reserved. 深圳市易考试乐学测评有限公司 版权所有.
 */

package com.easytnt.statis.domain.mark.index;

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
        if(nodataSymbol == null)
            this.nodataSymbol = new NoneDataSlashSymbol();
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
    public Number getValue() {
        return 0;
    }

    @Override
    public double getRate() {
        return 0d;
    }

    @Override
    public String getPercent() {
        return "/";
    }

    @Override
    public String getName() {
        return this.name;
    }

    public  String getNodataSymbol(){
        return this.nodataSymbol.getSymbol();
    }
}