/*
 * Copyright (c) 2016,2018, easytnt All Rights Reserved. 深圳市易考试乐学测评有限公司 版权所有.
 */

package com.easytnt.aim.domain.model.bank;

import com.easytnt.aim.domain.model.sheet.MarkItemSpecId;
import com.easytnt.commons.domain.EntityRepository;

import java.util.List;

/**
 * @author Liguiqing
 * @since V3.0
 */

public interface ItemBankRepository extends EntityRepository<ItemBank,ItemBankId> {

    List<ItemBank> findItemBanks(MarkItemSpecId itemSpecId);
}