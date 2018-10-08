/*
 * Copyright (c) 2016,2018, easytnt All Rights Reserved. 深圳市易考试乐学测评有限公司 版权所有.
 */

package com.easytnt.cache.annotation;

import java.lang.annotation.*;

/**
 * 本地缓存注解
 *
 * @author Liguiqing
 * @since V3.0
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Inherited
@Documented
public @interface LocalCache {

    /**
     * 缓存的过期时间，单位：秒，如果为0则表示永久缓存
     * @return 时间
     */
    int expire();

    /**
     * 动态获取缓存过期时间的表达式
     * @return 时间表达式
     */
    String expireExpression() default "";

    /**
     * 只缓存在本地
     * @return boolean
     */
    boolean localOnly() default false;
}