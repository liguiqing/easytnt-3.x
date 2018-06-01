/*
 * Copyright (c) 2016,2018, easytnt All Rights Reserved. 深圳市易考试乐学测评有限公司 版权所有.
 */

package com.easytnt.aim.domain.model.mark;

import com.easytnt.aim.domain.model.bank.*;
import com.easytnt.aim.domain.model.sheet.MarkItemSpecId;
import com.easytnt.commons.AssertionConcerns;
import com.easytnt.commons.domain.ValueObject;

import java.util.List;

/**
 * 评卷员助手
 * 负责为评卷员发放评题,协调各种关系
 *
 * @author Liguiqing
 * @since V3.0
 */

public class ItemMarkerAssitant extends ValueObject {

    private ItemMarker servicedMarker;

    private FormalItemBank formalItemBank;

    private SurveyItemBank surveyItemBank;

    private TestingItemBank testingItemBank;

    private TrainingItemBank trainingItemBank;

    public ItemMarkerAssitant(ItemMarker aMaker, FormalItemBank formalItemBank) {
        this.servicedMarker = aMaker;
        AssertionConcerns.assertArgumentTrue(formalItemBank.bankOf(aMaker.itemSpecId()),"评卷员不在评题任务中");

    }

    public ItemMarkerAssitant(ItemMarker aMaker, List<ItemBank> itemBanks) {
        this.servicedMarker = aMaker;
        if(itemBanks != null){

        }
    }

    public ItemMarker servicedMarker(){
        return this.servicedMarker;
    }
}