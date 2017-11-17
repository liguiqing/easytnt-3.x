/*
 * Copyright (c) 2016,2017, easytnt All Rights Reserved. 深圳市易考试乐学测评有限公司 版权所有.
 */

package com.easytnt.ts.domain.model.school;

/**
 * 班级或者科目的文理分类
 *
 * @author Liguiqing
 * @since V3.0
 */

public enum WLType {
    Liberal{//文科
        public int getValue(){
            return 1;
        }
    },Science{//理科
        public int getValue(){
            return 2;
        }
    },None{//不分
        public int getValue(){
            return 0;
        }
    };

    public int getValue(){
        return 0;
    }
}