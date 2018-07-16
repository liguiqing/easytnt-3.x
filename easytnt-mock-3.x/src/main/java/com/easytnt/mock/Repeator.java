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
    public  <T> T[] repeatOfGroupOfEachMaxLt(int size,int repeatsOfEach,int max,T... src){
        if(src == null || src.length == 0)
            return null;

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
        if(max <= 1)
            return values;
        if(max > values.length)
            max  = values.length;
        T[] valuesCopy = (T[]) Array.newInstance(src[0].getClass(), max);
        System.arraycopy(values,0,valuesCopy,0,max);
        return valuesCopy;
    }


    public  <T> T[] repeatOfGroupOfEach(int size,int repeatsOfEach,T... src){
        return this.repeatOfGroupOfEachMaxLt(size, repeatsOfEach, 1, src);
    }

    public  <T> T[] repeatOfGroup(int size,T... src){
        return this.repeatOfGroupOfEach(size, 1, src);
    }

    public  <T> T[] repeatOf(int size,T value){
        if(size < 1 )
            return null;

        T[] values = getArray(size,value);
        for(int i=0;i<size;i++){
            values[i] = value;
        }
        return values;
    }

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

    private <T> T[] getArray(int size,T t){
        Class<?> clazz = getClazz(t);
        T[] ts = (T[]) Array.newInstance(clazz, size);
        return ts;
    }

    private  <T> Class<?> getClazz(T t){
        return t==null?Object.class:t.getClass();
    }


}