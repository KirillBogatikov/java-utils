package org.projector.types;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

public class TrioTest {
    @Test
    public void testHashCode() {
        Trio<Integer, Integer, Integer> first = new Trio<>(10, 15, 20);
        Trio<Integer, Integer, Integer> second = new Trio<>(10, 11, 12);
        assertNotEquals(first.hashCode(), second.hashCode());

        second = new Trio<>(10, 15, 20);
        assertEquals(first.hashCode(), second.hashCode());
    }

    @Test
    public void testEquals() {
        Trio<Integer, Integer, Integer> first = new Trio<>(10, 15, 20);
        Trio<Integer, Integer, Integer> second = new Trio<>(10, 11, 12);
        assertNotEquals(first, second);

        second = new Trio<>(10, 15, 20);
        assertEquals(first, second);
    }

    @Test
    public void testNoNPE() {
        Trio<Integer, Integer, Integer> first = new Trio<>(10, null, 20);
        Trio<Integer, Integer, Integer> second = new Trio<>(10, null, 20);
        assertEquals(first, second);

        first = new Trio<>(null, 10, 15);
        second = new Trio<>(10, null, 20);
        assertNotEquals(first, second);

        first = new Trio<>(null, 315123, -120);
        second = new Trio<>(null, 315123, -120);
        assertEquals(first, second);
    }
}
