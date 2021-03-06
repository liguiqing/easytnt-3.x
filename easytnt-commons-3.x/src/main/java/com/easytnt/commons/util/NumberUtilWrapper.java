/*
 * Copyright (c) 2016,2018, easytnt All Rights Reserved. 深圳市易考试乐学测评有限公司 版权所有.
 */

package com.easytnt.commons.util;

import org.apache.commons.lang3.math.NumberUtils;

import java.text.NumberFormat;
import java.util.Comparator;

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

    /**
     * 生产两long类型数据间的随机数
     *
     * @param x
     * @param y
     * @return [x...y]
     */
    public static long randomBetween(long x,long y){
        if(x == y)
            return x;

        long rtn = x + (long) (Math.ceil((Math.random() * (y - x))));
        return rtn;
    }

    /**
     * 生产两int类型数据间的随机数
     *
     * @param x
     * @param y
     * @return [x...y]
     */
    public static int randomBetween(int x,int y){
        if(x == y)
            return x;

        int rtn = x + (int)(Math.ceil((Math.random() * (y - x))));
        return rtn;
    }

    /**
     * 生产两float类型数据间的随机数
     *
     * @param x
     * @param y
     * @return [x...y]
     */
    public static float randomBetween(float x,float y){
        if(x == y)
            return x;

        float rtn = x + (float) (Math.random() * (y - x + 1));
        return rtn;
    }
    /**
     * 生产两double类型数据间的随机数
     *
     * @param x
     * @param y
     * @return [x...y]
     */
    public static double randomBetween(double x,double y){
        if(x == y)
            return x;
        double rtn = x + (double) (Math.random() * (y - x + 1));
        return rtn;
    }

    /**
     * 生产两Number类型数据间的随机数
     *
     * @param x
     * @param y
     * @param leftOpened
     * @param rightOpend
     * @return leftOpened=true and rightOpend=false->[x...y];leftOpened=false and rightOpend=false ->(x...y)
     * otherwise (x...y] or [x...y)
     */
    public  static Number randomBetween(Number x,Number y,boolean leftOpened,boolean rightOpend){
        if(x.equals(y))
            return x;

        double rtn = x.doubleValue() + (double) (Math.random() * (x.doubleValue() - y.doubleValue() +1 ));
        if (leftOpened) {
            if(rtn == x.doubleValue())
                return rtn;
            else
                return randomBetween(x, y,leftOpened,rightOpend);
        }

        if (rightOpend) {
            if(rtn == y.doubleValue())
                return rtn;
            else
                return randomBetween(x, y,leftOpened,rightOpend);
        }
        return rtn;
    }

    /**
     * 将数字转换按指定步长增加的线性字符串
     * 如:2.5 步长0.5-->[0,0.5,1,1.5,2,2.5]
     * 又如:2.6 步长0.5-->[0,0.5,1,1.5,2,2.5,2.6]
     *
     * @param x 待转换的数字
     * @param step 线性增长的步长
     * @return
     */
    public static String toLinearString(double x,double step,int scale,String delimiter){
        if(scale<0)
            scale = 0;
        NumberFormat nf = NumberFormat.getInstance();
        nf.setMaximumFractionDigits(scale);

        int length = (int)Math.ceil(x/step) + 1;
        String[] array = new String[length];
        int j = 0;
        for(double i=0;i<=x;i=i+step){
            array[j++] = nf.format(i);
        }
        if(array[length-1] == null)
            array[length-1] = nf.format(x);
        String linear = String.join(delimiter,array);
        return linear;
    }

    public static int compare(Number x,Number y){
        int v = 0 ;
        //todo more comparing
        if(x instanceof Integer) {
            v = NumberUtils.compare(((Integer) x).intValue(), ((Integer) y).intValue());
        }
        return v;
    }
}