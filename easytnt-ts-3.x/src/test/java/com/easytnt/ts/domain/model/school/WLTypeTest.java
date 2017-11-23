package com.easytnt.ts.domain.model.school;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Liguiqing
 * @since V3.0
 */

public class WLTypeTest {

    @Test
    public void testFromName()throws Exception{
        WLType w = WLType.Liberal;
        WLType l = WLType.fromName("Liberal");
        WLType n = WLType.fromName("None");
        WLType s = WLType.fromName("Science");
        WLType none1 = WLType.fromName("df");
        WLType none2 = WLType.fromName("");
        WLType none3 = WLType.fromName(null);

        assertTrue(w == l);
        assertTrue(n == WLType.None);
        assertTrue(s == WLType.Science);
        assertNull(none1);
        assertNull(none2);
        assertNull(none3);

        assertTrue(l.getValue() == 1);
        assertTrue(s.getValue() == 2);
        assertTrue(n.getValue() == 0);
    }
}