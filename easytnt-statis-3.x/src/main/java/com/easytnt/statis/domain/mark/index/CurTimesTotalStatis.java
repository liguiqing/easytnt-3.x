package com.easytnt.statis.domain.mark.index;

import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

import com.easytnt.statis.domain.mark.ItemStatis;
import com.easytnt.statis.domain.mark.Score;
import com.easytnt.statis.domain.mark.StatisResult;
import com.easytnt.statis.domain.symbol.NoneDataSlashSymbol;
import com.easytnt.statis.domain.symbol.Symbol;

public class CurTimesTotalStatis extends AbstractStatisIndex{
	int curTimes = 1;

	public CurTimesTotalStatis(int curTimes){
		this("按"+curTimes+"评次统计总数",new NoneDataSlashSymbol(),curTimes);
	}
	
	public CurTimesTotalStatis(String name, Symbol nodataSymbol,int curTimes) {
		 super(name,nodataSymbol);
		 this.curTimes = curTimes;
	}

	@Override
	protected void computer(ItemStatis target) {

		AtomicLong c = new AtomicLong(0);

		
		target.getScores().stream().forEach(score->{
			
			long i =score.getTimes().stream().filter(s->s.curTimes()==curTimes).collect(Collectors.toList()).stream().count();
			c.addAndGet(i);
			
		});
		StatisResult result = new StatisResult(this.getName(),c.get(),0,percentOf(-1));
        target.addStatisResult(result);
	}

}
