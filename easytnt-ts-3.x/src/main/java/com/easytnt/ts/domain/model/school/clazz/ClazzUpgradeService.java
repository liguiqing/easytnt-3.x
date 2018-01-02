/*
 * Copyright (c) 2016,2018, easytnt All Rights Reserved. 深圳市易考试乐学测评有限公司 版权所有.
 */

package com.easytnt.ts.domain.model.school.clazz;

import com.easytnt.commons.event.progress.ProgressEvent;
import com.easytnt.commons.event.progress.ProgressEventBus;
import com.easytnt.commons.event.progress.ProgressListener;
import com.easytnt.ts.domain.model.school.Course;
import com.easytnt.ts.domain.model.school.Grade;
import com.easytnt.ts.domain.model.school.GradeCourseable;
import com.easytnt.ts.domain.model.school.GradeCourseableFactory;
import com.easytnt.ts.domain.model.school.student.Student;
import com.easytnt.ts.domain.model.school.student.StudentRepository;
import com.easytnt.ts.domain.model.school.term.Term;

import java.util.Collection;
import java.util.List;

/**
 * 班级升级服务，班级从低年级升级到高年级
 *
 * @author Liguiqing
 * @since V3.0
 */

public class ClazzUpgradeService implements ProgressListener {

    private ClazzRepository clazzRepository;

    private StudentRepository studentRepository;

    private int i = 0;

    public ClazzUpgradeService(){
        ProgressEventBus.register(this);
    }

    public void upGrade(Clazz clazz,Term term){
        clazz.upGrade(term);
        List<Student> students = this.studentRepository.studentsOf(clazz.clazzId());
        Grade grade = clazz.termGrade(term);
        GradeCourseable courseable = GradeCourseableFactory.lookup(clazz.schoolId());
        Collection<Course> gradeCourses = courseable.courseOf(grade);

        if(students != null){
            for(Student student:students){
                //student.addStudy();

            }
        }
    }

    @Override
    public void update(ProgressEvent event) {
        while(i>100){
            i++;
            try {
                Thread.sleep(1000l);
            } catch (InterruptedException e) {

            }
        }
    }

    @Override
    public void destry() {

    }
}