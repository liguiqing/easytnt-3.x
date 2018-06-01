/*
 * Copyright (c) 2016,2018, easytnt All Rights Reserved. 深圳市易考试乐学测评有限公司 版权所有.
 */

package com.easytnt.exam.domain.exam;

/**
 * @author Liguiqing
 * @since V3.0
 */

public enum CreatorOrgType {
    School(1),DivCounty(2),City(3);

    private int val;

    CreatorOrgType(int val) {
        this.val = val;
    }

    public static CreatorOrgType typeOf(int val){
        if(val == 2)
            return DivCounty;
        if(val == 3)
            return City;
        return School;
    }
}