/*
 * Copyright (c) 2016,2018, easytnt All Rights Reserved. 深圳市易考试乐学测评有限公司 版权所有.
 */

package com.easytnt.aim.domain.model.shop.form;

import com.easytnt.aim.domain.model.mark.ItemMarkerId;
import com.easytnt.aim.domain.model.sheet.MarkItemId;
import com.easytnt.commons.domain.IdentifiedValueObject;

/**
 * 评题单据,评题签入签出时所填写的单据
 * 分为签入单及签出两大类,一个签出单对应一个签入单
 *
 * @author Liguiqing
 * @since V3.0
 */

public abstract class ItemForm extends IdentifiedValueObject {

    private  ItemFormId itemFormId;

    private MarkItemId itemId;

    private ItemMarkerId markerId;

    private boolean cancel;

    private int currentTimes;//当前评次

}