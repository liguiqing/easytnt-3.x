/*
 * Copyright (c) 2016,2018, easytnt All Rights Reserved. 深圳市易考试乐学测评有限公司 版权所有.
 */

package com.easytnt.statis.domain.mark;

import com.easytnt.share.domain.id.PersonId;
import com.easytnt.share.domain.id.mark.MarkItemId;

/**
 * 评卷员评题统计
 *
 * @author Liguiqing
 * @since V3.0
 */

public class MarkerItemStatis extends MarkItemStatis {

    private PersonId personId;

    private String name;


    public MarkerItemStatis(PersonId personId,String name,MarkItemId markItemId, String itemName,
                            int timesRequired, double fullScore, double error) {
        super(markItemId, itemName, timesRequired, fullScore, error);
        this.personId = personId;
        this.name = name;
    }

    public MarkerItemStatis(PersonId personId,String name,MarkItemId markItemId, String itemName,
                            int timesRequired, double fullScore, double error,int totalRequired) {
        super(markItemId, itemName, timesRequired, fullScore, error, totalRequired);
        this.personId = personId;
        this.name = name;
    }
}