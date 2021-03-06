/*
 * Copyright (c) 2016,2017, easytnt All Rights Reserved. 深圳市易考试乐学测评有限公司 版权所有.
 */

package com.easytnt.ts.domain.model.school.clazz;

import com.easytnt.commons.domain.EntityRepository;
import com.easytnt.ts.domain.model.school.Grade;
import com.easytnt.ts.domain.model.school.term.Term;

import java.util.List;

/**
 * @author Liguiqing
 * @since V3.0
 */

public interface ClazzRepository extends EntityRepository<Clazz,ClazzId> {

    List<Clazz> listGradeClazzes(Grade grade, Term term);
}