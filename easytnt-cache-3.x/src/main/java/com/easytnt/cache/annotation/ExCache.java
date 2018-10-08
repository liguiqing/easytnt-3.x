/*
 * Copyright (c) 2016,2018, easytnt All Rights Reserved. 深圳市易考试乐学测评有限公司 版权所有.
 */

package com.easytnt.cache.annotation;

import java.lang.annotation.*;

/**
 * 对@Cache进行扩展，实现一次请求生成多个缓存数，降低与仓储的交互次数
 *
 * @author Liguiqing
 * @since V3.0
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Inherited
@Documented
public @interface ExCache {

    /**
     * 缓存的过期时间，单位：秒，如果为0则表示永久缓存
     * @return 时间
     */
    int expire();

    /**
     * 动态获取缓存过期时间的表达式
     * @return 时间
     */
    String expireExpression() default "";

    /**
     * 自定义缓存Key，支持表达式
     * @return String 自定义缓存Key
     */
    String key();

    /**
     * 设置哈希表中的字段，如果设置此项，则用哈希表进行存储，支持表达式
     * @return String
     */
    String hfield() default "";

    /**
     * 缓存的条件表达式，返回 true 或者 false，只有为 true 才进行缓存
     * @return String
     */
    String condition() default "";

    /**
     * 通过表达式获取需要缓存的数据，如果没有设置，则默认使用 #retVal
     * @return 缓存对象
     */
    String cacheObject() default "";

}