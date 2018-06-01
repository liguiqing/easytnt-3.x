/*
 * Copyright (c) 2016,2018, easytnt All Rights Reserved. 深圳市易考试乐学测评有限公司 版权所有.
 */

package com.easytnt.exam.domain.exam;

import com.easytnt.share.domain.school.Grade;
import org.junit.Test;

/**
 * @author Liguiqing
 * @since V3.0
 */

public class ExamTest {

    @Test
    public void testConstructor(){

        ExamCreator creator = new ExamCreator("a",CreatorOrgType.School,"a");
        Exam exam = new Exam(new ExamId(),new ProjectId(),new TargetOrgId(),
                Grade.G1(),ExamScope.School,creator);
    }

}