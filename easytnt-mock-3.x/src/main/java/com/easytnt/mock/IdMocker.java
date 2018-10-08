package com.easytnt.mock;

/**
 * @author Liguiqing
 * @since V3.0
 */

public class IdMocker {
    public  static String genId(String prefix,String suffix){
        if(suffix!=null)
            return prefix+"-Mock-9527-"+suffix;
        return genId(prefix);
    }

    public  static String genId(String prefix){
        return prefix+"-Mock-9527";
    }
}