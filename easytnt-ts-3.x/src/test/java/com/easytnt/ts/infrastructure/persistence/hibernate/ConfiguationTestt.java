/*
 * Copyright (c) 2016,2017, easytnt All Rights Reserved. 深圳市易考试乐学测评有限公司 版权所有.
 */

package com.easytnt.ts.infrastructure.persistence.hibernate;

import com.easytnt.ts.domain.model.school.School;
import com.easytnt.ts.domain.model.school.SchoolId;
import com.easytnt.ts.domain.model.school.SchoolType;
import com.easytnt.ts.domain.model.school.TenantId;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author Liguiqing
 * @since V3.0
 */


@ContextConfiguration(locations = { "classpath:applicationContext-test-ds.xml","classpath:applicationContext-ts.xml",
        "classpath:applicationContext-ts-hb.xml" })
@Transactional
@Rollback
public class ConfiguationTestt extends AbstractTransactionalJUnit4SpringContextTests {

    @Autowired
    private DataSource ds;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    @Qualifier("ds")
    @Override
    public void setDataSource(DataSource dataSource) {
        super.setDataSource(dataSource);
    }

    @Test
    public void test()throws Exception{

        assertNotNull(ds);
        assertNotNull(jdbcTemplate);
        List<String> xs = jdbcTemplate.query("SELECT 'x'", new RowMapper<String>() {
            @Override
            public String mapRow(ResultSet rs, int rowNum) throws SQLException {
                return rs.getString("x");
            }
        });
        assertNotNull(xs);
        assertTrue(xs.size() == 1);
        assertEquals(xs.get(0),"x");

        School s1 = new School(new TenantId("TenantId-12345678"), new SchoolId("SchoolId-12345678"),"School1",
                "s1", SchoolType.High);
        Session session = sessionFactory.openSession();
        Transaction transaction = session.getTransaction();
        transaction.begin();
        session.save(s1);
        transaction.rollback();
    }

}