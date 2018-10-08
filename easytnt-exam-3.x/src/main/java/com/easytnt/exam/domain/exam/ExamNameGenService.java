/*
 * Copyright (c) 2016,2018, easytnt All Rights Reserved. 深圳市易考试乐学测评有限公司 版权所有.
 */

package com.easytnt.exam.domain.exam;

import com.easytnt.share.domain.school.Grade;
import com.easytnt.share.domain.school.StudyYear;
import com.easytnt.share.domain.school.Term;

/**
 * 考试名称生成服务
 *
 * @author Liguiqing
 * @since V3.0
 */

public interface ExamNameGenService {

    String genName(String school,Grade grade);

    String genName(String school,Grade grade,StudyYear year,Term term);
}