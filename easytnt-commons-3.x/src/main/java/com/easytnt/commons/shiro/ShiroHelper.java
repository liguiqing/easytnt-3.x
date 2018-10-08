/*
 * Copyright (c) 2016,2018, easytnt All Rights Reserved. 深圳市易考试乐学测评有限公司 版权所有.
 */

package com.easytnt.commons.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * http　port的超类
 * @author Liguiqing
 * @since V3.0
 */

public  class ShiroHelper {
    private static Logger logger = LoggerFactory.getLogger(ShiroHelper.class);

    public static  Subject getSubject(){
        Subject subject = SecurityUtils.getSubject();
        logger.debug(subject.toString());
        if(subject.isRunAs()) {
            subject.releaseRunAs();
        }

        return subject;
    }

    public static boolean isAuthenticated(){
        Subject subject = getSubject();
        return subject.isAuthenticated();
    }

}