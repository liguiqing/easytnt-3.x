package com.easytnt.commons.util;

import org.apache.commons.lang3.math.NumberUtils;

import java.util.Comparator;

/**
 * 数组处理包装器
 * @author Liguiqing
 * @since V3.0
 */

public class ArraysUtilWraper {

    public static <T> boolean allElementsNotNull(T[] ts){
        for(T t:ts){
            if(t == null)
                return false;
        }
        return true;
    }

    public static <T> boolean hasElement(T t,T[] ts){
        for(T t1:ts){
            if(t instanceof Number){
                Number n1 = (Number)t;
                Number n2 = (Number)t1;
                boolean b = NumberUtilWrapper.compare(n1,n2) == 0;
                if(b)
                    return true;
            }else{
                //todo more
            }
        }

        return false;
    }
}