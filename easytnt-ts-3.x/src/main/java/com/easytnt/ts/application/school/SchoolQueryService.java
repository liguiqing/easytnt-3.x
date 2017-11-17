/*
 * Copyright (c) 2016,2017, easytnt All Rights Reserved. 深圳市易考试乐学测评有限公司 版权所有.
 */

package com.easytnt.ts.application.school;

import com.easytnt.ts.application.school.data.GradeData;
import com.easytnt.ts.domain.model.school.School;
import com.easytnt.ts.domain.model.school.SchoolId;
import com.easytnt.ts.domain.model.school.SchoolRepository;
import com.easytnt.ts.domain.model.school.SchoolType;

import java.util.List;

/**
 * 学校查询服务
 *
 * @author Liguiqing
 * @since V3.0
 */

public class SchoolQueryService {

    private SchoolRepository schoolRepository;

    public List<GradeData> schoolGrade(String schoolId){
        School school = schoolRepository.loadOfId(new SchoolId(schoolId));
        SchoolType type = school.type();
        //TODO
        return null;
    }

}