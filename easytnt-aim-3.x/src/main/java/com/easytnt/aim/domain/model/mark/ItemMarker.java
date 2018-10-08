/*
 * Copyright (c) 2016,2018, easytnt All Rights Reserved. 深圳市易考试乐学测评有限公司 版权所有.
 */

package com.easytnt.aim.domain.model.mark;

import com.easytnt.aim.domain.model.sheet.MarkItemSpecId;
import com.easytnt.commons.domain.Entity;

import java.util.Date;

/**
 * 评卷员
 *
 * @author Liguiqing
 * @since V3.0
 */

public abstract class ItemMarker extends Entity {

    private ItemMarkerId itemMarkerId;

    private MarkItemSpecId itemSpecId;

    private boolean marking; //是否正在评卷

    private Date lastSubmitTime;

    private String name;

    private String personId;

    private String logInToken;

    private ItemMarkerAssitant assitant;

    public ItemMarkerId itemMarkerId() {
        return itemMarkerId;
    }

    public MarkItemSpecId itemSpecId() {
        return itemSpecId;
    }

    public boolean marking() {
        return marking;
    }

    public Date lastSubmitTime() {
        return lastSubmitTime;
    }

    public String name() {
        return name;
    }

    protected void workWith(ItemMarkerAssitant assitant){
        this.assitant =assitant;
    }
}