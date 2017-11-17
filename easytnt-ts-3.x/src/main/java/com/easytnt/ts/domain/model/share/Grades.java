/*
 * Copyright (c) 2016,2017, easytnt All Rights Reserved. 深圳市易考试乐学测评有限公司 版权所有.
 */

package com.easytnt.ts.domain.model.share;

import com.easytnt.ts.domain.model.school.Grade;

/**
 * 学校性质
 *
 * @author Liguiqing
 * @since V3.0
 */

public enum Grades {

    Primary{//小学
        public Grade[] getGrades(){
            return ShareDomainModelQuery.findGrades(1);
        }
    },
    Middle{//初中
        public Grade[] getGrades(){
            return ShareDomainModelQuery.findGrades(2);
        }
    },
    High{//高中
        public Grade[] getGrades(){
            return ShareDomainModelQuery.findGrades(3);
        }
    },
    PrimaryToMiddlel{
        public Grade[] getGrades(){
            return ShareDomainModelQuery.findGrades(4);
        }
    },
    MiddleToHigh{
        public Grade[] getGrades(){
            return ShareDomainModelQuery.findGrades(5);
        }
    },
    All{
        public Grade[] getGrades(){
            return ShareDomainModelQuery.findGrades(0);
        }
    };

    public Grade[] getGrades(){
        return null;
    }
}