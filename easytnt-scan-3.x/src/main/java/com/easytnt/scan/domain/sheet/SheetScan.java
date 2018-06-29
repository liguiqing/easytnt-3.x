/*
 * Copyright (c) 2016,2018, easytnt All Rights Reserved. 深圳市易考试乐学测评有限公司 版权所有.
 */

package com.easytnt.scan.domain.sheet;

import com.easytnt.commons.domain.Entity;
import com.easytnt.scan.domain.batch.BatchScan;
import com.easytnt.share.domain.id.scan.BatchScanId;
import com.easytnt.share.domain.id.scan.SheetScanId;

/**
 * 答题卡扫描
 *
 * @author Liguiqing
 * @since V3.0
 */

public class SheetScan extends Entity {
    private SheetScanId sheetScanId;

    private BatchScanId batchScanId;

    private boolean examNumberDoubt; //考号错误疑似:0－无疑似错误；1－有疑似错误

    private boolean kgDoubt; //客观错误疑似:0－无疑似错误;1－有疑似错误

    private boolean zgOptionalDoubt ; //选择题错误疑似:0－无疑似错误；1－有疑似错误


    public boolean sameBatchOf(BatchScan batchScan){
        return this.batchScanId.equals(batchScan.batchScanId());
    }

    public SheetScanId sheetScanId() {
        return sheetScanId;
    }

    public BatchScanId batchScanId() {
        return batchScanId;
    }

    public boolean examNumberDoubt() {
        return examNumberDoubt;
    }

    public boolean kgDoubt() {
        return kgDoubt;
    }

    public boolean zgOptionalDoubt() {
        return zgOptionalDoubt;
    }
}