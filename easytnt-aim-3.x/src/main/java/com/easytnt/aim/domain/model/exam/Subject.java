/*
 * Copyright (c) 2016,2018, easytnt All Rights Reserved. 深圳市易考试乐学测评有限公司 版权所有.
 */

package com.easytnt.aim.domain.model.exam;

import com.easytnt.commons.domain.ValueObject;

/**
 * 考试科目
 * 考试科目不同于教学中的课程,一个考试科目至少包含一个教学课程,单科的考试可以教学课程通用.
 * K12中考试科目与教学科目的关系:
 * 考试科目            教学课程
 *  语文                语文
 *  数学                数学
 *  英语                英语
 *  政治                政治
 *  历史                历史
 *  地理                地理
 *  物理                物理
 *  化学                化学
 *  生物                生物
 *  文综                政史地
 *  理综                物化生
 * @author Liguiqing
 * @since V3.0
 */

public class Subject extends ValueObject{

    private SubjectId subjectId;

    private String name;

    private boolean single;

}