/*
 * Copyright (c) 2016,2018, easytnt All Rights Reserved. 深圳市易考试乐学测评有限公司 版权所有.
 */

package com.easytnt.cache.annotation;

import java.lang.annotation.*;

/**
 * 删除缓存注解
 *
 * @author Liguiqing
 * @since V3.0
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Inherited
@Documented
public @interface CacheDelete {

    CacheDeleteKey[] value();
}