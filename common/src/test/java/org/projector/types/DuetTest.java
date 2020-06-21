package org.projector.types;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

public class DuetTest {
    @Test
    public void testHashCode() {
        Duet<Integer, Integer> first = new Duet<>(10, 15);
        Duet<Integer, Integer> second = new Duet<>(10, 11);
        assertNotEquals(first.hashCode(), second.hashCode());

        second = new Duet<>(10, 15);
        assertEquals(first.hashCode(), second.hashCode());
    }

    @Test
    public void testEquals() {
        Duet<Integer, Integer> first = new Duet<>(10, 15);
        Duet<Integer, Integer> second = new Duet<>(10, 11);
        assertNotEquals(first, second);

        second = new Duet<>(10, 15);
        assertEquals(first, second);
    }

    @Test
    public void testNoNPE() {
        Duet<Integer, Integer> first = new Duet<>(10, null);
        Duet<Integer, Integer> second = new Duet<>(10, null);
        assertEquals(first, second);

        first = new Duet<>(null, 10);
        second = new Duet<>(10, null);
        assertNotEquals(first, second);

        first = new Duet<>(null, 315123);
        second = new Duet<>(null, 315123);
        assertEquals(first, second);
    }
}
