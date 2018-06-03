package com.easytnt.scan.domain.batch;

import com.easytnt.commons.domain.Entity;
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