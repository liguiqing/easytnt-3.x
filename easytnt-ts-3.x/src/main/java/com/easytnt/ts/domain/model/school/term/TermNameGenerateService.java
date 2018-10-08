/*
 * Copyright (c) 2016,2017, easytnt All Rights Reserved. 深圳市易考试乐学测评有限公司 版权所有.
 */

package com.easytnt.ts.domain.model.school.term;

import com.easytnt.ts.domain.model.school.School;
import com.easytnt.ts.domain.model.school.StudyYear;

/**
 * 学期名称生成服务
 *
 * @author Liguiqing
 * @since V3.0
 */

public interface TermNameGenerateService {

    public String  genTermName(School school, StudyYear studyYear, TermOrder termOrder);
}