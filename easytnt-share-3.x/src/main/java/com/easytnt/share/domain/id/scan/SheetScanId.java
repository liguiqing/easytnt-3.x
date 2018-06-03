package com.easytnt.share.domain.id.scan;

import com.easytnt.commons.domain.AbstractId;
import com.easytnt.commons.domain.Identities;

/**
 * 答题卡扫描图像唯一标识
 *
 * @author Liguiqing
 * @since V3.0
 */

public class SheetScanId extends AbstractId {

    private static String prefix = "PAS";

    public SheetScanId(String anId) {
        super(anId);
    }

    public SheetScanId() {
        super(Identities.genIdDNone(prefix));
    }

    public static  String prefix(){
        return prefix;
    }
}