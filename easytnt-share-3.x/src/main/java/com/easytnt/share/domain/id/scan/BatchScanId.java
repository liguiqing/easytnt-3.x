package com.easytnt.share.domain.id.scan;

import com.easytnt.commons.domain.AbstractId;
import com.easytnt.commons.domain.Identities;

/**
 * 答题卡扫描批次唯一标识
 *
 * @author Liguiqing
 * @since V3.0
 */

public class BatchScanId extends AbstractId {

    private static String prefix = "BAS";

    public BatchScanId(String anId) {
        super(anId);
    }

    public BatchScanId() {
        super(Identities.genIdDNone(prefix));
    }

    public static  String prefix(){
        return prefix;
    }
}