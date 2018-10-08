/*
 * Copyright (c) 2016,2017, easytnt All Rights Reserved. 深圳市易考试乐学测评有限公司 版权所有.
 */

package com.easytnt.ts.infrastructure.strategy;

import com.easytnt.ts.domain.model.school.Course;
import com.easytnt.ts.domain.model.school.Grade;
import com.easytnt.ts.domain.model.school.GradeCourseable;
import com.easytnt.ts.domain.model.school.common.Configable;
import com.easytnt.ts.domain.model.school.common.Configuation;
import com.easytnt.ts.domain.model.school.common.ConfiguationFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

/**
 * 年级默认教学课程
 *
 * @author Liguiqing
 * @since V3.0
 */

public class DefaultGradeCourseable implements GradeCourseable,Configable {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private  Configuation configuation = new Configuation("年级默认教学课程",getClass().getName(),
            "#link#defaultGradeCourse.html");

    public DefaultGradeCourseable(){
        ConfiguationFactory.register(this);
    }

    @Override
    @Cacheable(value = "DefaultGradeCourseCache", key = "#grade.name")
    public Collection<Course> courseOf(Grade grade) {
        String sql = "select name,subjectId from ts_course where schoolId is null and gradeLevel is null " +
                "union  select name,subjectId from ts_course where schoolId is null and gradeLevel=?; ";
        return this.jdbcTemplate.query(sql,new RowMapper<Course>(){

            @Override
            public Course mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new Course(rs.getString("name"), rs.getString("subjectId"));
            }
        },new Object[]{grade.seq().getSeq()});
    }

    @Override
    public Configuation config() {
        return configuation;
    }
}