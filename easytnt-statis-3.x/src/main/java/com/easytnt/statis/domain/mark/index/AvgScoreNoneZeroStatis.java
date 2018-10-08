package com.easytnt.statis.domain.mark.index;

import com.easytnt.statis.domain.mark.ItemStatis;
import com.easytnt.statis.domain.mark.StatisResult;
import com.easytnt.statis.domain.symbol.NoneDataSlashSymbol;
import com.easytnt.statis.domain.symbol.Symbol;

public class AvgScoreNoneZeroStatis  extends AbstractStatisIndex{

	public AvgScoreNoneZeroStatis() {
        this(new NoneDataSlashSymbol());
    }

    public AvgScoreNoneZeroStatis(Symbol nodataSymbol) {
        super("不含0平均分",nodataSymbol);
    }


	@Override
	protected void computer(ItemStatis target) {
		double avg =  target.getAvgScoreNoneZero(target.getScores());
        target.addStatisResult(new StatisResult(this.getName(),avg,0,percentOf(-1)));
	}

}
