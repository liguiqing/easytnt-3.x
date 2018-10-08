/*
 * Copyright (c) 2016,2018, easytnt All Rights Reserved. 深圳市易考试乐学测评有限公司 版权所有.
 */

package com.easytnt.commons.domain;

import java.util.UUID;

/**
 * 唯一标识工具
 *
 * @author Liguiqing
 * @since V3.0
 */

public class Identities {

    /**
     * 生成带前缀的id
     * @param prefix 前缀
     * @return
     */
    public static String genId(String prefix){
        String uuid = UUID.randomUUID().toString();
        return prefix + uuid;
    }

    /**
     * 生成带前缀且不含"-"id
     * @param prefix 前缀
     * @return
     */
    public static String genIdNone(String prefix){
        String uuid = UUID.randomUUID().toString();
        return prefix + uuid.replaceAll("-","");
    }
}