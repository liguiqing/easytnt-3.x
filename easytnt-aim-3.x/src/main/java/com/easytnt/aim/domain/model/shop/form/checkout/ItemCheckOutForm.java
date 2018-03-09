/*
 * Copyright (c) 2016,2018, easytnt All Rights Reserved. 深圳市易考试乐学测评有限公司 版权所有.
 */

package com.easytnt.aim.domain.model.shop.form.checkout;

import com.easytnt.aim.domain.model.shop.form.ItemForm;

import java.util.Date;

/**
 * 评题签出单据
 *
 * @author Liguiqing
 * @since V3.0
 */

public abstract class ItemCheckOutForm extends ItemForm {
    private Date checkOutTime;

    private String signature;
}