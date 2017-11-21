/*
 * Copyright (c) 2016,2017, easytnt All Rights Reserved. 深圳市易考试乐学测评有限公司 版权所有.
 */

package com.easytnt.ts.domain.model.school;

/**
 * 年级序列,一到十二年级
 *
 * @author Liguiqing
 * @since V3.0
 */

public enum GradeLevel {
    One,
    Two{
        public int getSeq(){
            return 2;
        }
    },
    Three{
        public int getSeq(){
            return 3;
        }
    },
    Four{
        public int getSeq(){
            return 4;
        }
    },
    Five{
        public int getSeq(){
            return 5;
        }
    },
    Six{
        public int getSeq(){
            return 6;
        }
    },
    Seven{
        public int getSeq(){
            return 7;
        }
    },
    Eight{
        public int getSeq(){
            return 8;
        }
    },
    Nine{
        public int getSeq(){
            return 9;
        }
    },
    Ten{
        public int getSeq(){
            return 10;
        }
    },
    Eleven{
        public int getSeq(){
            return 11;
        }
    },
    Twevel{
        public int getSeq(){
            return 12;
        }
    };

    public int getSeq(){
        return 1;
    }

    public GradeLevel next(){
        int o = this.ordinal();
        for(GradeLevel level:GradeLevel.values()){
            if(level.ordinal() == o+1)
                return level;
        }
        return null;
    }

    public static GradeLevel fromLevel(int level){
        for(GradeLevel aLevel:GradeLevel.values()){
            if(aLevel.getSeq() == level)
                return aLevel;
        }

        return null;
    }

    public static GradeLevel fromName(String name){
        for(GradeLevel aLevel:GradeLevel.values()){
            if(aLevel.name().equals(name))
                return aLevel;
        }

        return null;
    }

}