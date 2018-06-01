/*
 * Copyright (c) 2016,2018, easytnt All Rights Reserved. 深圳市易考试乐学测评有限公司 版权所有.
 */

package com.easytnt.cache.annotation;

import java.lang.annotation.*;

/**
 * 生成删除缓存Key注解
 *
 * @author Liguiqing
 * @since V3.0
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Inherited
@Documented
public @interface CacheDeleteKey {

    /**
     * 缓存的条件表达式，可以为空，返回 true 或者 false，只有为 true 才进行缓存
     * @return String
     */
    String condition() default "";

    /**
     * 删除缓存的Key表达式, 当value有值时，是自定义缓存key（删除缓存不支持默认缓存key）。
     * @return String
     */
    String[] value();

    /**
     * 哈希表中的字段表达式
     * @return String
     */
    String hfield() default "";
}