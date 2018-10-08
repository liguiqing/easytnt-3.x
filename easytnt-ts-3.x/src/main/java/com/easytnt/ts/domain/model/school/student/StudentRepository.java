/*
 * Copyright (c) 2016,2017, easytnt All Rights Reserved. 深圳市易考试乐学测评有限公司 版权所有.
 */

package com.easytnt.ts.domain.model.school.student;

import com.easytnt.commons.domain.EntityRepository;
import com.easytnt.ts.domain.model.school.clazz.ClazzId;

import java.util.List;

/**
 * @author Liguiqing
 * @since V3.0
 */

public interface StudentRepository extends EntityRepository<Student,StudentId> {

    List<Student> studentsOf(ClazzId clazzId);
}