package com.easytnt.scan.port.adapter.persistence.repository;

import com.easytnt.commons.persistence.JdbcRepository;
import com.easytnt.scan.domain.batch.BatchScan;
import com.easytnt.scan.domain.batch.BatchScanReporitory;
import com.easytnt.share.domain.id.scan.BatchScanId;
import org.springframework.jdbc.core.JdbcOperations;


/**
 * @author Liguiqing
 * @since V3.0
 */

public class JdbcBatchScanRepository extends JdbcRepository<BatchScan> implements BatchScanReporitory {

    private static final String loadOfIdSql = "select * from t_ps_BatchScan where batchId =? and removed=0";

    public JdbcBatchScanRepository(BatchScanMapper mapper, JdbcOperations jdbc) {
        super(mapper, jdbc);
    }

    @Override
    public BatchScanId nextIdentity() {
        return new BatchScanId();
    }

    @Override
    public BatchScan loadOfId(BatchScanId id) {
        return find(loadOfIdSql,id);
    }

    @Override
    public void save(BatchScan batchScan) {
        insert(batchScan);
    }
}