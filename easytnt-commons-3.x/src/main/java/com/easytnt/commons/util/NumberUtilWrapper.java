/*
 * Copyright (c) 2016,2018, easytnt All Rights Reserved. 深圳市易考试乐学测评有限公司 版权所有.
 */

package com.easytnt.commons.util;

import java.text.NumberFormat;

/**
 * 数字处理工具
 *
 * @author Liguiqing
 * @since V3.0
 */

public class NumberUtilWrapper {

    /**
     * 两数相除后，转换为百分数
     * @param x 除数
     * @param y 被除数
     * @param scale 百分化后保留小数点位数
     * @return n.scale%
     */
    public static String formattedDecimalToPercentage(double x, double y,int scale) {
        double rate = rate(x,y);
        return formattedDecimalToPercentage(rate,scale);
    }

    /**
     * 将数字转换为百分数
     * @param decimal 除数
     * @param scale 百分化后保留小数点位数
     * @return n.scale%
     */
    public static String formattedDecimalToPercentage(double decimal,int scale) {
        if(scale < 1)
            scale = 1;

        NumberFormat nt = NumberFormat.getPercentInstance();
        nt.setMinimumFractionDigits(scale);
        return nt.format(decimal);
    }

    /**
     * 计算两数和比率
     * @param x
     * @param y
     * @return
     */
    public static double rate(double x,double y){
        if(y==0)
            return 0d;

        return (x*1d)/(y*1d);
    }
}