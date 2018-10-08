package com.easytnt.mock;

import com.easytnt.commons.util.NumberUtilWrapper;
import org.springframework.stereotype.Component;

import java.lang.reflect.Array;

/**
 * @author Liguiqing
 * @since V3.0
 */
@Component
public class Repeator {

    /**
     * 将一组数据重复n次后
     * @param size　重复次数
     * @param <T>　src　待重复的数据
     * @return　
     */
    public  <T> T[] repeatOfGroup(int size,T... src){
        return this.repeatOfGroupOfEach(size, 1, src);
    }

    /**
     * 将一组数据重复n次,每个元素重复m次(元素a1重复m次，元素a2重复m次)
     * 输入数据[a1,a2,a3...an]
     * 输出数据[a1,a1...a1x,an,an...anx]
     * @param size 重复次数
     * @param repeatsOfEach 每个元素的重复次数
     * @param src 待重复的数据
     * @param <T>
     * @return
     */
    public  <T> T[] repeatOfGroupOfEach(int size,int repeatsOfEach,T... src){
        if(src == null)
            return null;

        return this.repeatOfGroupOfEachMaxLt(size, repeatsOfEach, size * repeatsOfEach * src.length, src);
    }

    /**
     * 将一组数据重复n次,每个元素重复m次后,取前x次结果
     * 输入数据[a1,a2,a3...an]
     * 输出数据[a1,a1...a1x,an(n*m-x)]
     * @param size　重复次数
     * @param repeatsOfEach　每个元素的重复次数
     * @param max　返回的最元素集合量
     * @param src 待重复的数据
     * @param <T>
     * @return
     */
    public  <T> T[] repeatOfGroupOfEachMaxLt(int size,int repeatsOfEach,int max,T... src){
        if(src == null || src.length == 0)
            return null;

        if(size ==1 && repeatsOfEach==1)
            return arrayCopy(max, src);

        int srcLength = src.length;
        int eachCount = 0;
        int srcCount = 0;
        int length = size * repeatsOfEach * srcLength;
        T[] values = (T[]) Array.newInstance(src[0].getClass(), length);
        for(int i=0;i<length;i++){
            values[i] = src[srcCount];

            if(eachCount<repeatsOfEach) {
                eachCount++;
            }
            if(eachCount == repeatsOfEach) {
                srcCount ++;
                eachCount = 0;
            }
            if(srcCount >= srcLength){
                srcCount = 0;
            }

        }
        return arrayCopy(max, values);
    }

    /**
     * 将一组数据重复n次,每个元素循环重复m次后,取前x次结果
     * 输入数据[a1,a2,a3...an]
     * 输出数据[a1,a2,a3...an,a1,a2,a3...an(n*m-x)]
     * @param size　重复次数
     * @param repeatsOfEach　每个元素的重复次数
     * @param src 待重复的数据
     * @param <T>
     * @return
     */
    public <T> T[] repeatOfGroupOfEachLoop(int size,int repeatsOfEach,T... src){
        if(src == null)
            return null;
        return this.repeatOfGroupOfEachLoopMaxLt(size, repeatsOfEach, size * repeatsOfEach * src.length,src);
    }

    /**
     * 将一组数据重复n次,每个元素循环重复m次后,取前x次结果
     * 输入数据[a1,a2,a3...an]
     * 输出数据[a1,a2,a3...an,a1,a2,a3...an(n*m-x)]
     * @param size　重复次数
     * @param repeatsOfEach　每个元素的重复次数
     * @param max　返回的元素集合量,<=0时返回重复后的全数据
     * @param src 待重复的数据
     * @param <T>
     * @return
     */
    public <T> T[] repeatOfGroupOfEachLoopMaxLt(int size,int repeatsOfEach,int max,T... src){
        if(src == null || src.length == 0)
            return null;

        int srcLength = src.length;
        int eachCount = 0;
        int srcCount = 0;
        int length = size * repeatsOfEach * srcLength;
        T[] values = (T[]) Array.newInstance(src[0].getClass(), length);

        for(int i=0;i<length;i++){
            values[i] = src[srcCount];
            srcCount ++;

            if(eachCount<repeatsOfEach) {
                eachCount++;
            }
            if(eachCount == repeatsOfEach) {
                eachCount = 0;
            }
            if(srcCount >= srcLength){
                srcCount = 0;
            }

        }
        return arrayCopy(max, values);
    }

    /**
     * 将一个数据重复n次
     * @param size 重复次数
     * @param value 待重复数据
     * @param <T>
     * @return
     */
    public  <T> T[] repeatOf(int size,T value){
        if(size < 1 )
            return null;

        T[] values = getArray(size,value);
        for(int i=0;i<size;i++){
            values[i] = value;
        }
        return values;
    }

    /**
     * 将一个数据重复n次，按某个比率使用指定数据随机替换重复后的数据
     * @param size　重复次数
     * @param mixed　替换数据
     * @param rate　替换比率
     * @param value　待重复数据
     * @param <T>
     * @param <R>
     * @return
     */
    public  <T,R extends T> T[] repeatOfMixedRandom(int size,R mixed,double rate,T value){
        T[] values = getArray(size,value);
        for(int i=0;i<size;i++){
            values[i] = value;
        }
        int count = (int)Math.floor(values.length * rate);
        for(int i=0;i<count;i++){
            int random = NumberUtilWrapper.randomBetween(0,values.length-1);
            values[random] = mixed;
        }

        return values;
    }

    /**
     * 将一个数据重复n次，按某个比率使用指定另一组数据的元素随机替换重复后的数据
     * @param size　重复次数
     * @param rs　替换数据
     * @param rate　替换比率
     * @param src　待重复数据
     * @param <T>
     * @param <R>
     * @return
     */
    public  <T,R extends T> T[] repeatOfGroupMixedRandom(int size,R[] rs,double rate,T... src){
        T[] values = repeatOfGroup(size,src);
        if(rate <0 || rate >1)
            return values;

        int count = (int)Math.ceil(values.length * rate);
        for(int i=0;i<count;i++){
            int random1 = NumberUtilWrapper.randomBetween(0,rs.length-1);
            int random2 = NumberUtilWrapper.randomBetween(0,values.length-1);
            values[random2] = rs[random1];
        }

        return values;
    }

    private <T> T[] arrayCopy(int length,T[] src){
        if(length <= 0)
            return src;
        if(length > src.length)
            length  = src.length;
        T[] valuesCopy = (T[]) Array.newInstance(src[0].getClass(), length);
        System.arraycopy(src,0,valuesCopy,0,length);
        return valuesCopy;
    }

    private <T> T[] getArray(int size,T t){
        Class<?> clazz = getClazz(t);
        T[] ts = (T[]) Array.newInstance(clazz, size);
        return ts;
    }

    private  <T> Class<?> getClazz(T t){
        return t==null?Object.class:t.getClass();
    }


}