/*
 * Copyright (c) 2016,2018, easytnt All Rights Reserved. 深圳市易考试乐学测评有限公司 版权所有.
 */

package com.easytnt.statis.domain.mark.index;

import com.easytnt.statis.domain.mark.ItemStatis;
import com.easytnt.statis.domain.mark.Score;
import com.easytnt.statis.domain.mark.StatisResult;
import com.easytnt.statis.domain.symbol.NoneDataSlashSymbol;
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

    public SdScoreStatis() {
        this(new NoneDataSlashSymbol());
    }

    public SdScoreStatis(Symbol nodataSymbol) {
        super("标准差",nodataSymbol);
    }

    @Override
    protected void computer(ItemStatis target) {
        double avg =  target.getAvgScore();
        int total = target.getValids();
        double sum = 0d;

        List<Score> scores = target.getScores();
        for(Score score:scores){
            int total1 = score.size();
            sum += Math.pow(score.getValue()-avg,2)*total1;
        }
        double sd = Math.sqrt(sum/(total-1));

        target.addStatisResult(new StatisResult(this.getName(),sd,0,percentOf(-1)));
    }
}