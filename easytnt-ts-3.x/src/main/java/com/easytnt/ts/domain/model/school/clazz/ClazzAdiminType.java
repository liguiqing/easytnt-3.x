/*
 * Copyright (c) 2016,2017, easytnt All Rights Reserved. 深圳市易考试乐学测评有限公司 版权所有.
 */

package com.easytnt.ts.domain.model.school.clazz;

/**
 * 班级的管理类型：行政班，教学班，不分类型
 *
 * @author Liguiqing
 * @since V3.0
 */

public enum ClazzAdiminType {
    Manage{//行政班
        public int getType(){
            return 1;
        }

        public boolean canBeStudied(){
            return  false;
        }
    },
    Study{//教学班
        public int getType(){
            return 2;
        }

        public boolean canBeManaged(){
            return false;
        }

    },
    Union;//不分类型

    public int getType(){
        return 9;
    }

    public boolean canBeManaged(){
        return  true;
    }

    public boolean canBeStudied(){
        return  true;
    }

    public static ClazzAdiminType fromName(String name){
        for(ClazzAdiminType type:ClazzAdiminType.values()){
            if(type.name().equals(name)){
                return type;
            }
        }
        throw new ClazzAdminTypeNotFoundException("错误的班级管理类型：" + name);
    }
}