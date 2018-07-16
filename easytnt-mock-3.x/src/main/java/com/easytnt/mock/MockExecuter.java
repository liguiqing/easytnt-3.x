package com.easytnt.mock;

import com.easytnt.commons.spring.SpringContextUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author Liguiqing
 * @since V3.0
 */

public class MockExecuter {
    private static Logger logger = LoggerFactory.getLogger(MockExecuter.class);

    private JdbcTemplate jdbc;

    public MockExecuter(JdbcTemplate jdbc) throws Exception{
        this.jdbc = jdbc;
    }

    public void exec() {
        Mock[] mocks = SpringContextUtil.getBeans(Mock.class);
        if(mocks == null || mocks.length == 0){
            logger.debug("No Mock will be executing");
            return;
        }
        logger.debug("{} Mocks will be executing",mocks.length);
        Arrays.sort(mocks, Comparator.comparingInt(Mock::order));
        for (Mock mock : mocks) {
            if(mock != null){
                mock.createData(jdbc);
            }
        }
    }

}