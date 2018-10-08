package com.easytnt.mock;

import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @author Liguiqing
 * @since V3.0
 */

public interface Mock {
    String[] ids();

    void createData(JdbcTemplate jdbc);

    int size();

    int order();
}