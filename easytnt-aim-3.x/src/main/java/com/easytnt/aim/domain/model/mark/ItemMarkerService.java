/*
 * Copyright (c) 2016,2018, easytnt All Rights Reserved. 深圳市易考试乐学测评有限公司 版权所有.
 */

package com.easytnt.aim.domain.model.mark;

import com.easytnt.aim.domain.model.bank.ItemBank;
import com.easytnt.aim.domain.model.bank.ItemBankRepository;
import com.easytnt.aim.domain.model.shop.ItemMarkingShop;
import com.easytnt.aim.domain.model.shop.ItemMarkingShopRepository;
import com.easytnt.commons.AssertionConcerns;

import java.util.List;

/**
 * @author Liguiqing
 * @since V3.0
 */

public class ItemMarkerService {

    private ItemMarkingShopRepository shopRepository;

    private ItemMarkerRepository markerRepository;

    private ItemBankRepository bankRepository;

    public ItemMarker toBeAMarkerTo(String personId,MarkerRole role, ItemMarkingShop shop){

        ItemMarkerAssitant markerAssitant = markerRepository.findAssitantOf(personId,role,shop.shopId());
        if(markerAssitant != null){
            return markerAssitant.servicedMarker();
        }
        ItemMarker marker = this.verifyIdentity(personId,role,shop);

        List<ItemBank> itemBanks = bankRepository.findItemBanks(shop.itemSpecId());

        ItemMarkerAssitant assitant = shop.alloateAssistan(marker,itemBanks);

        marker.workWith(assitant);
        return marker;
    }

    private ItemMarker verifyIdentity(String personId,MarkerRole role,ItemMarkingShop shop){
        ItemMarker marker = markerRepository.findMarker(shop.itemSpecId(),role, personId);
        AssertionConcerns.assertArgumentNotNull(marker,"您没有被安排在本题阅卷任务中");
        return marker;
    }

}