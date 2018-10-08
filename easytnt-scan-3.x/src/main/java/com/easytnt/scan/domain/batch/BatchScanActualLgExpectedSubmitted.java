package com.easytnt.scan.domain.batch;

import com.easytnt.commons.domain.AbstractDomainEvent;
import com.easytnt.share.domain.id.scan.BatchScanId;

/**
 * 按考场扫描时，实际扫描数量超过计划扫描数量但仍可以提交事件
 * 
 * @author Liguiqing
 * @since V3.0
 */

public class BatchScanActualLgExpectedSubmitted extends AbstractDomainEvent {

    private BatchScanId batchScanId;

    public BatchScanActualLgExpectedSubmitted(BatchScanId batchScanId) {
        this.batchScanId = batchScanId;
    }

    public BatchScanId batchScanId() {
        return batchScanId;
    }
}