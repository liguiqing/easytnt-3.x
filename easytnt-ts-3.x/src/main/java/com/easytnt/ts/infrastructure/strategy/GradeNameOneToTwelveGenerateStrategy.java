/*
 * Copyright (c) 2016,2017, easytnt All Rights Reserved. 深圳市易考试乐学测评有限公司 版权所有.
 */

package com.easytnt.ts.infrastructure.strategy;

import com.easytnt.ts.domain.model.school.GradeLevel;
import com.easytnt.ts.domain.model.school.GradeNameGenerateStrategy;
import com.easytnt.ts.domain.model.school.common.Configable;
import com.easytnt.ts.domain.model.school.common.Configuation;
import com.easytnt.ts.domain.model.school.common.ConfiguationFactory;

/**
 * 一到十二年级名称生成器
 *
 * @author Liguiqing
 * @since V3.0
 */

public class GradeNameOneToTwelveGenerateStrategy implements GradeNameGenerateStrategy,Configable {

    private  Configuation configuation = new Configuation(GradeNameGenerateStrategy.configName,
            getClass().getName(),"#link#gradeNameOneToTwelveGenerate.html");

    public GradeNameOneToTwelveGenerateStrategy(){
        ConfiguationFactory.register(this);
    }

    @Override
    public Configuation config() {
        return this.configuation;
    }

    @Override
    public String genGradeName(GradeLevel seq) {
        switch (seq){
            case Two:
                return "二年级";
            case Three:
                return "三年级";
            case Four:
                return "四年级";
            case Five:
                return "五年级";
            case Six:
                return "六年级";
            case Seven:
                return "七年级";
            case Eight:
                return "八年级";
            case Nine:
                return "九年级";
            case Ten:
                return "十年级";
            case Eleven:
                return "十一年级";
            case Twelve:
                return "十二年级";
        }
        return "一年级";
    }

}