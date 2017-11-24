/*
 * Copyright (c) 2016,2017, easytnt All Rights Reserved. 深圳市易考试乐学测评有限公司 版权所有.
 */

package com.easytnt.ts.application.school;

import com.easytnt.commons.util.DateUtilWrapper;
import com.easytnt.ts.application.school.data.CourseData;
import com.easytnt.ts.application.school.data.GradeData;
import com.easytnt.ts.domain.model.school.*;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 学校查询服务
 *
 * @author Liguiqing
 * @since V3.0
 */

public class SchoolQueryService {

    @Autowired
    private SchoolRepository schoolRepository;

    @Autowired
    private JdbcTemplate query;

    /**
     * 查询学校年级
     * @param schoolId
     * @return
     */
    @Transactional(readOnly = true)
    @Cacheable(value = "SchoolGradeCache", key = "#schoolId")
    public List<GradeData> schoolGrade(String schoolId){
        SchoolId schoolId1 = new SchoolId(schoolId);
        GradeNameGenerateStrategy nameGenerateStrategy =
                GradeNameGenerateStrategyFactory.lookup(schoolId1);
        School school = schoolRepository.loadOfId(schoolId1);
        List<Grade> grades = school.grades();
        ArrayList<GradeData> gs = Lists.newArrayList();
        for(Grade g:grades){
            gs.add(new GradeData(g.name(), g.seq().getSeq()));
        }
        return gs;
    }

    /**
     * 查询学校课程
     *
     * @param schoolId
     * @return
     */
    @Transactional(readOnly = true)
    @Cacheable(value = "SchoolCourseCache", key = "#schoolId")
    public List<CourseData> schoolCourses(String schoolId){
        String sql = " select name,subjectId,gradeName from (select name,subjectId,gradeName,gradeLevel from ts_course where schoolId is null union select name,subjectId,gradeName,gradeLevel from ts_course where schoolId=?) a order by gradeLevel";
        return query.query(sql, new RowMapper<CourseData>() {
            @Override
            public CourseData mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new CourseData(rs.getString("name"),rs.getString("subjectId"),rs.getString("gradeName"));
            }
        }, new Object[]{schoolId});
    }

}