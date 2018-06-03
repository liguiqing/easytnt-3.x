package com.easytnt.scan.domain.batch;

import com.easytnt.commons.domain.AbstractDomainEvent;
import com.easytnt.share.domain.id.scan.BatchScanId;

/**
 * 扫描批次开始上传事件
 *
 * @author Liguiqing
 * @since V3.0
 */

public class BatchSubmitting extends AbstractDomainEvent {

    private BatchScanId batchScanId;

    public BatchSubmitting(BatchScanId batchScanId) {
        this.batchScanId = batchScanId;
    }

    public BatchScanId batchScanId() {
        return batchScanId;
    }
}