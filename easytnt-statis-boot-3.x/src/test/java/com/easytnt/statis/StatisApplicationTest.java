package com.easytnt.statis;

import com.easytnt.statis.infrastructure.DefaultContextBuilder;
import com.easytnt.statis.port.adapter.http.controller.MarkStatisController;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * Copyright (c) 2016,, 深圳市易考试乐学测评有限公司
 **/
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = StatisApplication.class)
public class StatisApplicationTest {

    @Autowired
    private MarkStatisController markStatisController;

    @Autowired
    private DefaultContextBuilder defaultContextBuilder;

    @Test
    public void test(){

    }
}