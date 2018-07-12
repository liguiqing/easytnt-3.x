package com.easytnt.mock;

import com.easytnt.commons.lang.Throwables;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;

import java.io.File;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author Liguiqing
 * @since V3.0
 */

public class MockExecuter {
    private static Logger logger = LoggerFactory.getLogger(MockExecuter.class);
    List<Mock> mocks = Lists.newArrayList();

    private JdbcTemplate jdbc;


    public MockExecuter(JdbcTemplate jdbc) throws Exception{
        this.jdbc = jdbc;
        File directory = new File("");// 参数为空
        String courseFile = directory.getCanonicalPath();
        registerMocks("", courseFile );
    }

    public void exec() {
        Collections.sort(mocks, Comparator.comparingInt(Mock::order));
        for (Mock mock : mocks) {
            if(mock != null){
                mock.userMocks(mocks);
                mock.createData(jdbc);
            }
        }
    }

    private void registerMocks(String pkgName, String pkgPath) {
        File dir = new File(pkgPath);
        if (!dir.exists() || !dir.isDirectory()) {
            return;
        }

        // 过滤获取目录，or class文件
        File[] dirfiles = dir.listFiles(pathname -> pathname.isDirectory() || pathname.getName().endsWith("class"));

        if (dirfiles == null || dirfiles.length == 0) {
            return;
        }


        for (File f : dirfiles) {
            if (f.isDirectory()) {
                registerMocks(pkgName + "." + f.getName(), pkgPath + "/" + f.getName());
                continue;
            }

            // 获取类名
            String className = f.getName();
            className = className.substring(0, className.length() - 6); //删除".class"扩展名
            pkgName=pkgName.substring(pkgName.indexOf("com"),pkgName.length());
            // 加载类
            Class clz = loadClass(pkgName + "." + className);
            if (clz != null ) {
                Object mock = newInstance(clz);
                if(mock != null && mock instanceof Mock) {
                    Mock ms = (Mock) mock;
                    mocks.add(ms);
                }
            }
        }
    }

    private Object newInstance(Class<?> mockClazz) {
        try {
            return mockClazz.newInstance();
        } catch (InstantiationException e) {
            logger.error(Throwables.toString(e));
        } catch (IllegalAccessException e) {
            logger.error(Throwables.toString(e));
        }
        return null;
    }

    private Class<?> loadClass(String fullClzName) {
        try {
            return Thread.currentThread().getContextClassLoader().loadClass(fullClzName);
        } catch (ClassNotFoundException e) {
            logger.error("load class error! clz: {}, e:{}", fullClzName, e);
        }
        return null;
    }

}