package com.easytnt.statis.application;

import com.easytnt.commons.util.DateUtilWrapper;
import com.easytnt.statis.domain.mark.DataSetFilter;
import com.easytnt.statis.domain.mark.DataSetFilterBuilder;
import com.easytnt.statis.domain.mark.ItemStatis;
import com.easytnt.statis.infrastructure.DefaultDataSetFilterBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import static junit.framework.TestCase.*;

/**
 * @author Liguiqing
 * @since V3.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext-statis-test-ds.xml",
        "classpath:applicationContext-statis-test-app.xml",
        "classpath:META-INF/spring/applicationContext-statis-app.xml"})
public class IntegrationsTest {

    @Autowired
    private StatisApplicationService applicationService;

    @Autowired
    private StatisQueryService queryService;

    @Test
    public void test()throws Exception{
        assertNotNull(applicationService);
        assertNotNull(queryService);
        DataSetFilterBuilder dataSetFilterBuilder = DefaultDataSetFilterBuilder.getInstance();
        DataSetFilter filter = dataSetFilterBuilder.concreate(new HashMap());
        String subjectId = "SUB-Mock-9527-1";
        Date starTime = DateUtilWrapper.toDate("2000-01-01 00:00:00","yyyy-MM-dd HH:mm:ss");
        Date endTime = DateUtilWrapper.now();
        applicationService.statisForSubject(subjectId,starTime,endTime,filter);
        List<ItemStatis> itemStatises =  queryService.queryStatisForSubject(subjectId,starTime,endTime);
        assertNotNull(itemStatises);
    }
}