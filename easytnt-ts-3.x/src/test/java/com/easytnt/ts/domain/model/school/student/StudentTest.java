/*
 * Copyright (c) 2016,2017, easytnt All Rights Reserved. 深圳市易考试乐学测评有限公司 版权所有.
 */

package com.easytnt.ts.domain.model.school.student;

import com.easytnt.commons.util.DateUtilWrapper;
import com.easytnt.ts.domain.model.school.Grade;
import com.easytnt.ts.domain.model.school.GradeLevel;
import com.easytnt.ts.domain.model.school.SchoolId;
import com.easytnt.ts.domain.model.school.StudyYear;
import com.easytnt.ts.domain.model.school.clazz.Clazz;
import com.easytnt.ts.domain.model.school.clazz.ClazzId;
import com.easytnt.ts.domain.model.school.staff.Teacher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.Date;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author Liguiqing
 * @since V3.0
 */

@RunWith(PowerMockRunner.class)
public class StudentTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();


    @Test
    public void testConstruct()throws Exception{
        Student s1 = new Student(new SchoolId("12345678"), new StudentId("12345678"), "name", "1234");
        assertNotNull(s1);
    }

    @Test
    public void testConstruct1()throws Exception{
        thrown.expect(NullPointerException.class);
        thrown.expectMessage("请提供学生所在学校");
        new Student(null, new StudentId("12345678"), "name", "1234");
    }

    @Test
    public void testConstruct2()throws Exception{
        thrown.expect(NullPointerException.class);
        thrown.expectMessage("请提供学生唯一标识");
        new Student(new SchoolId("12345678"), null, "name", "1234");
    }

    @Test
    public void testChangeManagedClazz()throws Exception{
        Student s1 = new Student(new SchoolId("12345678"), new StudentId("12345678"), "name", "1234");
        Clazz c1 = mock(Clazz.class);
        ClazzId clazzId1 = new ClazzId("12345678");
        when(c1.clazzId()).thenReturn(clazzId1);
        when(c1.canBeManaged()).thenReturn(true);
        s1.changeManagedClazz(c1, DateUtilWrapper.today(),null);
        assertTrue(s1.belongTos().size() == 1);
        Date starts = DateUtilWrapper.today();
        Date ends = DateUtilWrapper.tomorrow();
        s1.changeManagedClazz(c1, starts,ends);
        assertTrue(s1.belongTos().size() == 1);
        BelongToClazz belongTo1 = s1.belongTos().iterator().next();
        assertNotNull(belongTo1);
        assertEquals(belongTo1.period().ends(),ends);

        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("学生所属班级不能管理学生");
        when(c1.canBeManaged()).thenReturn(false);
        s1.changeManagedClazz(c1, starts,ends);
    }

    @Test
    public void testAddStudy()throws Exception{
        Student s1 = new Student(new SchoolId("12345678"), new StudentId("12345678"), "name", "1234");
        Clazz c1 = mock(Clazz.class);
        ClazzId clazzId1 = new ClazzId("12345678");
        when(c1.clazzId()).thenReturn(clazzId1);
        when(c1.canBeStudied()).thenReturn(true);
        Teacher teacher = mock(Teacher.class);
        when(teacher.isTeaching()).thenReturn(true);
        Date starts = DateUtilWrapper.today();
        Date ends = DateUtilWrapper.tomorrow();

        s1.addStudy(c1,new Grade("七年级", GradeLevel.Seven,new StudyYear("2017-2018")),teacher,starts,ends);
        Study st1 = s1.studies().iterator().next();
        assertEquals(st1.period().ends(),ends);
    }

}