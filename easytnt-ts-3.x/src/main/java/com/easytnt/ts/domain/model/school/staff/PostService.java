/*
 * Copyright (c) 2016,2017, easytnt All Rights Reserved. 深圳市易考试乐学测评有限公司 版权所有.
 */

package com.easytnt.ts.domain.model.school.staff;

import com.easytnt.ts.domain.model.school.SchoolId;

import java.util.Date;

/**
 * 职务创建接口
 *
 * @author Liguiqing
 * @since V3.0
 */

public interface PostService {

    HeadMaster headMasterFrom(SchoolId schoolId,String identity,Period period);

    GradeMaster gradeMasterFrom(SchoolId schoolId,String identity,Period period);

    GradeMaster clazzMasterFrom(SchoolId schoolId,String identity,Period period);
}