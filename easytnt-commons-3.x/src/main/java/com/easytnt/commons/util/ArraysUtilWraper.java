package com.easytnt.commons.util;

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
}