package com.easytnt.statis.infrastructure.jdbc;

import com.easytnt.commons.spring.SpringContextUtil;
import com.easytnt.statis.domain.mark.MarkScore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Collection;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.*;
import static org.powermock.api.mockito.PowerMockito.*;


/**
 * Copyright (c) 2016,$today.year, 深圳市易考试乐学测评有限公司
 **/

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext-statis-test-app.xml",
        "classpath:applicationContext-statis-test-ds.xml"})
public class JdbcItemDataSetTest {

    @Test
    public void next() throws Exception{
        String markItemId = "markItemId";
        DataSource ds1 = SpringContextUtil.getBean(DataSource.class);
        DataSource ds = spy(ds1);

        JdbcTemplate jdbc1 = new JdbcTemplate(ds);
        JdbcTemplate jdbc = spy(jdbc1);

        doReturn(10d).doReturn(1).when(jdbc).queryForObject(anyString(),any(RowMapper.class),any(String.class));
        RowCallbackHandler rc = mock(RowCallbackHandler.class);
        ResultSet rs = mock(ResultSet.class);
        when(rs.next()).thenReturn(true).thenReturn(true).thenReturn(true).thenReturn(true).thenReturn(true).thenReturn(false);
        when(rs.getString(eq("team_id"))).thenReturn("t1").thenReturn("t11").thenReturn("t12").thenReturn("t2").thenReturn("t21");
        when(rs.getString(eq("parent_team_id"))).thenReturn(null).thenReturn("t1").thenReturn("t1").thenReturn(null).thenReturn("t2");

        doNothing().when(rc).processRow(eq(rs));

        doNothing().when(jdbc).query(anyString(),any(RowCallbackHandler.class),any(String.class));

        new JdbcItemDataSet(jdbc,markItemId);

        JdbcItemDataSet dataSet = new JdbcItemDataSet(jdbc1,"MIT-Mock-9527-5");
        Collection<MarkScore> markScores = dataSet.next();
        boolean next = dataSet.hasNext();
    }
}