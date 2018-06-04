package com.easytnt.share.domain.id.scan;

import com.easytnt.commons.domain.AbstractId;
import com.easytnt.commons.domain.Identities;

import static com.easytnt.share.domain.id.IdPrefixes.SheetScanIdPrefix;

/**
 * 答题卡扫描图像唯一标识
 *
 * @author Liguiqing
 * @since V3.0
 */

public class SheetScanId extends AbstractId {

    public SheetScanId(String anId) {
        super(anId);
    }

    public SheetScanId() {
        super(Identities.genIdNone(SheetScanIdPrefix));
    }

}