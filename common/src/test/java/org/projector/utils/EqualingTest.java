package org.projector.utils;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class EqualingTest {
    @Test
    public void testEqualsEmpty() {
        assertTrue(Equaling.equals());
    }

    @Test
    public void testEqualsInt() {
        assertTrue(Equaling.equals(1, 1));
    }

    @Test
    public void testNotEqualsInt() {
        assertFalse(Equaling.equals(1, 2));
    }

    @Test
    public void testEqualsString() {
        assertTrue(Equaling.equals("hello", "hello"));
    }

    @Test
    public void testNotEqualsString() {
        assertFalse(Equaling.equals("hello", "bye"));
    }

    @Test
    public void testEqualsNull() {
        assertTrue(Equaling.equals(null, null));
    }

    @Test
    public void testNotEqualsWithNull() {
        assertFalse(Equaling.equals("hello", null));
        assertFalse(Equaling.equals(null, "hello"));
    }

    @Test
    public void testEqualsVararg() {
        assertTrue(Equaling.equals("one", "one", "one"));
        assertTrue(Equaling.equals(1, 1, 1));
        assertTrue(Equaling.equals(null, null, null));
    }

    @Test
    public void testNotEqualsVararg() {
        assertFalse(Equaling.equals("one", "two", "three"));
        assertFalse(Equaling.equals(1, 2, 3));
    }

    @Test
    public void testEqualsArrays() {
        Integer[] a = new Integer[]{ 1, 2, 3, 4, 5 };
        Integer[] b = new Integer[]{ 1, 2, 3, 4, 5 };

        assertTrue(Equaling.equalsArrays(a, a)); //by pointer
        assertTrue(Equaling.equalsArrays(a, b)); //by value
    }
}
