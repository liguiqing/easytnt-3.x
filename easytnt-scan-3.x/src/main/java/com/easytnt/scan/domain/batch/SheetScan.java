package com.easytnt.scan.domain.batch;

import com.easytnt.commons.AssertionConcerns;
import com.easytnt.commons.domain.Entity;
import com.easytnt.share.domain.id.scan.BatchScanId;
import com.easytnt.share.domain.id.scan.SheetScanId;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

/**
 * 答题卡扫描
 *
 * @author Liguiqing
 * @since V3.0
 */

public class SheetScan extends Entity {
    private SheetScanId sheetScanId;

    private BatchScanId batchScanId;

    private String sheetKey;

    private int sheet; //扫描卡张码

    private int page;  //扫描卡页码

    private ScanFeature gobalFeature;

    private ScanFeature examNumberFeature;

    private ScanFeature kgFeature;

    private ScanFeature zgFeature;

    private ScanFeature zgOptionalFeature;

    private boolean examNumberDoubt; //考号错误疑似

    private boolean kgDoubt; //客观错误疑似

    private boolean zgOptionalDoubt ; //选择题错误疑似

    private boolean examNumberDoubtDone; //考号疑似错误是扫描时已经处理过


    public SheetScan(BatchScanId batchScanId, String sheetKey, int sheet, int page) {
        AssertionConcerns.assertArgumentNotNull(batchScanId,"无效的扫描批次标识");
        AssertionConcerns.assertArgumentNotNull(sheetKey,"无效的扫描图像标识");
        AssertionConcerns.assertArgumentTrue(sheet > 0,"无效的张码");
        AssertionConcerns.assertArgumentTrue(page > 0,"无效的页码");

        this.batchScanId = batchScanId;
        this.sheetKey = sheetKey;
        this.sheet = sheet;
        this.page = page;

        this.sheetScanId = new SheetScanId();
    }

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

    public boolean examNumberDoubtDone() {
        return examNumberDoubtDone;
    }

    public String sheetKey() {
        return sheetKey;
    }

    public int sheet() {
        return sheet;
    }

    public int page() {
        return page;
    }

    public ScanFeature gobalFeature() {
        return gobalFeature;
    }

    public ScanFeature examNumberFeature() {
        return examNumberFeature;
    }

    public ScanFeature kgFeature() {
        return kgFeature;
    }

    public ScanFeature zgFeature() {
        return zgFeature;
    }

    public ScanFeature zgOptionalFeature() {
        return zgOptionalFeature;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("sheetScanId", sheetScanId)
                .add("batchScanId", batchScanId)
                .add("sheetKey", sheetKey)
                .add("sheet", sheet)
                .add("page", page)
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SheetScan sheetScan = (SheetScan) o;
        return Objects.equal(sheetScanId, sheetScan.sheetScanId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(sheetScanId);
    }
}