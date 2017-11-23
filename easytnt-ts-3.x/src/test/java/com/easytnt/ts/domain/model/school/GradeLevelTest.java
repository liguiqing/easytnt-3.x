package com.easytnt.ts.domain.model.school;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Liguiqing
 * @since V3.0
 */

public class GradeLevelTest {

    @Test
    public void testNext()throws Exception{
        GradeLevel one = GradeLevel.One;
        assertTrue(one.next() == GradeLevel.Two);
        assertTrue(one.next().next() == GradeLevel.Three);
        assertTrue(one.next().next().next() == GradeLevel.Four);
        assertTrue(one.next().next().next().next() == GradeLevel.Five);
        assertTrue(one.next().next().next().next().next() == GradeLevel.Six);
        assertTrue(one.next().next().next().next().next().next() == GradeLevel.Seven);
        assertTrue(one.next().next().next().next().next().next().next() == GradeLevel.Eight);
        assertTrue(one.next().next().next().next().next().next().next().next() == GradeLevel.Nine);
        assertTrue(one.next().next().next().next().next().next().next().next().next() == GradeLevel.Ten);
        assertTrue(one.next().next().next().next().next().next().next().next().next().next() == GradeLevel.Eleven);
        assertTrue(one.next().next().next().next().next().next().next().next().next().next().next() == GradeLevel.Twelve);
        assertNull(one.next().next().next().next().next().next().next().next().next().next().next().next() );
    }

    @Test
    public void testFromLevel()throws Exception{
        GradeLevel one = GradeLevel.fromLevel(1);
        assertTrue(one == GradeLevel.One);

        GradeLevel four = GradeLevel.fromLevel(4);
        assertTrue(four == GradeLevel.Four);

        GradeLevel ten = GradeLevel.fromLevel(10);
        assertTrue(ten == GradeLevel.Ten);

        GradeLevel twelve = GradeLevel.fromLevel(12);
        assertTrue(twelve == GradeLevel.Twelve);

        GradeLevel thirteen = GradeLevel.fromLevel(13);
        assertNull(thirteen);

        GradeLevel zero = GradeLevel.fromLevel(0);
        assertNull(zero);
    }

    @Test
    public void  testFromName()throws Exception{
        GradeLevel one = GradeLevel.fromName("One");
        assertTrue(one == GradeLevel.One);

        GradeLevel four = GradeLevel.fromName("Four");
        assertTrue(four == GradeLevel.Four);

        GradeLevel ten = GradeLevel.fromName("Ten");
        assertTrue(ten == GradeLevel.Ten);

        GradeLevel twelve = GradeLevel.fromName("Twelve");
        assertTrue(twelve == GradeLevel.Twelve);

        GradeLevel thirteen = GradeLevel.fromName("Thirteen");
        assertNull(thirteen);

        GradeLevel zero = GradeLevel.fromName("Zero");
        assertNull(zero);

        GradeLevel other = GradeLevel.fromName("asdfasdf");
        assertNull(other);

        GradeLevel Null = GradeLevel.fromName(null);
        assertNull(Null);
    }
}