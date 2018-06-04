package com.easytnt.share.domain.id.scan;

import com.easytnt.commons.domain.AbstractId;
import com.easytnt.commons.domain.Identities;

import static com.easytnt.share.domain.id.IdPrefixes.BatchScanIdPrefix;

/**
 * 答题卡扫描批次唯一标识
 *
 * @author Liguiqing
 * @since V3.0
 */

public class BatchScanId extends AbstractId {

    public BatchScanId(String anId) {
        super(anId);
    }

    public BatchScanId() {
        super(Identities.genIdNone(BatchScanIdPrefix));
    }


}