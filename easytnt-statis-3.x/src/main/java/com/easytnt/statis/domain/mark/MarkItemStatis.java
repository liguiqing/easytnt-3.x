/*
 * Copyright (c) 2016,2018, easytnt All Rights Reserved. 深圳市易考试乐学测评有限公司 版权所有.
 */

package com.easytnt.statis.domain.mark;

import com.easytnt.share.domain.id.PersonId;
import com.easytnt.share.domain.id.mark.MarkItemId;
import com.easytnt.share.domain.id.mark.MarkerTeamId;

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

    public MarkItemId getMarkItemId() {
        return (MarkItemId) super.getTargetId();
    }

    public String getName() {
        return super.getTargetName();
    }
}