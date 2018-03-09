/*
 * Copyright (c) 2016,2018, easytnt All Rights Reserved. 深圳市易考试乐学测评有限公司 版权所有.
 */

package com.easytnt.aim.domain.model.exam;

import com.easytnt.commons.domain.ValueObject;

/**
 * 考试科目统计表
 *
 * @author Liguiqing
 * @since V3.0
 */

public class ExamSubjectStatistics extends ValueObject {

    private int registerExaminees;  //报名学生人数

    private int sheetScanneds; //已扫描答题卡数量

    private int sheetExamNoErrors;//已扫答题卡中考号异常量 值小于等 sheetScanneds

    private int sheetExamNoErrorsHandled; //答题卡考号异常已处理数量 值小于等于sheetExamNoErrors

    private int sheetKgErrors;//已扫答题卡中客观异常量 值小于等 sheetScanneds

    private int sheetKgErrorsHandled; //答题卡客观题异常已处理数量 sheetKgErrors

    private int sheetMarkFinished; //完成评阅数

}