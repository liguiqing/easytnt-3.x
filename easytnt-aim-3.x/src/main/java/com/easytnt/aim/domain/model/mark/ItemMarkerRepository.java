/*
 * Copyright (c) 2016,2018, easytnt All Rights Reserved. 深圳市易考试乐学测评有限公司 版权所有.
 */

package com.easytnt.aim.domain.model.mark;

import com.easytnt.aim.domain.model.sheet.MarkItemSpecId;
import com.easytnt.aim.domain.model.shop.ItemMarkingShopId;
import com.easytnt.commons.domain.EntityRepository;

/**
 * @author Liguiqing
 * @since V3.0
 */

public interface ItemMarkerRepository extends EntityRepository< ItemMarker,ItemMarkerId> {

    ItemMarkerAssitant findAssitantOf(String personId,MarkerRole role, ItemMarkingShopId itemMarkingShopId);

    ItemMarker findMarker(MarkItemSpecId itemSpecId,MarkerRole role, String personId);
}