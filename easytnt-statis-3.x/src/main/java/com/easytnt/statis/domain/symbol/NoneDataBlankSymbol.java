/*
 * Copyright (c) 2016,2018, easytnt All Rights Reserved. 深圳市易考试乐学测评有限公司 版权所有.
 */

package com.easytnt.statis.domain.symbol;

/**
 * 无数据使用-符号
 *
 * @author Liguiqing
 * @since V3.0
 */

public class NoneDataBlankSymbol extends  Symbol{
    private final static String symbol = "-";

    @Override
    public String getSymbol() {
        return symbol;
    }
}