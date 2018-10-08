package com.easytnt.scan.domain.batch;

import java.util.List;

/**
 * 答题卡扫描数据解析器
 *
 * @author Liguiqing
 * @since V3.0
 */

public interface SheetParser {

    List<SheetScan> parse(String sheets, BatchScan batchScan);
}