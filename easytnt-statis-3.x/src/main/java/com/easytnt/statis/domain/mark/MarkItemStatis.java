/*
 * Copyright (c) 2016,2018, easytnt All Rights Reserved. 深圳市易考试乐学测评有限公司 版权所有.
 */

package com.easytnt.statis.domain.mark;

import java.util.List;

import com.easytnt.share.domain.id.PersonId;
import com.easytnt.share.domain.id.mark.MarkItemId;
import com.easytnt.share.domain.id.mark.MarkerTeamId;
import com.google.common.collect.Lists;

/**
 * 评题统计
 *
 * @author Liguiqing
 * @since V3.0
 */

public class MarkItemStatis extends ItemStatis {

    public MarkItemStatis(MarkItemId markItemId, String itemName,
                          int timesRequired, double fullScore) {
        this(markItemId, itemName, timesRequired, fullScore, 0);
    }

    public MarkItemStatis(MarkItemId markItemId, String itemName,
                          int timesRequired, double fullScore, double error) {
        this(markItemId, itemName, timesRequired, fullScore, error,-1);
    }

    public MarkItemStatis(MarkItemId markItemId, String itemName,
                          int timesRequired, double fullScore, double error, int totalRequired) {
        super(markItemId, itemName, timesRequired, fullScore, error, totalRequired);
        this.targetId(markItemId);
        this.targetName(itemName);
    }
    
    public static List<MarkItemStatis> addMarkItemStatis(List<ItemStatis> statises){
    	List<MarkItemStatis> ItemStatises = Lists.newArrayList();
    	for(ItemStatis statis: statises){
    		if(statis instanceof MarkItemStatis){
    			MarkItemStatis itemStatis = (MarkItemStatis) statis;
    			ItemStatises.add(itemStatis);
    		}
    	}
    	return ItemStatises;
    }
}