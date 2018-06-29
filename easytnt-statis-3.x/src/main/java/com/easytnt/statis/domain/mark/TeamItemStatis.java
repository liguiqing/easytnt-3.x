/*
 * Copyright (c) 2016,2018, easytnt All Rights Reserved. 深圳市易考试乐学测评有限公司 版权所有.
 */

package com.easytnt.statis.domain.mark;

import com.easytnt.share.domain.id.PersonId;
import com.easytnt.share.domain.id.mark.MarkItemId;
import com.easytnt.share.domain.id.mark.MarkerTeamId;

/**
 * 评卷组评题统计
 *
 * @author Liguiqing
 * @since V3.0
 */

public class TeamItemStatis extends ItemStatis {

    public TeamItemStatis(MarkerTeamId teamId, String name, MarkItemId markItemId, String itemName,
                          int timesRequired, double fullScore, double error) {
        super(markItemId, itemName, timesRequired, fullScore, error);
        this.targetId(teamId);
        this.targetName(name);
    }

    public TeamItemStatis(MarkerTeamId teamId, String name, MarkItemId markItemId, String itemName,
                          int timesRequired, double fullScore, double error, int totalRequired) {
        super(markItemId, itemName, timesRequired, fullScore, error, totalRequired);
        this.targetId(teamId);
        this.targetName(name);
    }

    public PersonId getPersonId() {
        return (PersonId) super.getTargetId();
    }

    public String getName() {
        return super.getTargetName();
    }
}