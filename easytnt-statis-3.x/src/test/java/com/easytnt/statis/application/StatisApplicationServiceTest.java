package com.easytnt.statis.application;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * Copyright (c) 2016,$today.year, 深圳市易考试乐学测评有限公司
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext-statis-test-ds.xml","classpath:applicationContext-statis-test-app.xml",
        "classpath:META-INF/spring/applicationContext-statis-app.xml"})
public class StatisApplicationServiceTest {

    @Test
    public void statisForItem() {
    }

    @Test
    public void statisForSubject() {
    }
}