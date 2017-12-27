/*
 * Copyright (c) 2016,2017, easytnt All Rights Reserved. 深圳市易考试乐学测评有限公司 版权所有.
 */

package com.easytnt.ts.infrastructure.persistence.hibernate;

import com.easytnt.commons.util.DateUtilWrapper;
import com.easytnt.ts.domain.model.school.*;
import com.easytnt.ts.domain.model.school.common.Gender;
import com.easytnt.ts.domain.model.school.common.IdentityType;
import com.easytnt.ts.domain.model.school.common.Period;
import com.easytnt.ts.domain.model.school.staff.*;
import com.easytnt.ts.domain.model.school.term.Term;
import com.easytnt.ts.domain.model.school.term.TermId;
import com.easytnt.ts.domain.model.school.term.TermOrder;
import com.easytnt.ts.domain.model.school.term.TimeSpan;
import org.hibernate.*;
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
import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author Liguiqing
 * @since V3.0
 */


@ContextConfiguration(locations = {"classpath:applicationContext-test-ds.xml", "classpath:applicationContext-ts.xml",
        "classpath:applicationContext-ts-hb.xml"})
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
    public void test() throws Exception {

        Session session = sessionFactory.openSession();
        Transaction transaction = session.getTransaction();
        Query query = null;
        List l1 = null;

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
        assertEquals(xs.get(0), "x");

        School s1 = new School(new TenantId("TenantId-12345678"), new SchoolId("SchoolId-12345678"), "School1",
                "s1", SchoolType.High);

        transaction.begin();
        session.save(s1);
        query = session.createQuery("from School where schoolId='SchoolId-12345678'");
        l1 = query.list();
        School s1_ = (School) l1.get(0);
        assertNotNull(s1_);
        assertEquals(s1, s1_);
        assertEquals(s1.schoolId(), s1_.schoolId());
        assertEquals(s1.tenantId(), s1_.tenantId());
        session.delete(s1_);

        Term t1 = new Term(new TermId("TermId-12345678"), "2017-2018上学期", StudyYear.yearOfNow(),
                TermOrder.First, new TimeSpan(DateUtilWrapper.today(), DateUtilWrapper.tomorrow()),
                new SchoolId("SchoolId-12345678"));
        session.save(t1);
        query = session.createQuery("from Term where termId='TermId-12345678'");
        l1 = query.list();
        Term t1_ = (Term) l1.get(0);
        assertNotNull(t1_);
        assertEquals(t1, t1_);
        assertEquals(t1_.termYear().name(), StudyYear.yearOfNow().name());
        session.delete(t1_);

        Staff staff1 = new Staff(new SchoolId("SchoolId-12345678"), new StaffId("StaffId-12345678"), "Staff1",
                Gender.Female, new Period(DateUtilWrapper.today(), DateUtilWrapper.tomorrow()));
        staff1.addIdentity(new StaffIdentity(new StaffId("StaffId-12345678"), IdentityType.JobNo, "12345678995456"));
        staff1.addIdentity(new StaffIdentity(new StaffId("StaffId-12345678"), IdentityType.QQ, "3215647899"));
        Period period = new Period(DateUtilWrapper.today(), DateUtilWrapper.tomorrow());
        staff1.actTo(new ActHeadMaster(),period);
        staff1.actTo(new ActTeacher(new Course("语文","isd-102345678")),period);
        //staff1.actTo(new ActClazzMaster(new Course("语文","isd-102345678")),period);

        session.save(staff1);

        query = session.createQuery("from Staff where staffId=?");
        query.setParameter(0, new StaffId("StaffId-12345678"));
        l1 = query.list();
        Staff staff1_ = (Staff) l1.get(0);
        assertNotNull(staff1_);
        assertEquals(staff1_.gender(), Gender.Female);
        assertTrue(staff1_.identity().size() > 0);
        assertTrue(staff1_.positions().size() > 0);
        assertTrue(staff1_.identity().contains(new StaffIdentity(new StaffId("StaffId-12345678"), IdentityType.JobNo, "12345678995456")));
        //assertTrue(staff1_.positions().contains(new ActTeacher(new Course("语文", "isd-102345678")).actTo(staff1,period)));
        //Iterator it = staff1_.identity().iterator();
        //assertEquals(it.next(), new StaffIdentity(new StaffId("StaffId-12345678"), IdentityType.JobNo, "12345678995456"));
        //assertEquals(it.next(), new StaffIdentity(new StaffId("StaffId-12345678"), IdentityType.QQ, "3215647899"));
        Iterator it = staff1_.positions().iterator();
        int i = 0;
        while(it.hasNext()){
            it.next();
            i++;
        }
        assertEquals(i,2);
        SQLQuery sqlQuery = session.createSQLQuery("select schoolId, staffId, name, gender, periodStarts, periodEnds from ts_staff where staffId='StaffId-12345678'");
        List ll = sqlQuery.list();
        assertNotNull(ll);
        Object[] rs = (Object[]) ll.get(0);
        assertEquals(rs[3], "女");

        transaction.rollback();


    }

}