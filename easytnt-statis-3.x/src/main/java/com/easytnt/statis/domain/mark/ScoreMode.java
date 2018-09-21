/*
 * Copyright (c) 2016,2018, easytnt All Rights Reserved. 深圳市易考试乐学测评有限公司 版权所有.
 */

package com.easytnt.statis.domain.mark;

/**
 * 评分方式
 * Normal:正常评分;Errored：处理问题卷;Arbitrate:仲裁给分；Sampling：抽查给分
 * @author Liguiqing
 * @since V3.0
 */

public enum ScoreMode {
	//评卷类类型标识：1-识别(Mechine);2-Formal-正评;3-Arbiter-仲裁/抽查给分;4-Error-提问题卷;5-Borker-问题卷处理;6-ReFormal-回评;7-Learnning-试评;8-Self-自评;9-ForceFormal-发回重评;10-Monitor-质控
	Mechine(1),Formal(2),Arbiter(3),Error(4),Borker(5),ReFormal(6),Learnning(7),Self(8),ForceFormal(9),Monitor(10);

    private int way;

    private ScoreMode(int way){
        this.way = way;
    }
    
    public static ScoreMode wayOf(int way){
    	switch (way) {
		case 1:
			return Mechine;
		case 2:
			return Formal;
		case 3:
			return Arbiter;
		case 4:
			return Error;
		case 5:
			return Borker;
		case 6:
			return ReFormal;
		case 7:
			return Learnning;
		case 8:
			return Self;
		case 9:
			return ForceFormal;
		case 10:
			return Monitor;
		}
		return null;
    }
}