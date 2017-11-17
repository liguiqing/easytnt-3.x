/*
 * Copyright (c) 2016,2017, easytnt All Rights Reserved. 深圳市易考试乐学测评有限公司 版权所有.
 */

package com.easytnt.ts.domain.model.school.staff;

/**
 * 教职工职务范围
 *
 * @author Liguiqing
 * @since V3.0
 */

public enum StaffPost {
    School{
        public int getType(){
            return 1;
        }
    },Grade{
        public int getType(){
            return 2;
        }
    },Subject{
        public int getType(){
            return 3;
        }

    },Clazz{
        public int getType(){
            return 4;
        }
    };

    public int getType(){
        return 0;
    }
}