/*
 * Copyright (c) 2016,2018, easytnt All Rights Reserved. 深圳市易考试乐学测评有限公司 版权所有.
 */

package com.easytnt.aim.domain.model.shop;

import com.easytnt.aim.domain.model.bank.ItemBank;
import com.easytnt.aim.domain.model.mark.ItemMarker;
import com.easytnt.aim.domain.model.mark.ItemMarkerAssitant;
import com.easytnt.aim.domain.model.sheet.MarkItemSpecId;
import com.easytnt.commons.domain.Entity;
import com.google.common.collect.Maps;

import java.util.List;
import java.util.Map;

/**
 * 评卷工场
 *
 * @author Liguiqing
 * @since V3.0
 */

public class ItemMarkingShop extends Entity {

    private ItemMarkingShopId shopId;

    private MarkItemSpecId itemSpecId;

    public MarkItemSpecId itemSpecId() {
        return itemSpecId;
    }

    public ItemMarkingShopId shopId() {
        return shopId;
    }

    public ItemMarkerAssitant alloateAssistan(ItemMarker aMaker, List<ItemBank> itemBanks){
        ItemMarkerAssitant assitant = new ItemMarkerAssitant(aMaker,itemBanks);
        return assitant;
    }

}