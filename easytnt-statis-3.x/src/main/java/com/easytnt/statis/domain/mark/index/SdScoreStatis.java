/*
 * Copyright (c) 2016,2018, easytnt All Rights Reserved. 深圳市易考试乐学测评有限公司 版权所有.
 */

package com.easytnt.statis.domain.mark.index;

import com.easytnt.statis.domain.mark.ItemStatis;
import com.easytnt.statis.domain.mark.Score;
import com.easytnt.statis.domain.symbol.Symbol;

import java.math.BigDecimal;
import java.util.List;

/**
 * 评分标准差统计
 *
 * @author Liguiqing
 * @since V3.0
 */

public class SdScoreStatis extends AbstractStatisIndex {

    private double sd = 0d;

    public SdScoreStatis(Symbol nodataSymbol) {
        super("平均分",nodataSymbol);
    }

    @Override
    protected void computer(ItemStatis target) {
        double avg =  target.getAvgScore();
        int total = target.getValids();
        double sum = 0d;

        List<Score> scores = target.getScores();
        for(Score score:scores){
            sum += score.quadraticSum(avg);
        }
        this.sd = new BigDecimal(Math.sqrt(sum/(total-1))).setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    @Override
    public Number getValue() {
        return this.sd;
    }

}