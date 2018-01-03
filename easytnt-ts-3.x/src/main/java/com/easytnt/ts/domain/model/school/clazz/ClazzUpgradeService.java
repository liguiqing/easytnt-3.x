/*
 * Copyright (c) 2016,2018, easytnt All Rights Reserved. 深圳市易考试乐学测评有限公司 版权所有.
 */

package com.easytnt.ts.domain.model.school.clazz;

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

public class ClazzUpgradeService  {

    private ClazzRepository clazzRepository;

    private StudentRepository studentRepository;

    public ClazzUpgradeService(ClazzRepository clazzRepository,StudentRepository studentRepository){
        this.clazzRepository = clazzRepository;
        this.studentRepository = studentRepository;
    }

    public void upGrade(Clazz clazz,Term term){
        clazz.upGrade(term);
        if(!clazz.canBeStudied())
            return;
        List<Student> students = this.studentRepository.studentsOf(clazz.clazzId());
        Grade grade = clazz.termGrade(term);
        GradeCourseable courseable = GradeCourseableFactory.lookup(clazz.schoolId());
        Collection<Course> gradeCourses = courseable.courseOf(grade);
        if(students != null){
            for(Student student:students){
                for(Course course : gradeCourses)
                student.changeStudyClazzOfCourse(clazz,grade,course, term.timeSpan().starts(),term.timeSpan().ends());
                studentRepository.save(student);
            }
        }
    }

    public void upGrade(Grade grade,Term term){
        List<Clazz> clazzes = clazzRepository.listGradeClazzes(grade,term);
    }

}