package com.easytnt.mock;

import com.easytnt.commons.domain.Identity;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * @author Liguiqing
 * @since V3.0
 */

public interface Mock {
    Identity[] ids();

    void createData(JdbcTemplate jdbc);

    void userMocks(List<Mock> mocks);

    int order();
}