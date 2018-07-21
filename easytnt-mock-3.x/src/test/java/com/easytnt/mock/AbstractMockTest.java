package com.easytnt.mock;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author Liguiqing
 * @since V3.0
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext-mock-test-ds.xml",
        "classpath:META-INF/spring/applicationContext-mock-app.xml"})
public abstract class AbstractMockTest {


}