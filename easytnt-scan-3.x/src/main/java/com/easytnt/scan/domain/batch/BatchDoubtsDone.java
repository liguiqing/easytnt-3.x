package com.easytnt.scan.domain.batch;

import com.easytnt.commons.domain.AbstractDomainEvent;
import com.easytnt.share.domain.id.scan.BatchScanId;

/**
 * 扫描批次完成怀疑处理事件
 *
 * @author Liguiqing
 * @since V3.0
 */

public class BatchDoubtsDone extends AbstractDomainEvent {

    private BatchScanId batchScanId;

    public BatchDoubtsDone(BatchScanId batchScanId) {
        this.batchScanId = batchScanId;
    }

    public BatchScanId batchScanId() {
        return batchScanId;
    }
}