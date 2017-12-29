/*
 * Copyright (c) 2016,2017, easytnt All Rights Reserved. 深圳市易考试乐学测评有限公司 版权所有.
 */

package com.easytnt.ts.domain.model.school;

import com.easytnt.commons.spring.SpringContextUtil;
import com.easytnt.ts.application.school.SchoolQueryService;
import com.easytnt.ts.domain.model.school.common.Configable;
import com.easytnt.ts.domain.model.school.common.ConfiguationFactory;
import com.easytnt.ts.domain.model.school.common.SchoolConfig;
import com.easytnt.ts.infrastructure.strategy.GradeNameOneToNineAndG1ToG3GenerateStrategy;

import java.util.List;

/**
 * 年级名称生成器工厂
 *
 * @author Liguiqing
 * @since V3.0
 */

public class GradeNameGenerateStrategyFactory {

    public static GradeNameGenerateStrategy lookup(SchoolId schoolId){
        SchoolQueryService queryService = SpringContextUtil.getBean(SchoolQueryService.class);
        List<SchoolConfig> configs = queryService.schoolConfigs(schoolId.id());
        Configable configable = null;
        if(configs != null){
            for(SchoolConfig config:configs){
                configable = ConfiguationFactory.getConfigablesByNameAndValue(
                        GradeNameGenerateStrategy.configName, config.getConfiguation().getValue());
            }
        }

        if(configable == null)
             configable = ConfiguationFactory.getConfigablesByNameAndValue(
                GradeNameGenerateStrategy.configName,
                GradeNameOneToNineAndG1ToG3GenerateStrategy.class.getName());

        return getGenerateStrategyFrom(configable);
    }


    private static GradeNameGenerateStrategy getGenerateStrategyFrom(Configable configable){
        return (GradeNameGenerateStrategy)configable;
    }
}