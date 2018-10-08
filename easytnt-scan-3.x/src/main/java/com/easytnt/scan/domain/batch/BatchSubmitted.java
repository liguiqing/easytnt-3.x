package com.easytnt.scan.domain.batch;

import com.easytnt.commons.domain.AbstractDomainEvent;
import com.easytnt.share.domain.id.scan.BatchScanId;

/**
 * 扫描批次完成上传事件
 * 
 * @author Liguiqing
 * @since V3.0
 */

public class BatchSubmitted extends AbstractDomainEvent {

    private BatchScanId batchScanId;

    public BatchSubmitted(BatchScanId batchScanId) {
        this.batchScanId = batchScanId;
    }

    public BatchScanId batchScanId() {
        return batchScanId;
    }
}