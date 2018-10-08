package com.easytnt.scan.port.adapter.persistence.repository;

import com.easytnt.commons.lang.reflect.FieldReflector;
import com.easytnt.commons.lang.reflect.ProtectedConstractorReflector;
import com.easytnt.commons.persistence.EntityMapper;
import com.easytnt.scan.domain.batch.BatchScan;

/**
 * @author Liguiqing
 * @since V3.0
 */

public class BatchScanMapper extends EntityMapper<BatchScan> {


    public BatchScanMapper(ProtectedConstractorReflector constractorReflector, FieldReflector fieldReflector) {
        super(constractorReflector, fieldReflector,BatchScan.class);
    }
}