/*
 * Copyright (c) 2016,2017, easytnt All Rights Reserved. 深圳市易考试乐学测评有限公司 版权所有.
 */

package com.easytnt.ts.application.study;

import com.easytnt.ts.domain.model.school.clazz.Clazz;
import com.easytnt.ts.domain.model.school.clazz.ClazzRepository;
import com.easytnt.ts.domain.model.school.student.Student;
import com.easytnt.ts.domain.model.school.student.StudentRepository;

/**
 * 学习应用服务
 *
 * @author Liguiqing
 * @since V3.0
 */

public class StudyApplicationService {

    private StudentRepository studentRepository;

    private ClazzRepository clazzRepository;

    public void joinClazz(JoinClazzCommand command){
       // Course course = courseRepository.loadOfId(command.get)
        Student student = studentRepository.loadOfId(command.getStdudentId());
        Clazz clazz = clazzRepository.loadOfId(command.getClazzId());

        if(clazz.canStudyAndTeachIn()){
            this.studyInClazz();
        }
        //ClazzCourse course = new ClazzCourse(command.getSchoolId(),command.getTermId(),command.getClazzId(),command.get);
    }

    public void studyInClazz(){}
}