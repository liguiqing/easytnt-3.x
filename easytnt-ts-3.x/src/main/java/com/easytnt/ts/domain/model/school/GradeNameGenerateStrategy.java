/*
 * Copyright (c) 2016,2017, easytnt All Rights Reserved. 深圳市易考试乐学测评有限公司 版权所有.
 */

package com.easytnt.ts.domain.model.school;

/**
 * 学校年级名称生成策略
 *
 * @author Liguiqing
 * @since V3.0
 */

public interface GradeNameGenerateStrategy {

    public final String configName = "年级名称";

    String genGradeName(GradeLevel seq);
}