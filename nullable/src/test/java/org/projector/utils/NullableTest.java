package org.projector.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.projector.utils.Nullable.checkNotNull;
import static org.projector.utils.Nullable.firstOrDefault;
import static org.projector.utils.Nullable.ifNotNull;
import static org.projector.utils.Nullable.ifNull;
import static org.projector.utils.Nullable.lastOrDefault;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import org.junit.Test;

public class NullableTest {
    @Test(expected = NullPointerException.class)
    public void testCheckNotNull() {
        checkNotNull(null, "", "");
    }

    @Test
    public void testIfNotNull() {
        AtomicBoolean notNull = new AtomicBoolean(false);
        ifNotNull("Hello", (s) -> {
            notNull.set(true);
            return null;
        });
        assertTrue(notNull.get());

        notNull.set(false);
        ifNotNull(null, (o) -> {
            notNull.set(true);
            return null;
        });
        assertFalse(notNull.get());
    }

    @Test
    public void testIfNull() {
        AtomicBoolean isNull = new AtomicBoolean(false);
        ifNull("Hello", () -> {
            isNull.set(true);
            return null;
        });
        assertFalse(isNull.get());

        isNull.set(false);
        ifNull(null, () -> {
            isNull.set(true);
            return null;
        });
        assertTrue(isNull.get());
    }

    @Test
    public void testFirstOrDefaultFirstValue() {
        List<Integer> list = Arrays.asList(5, 4, 3, 2, 1);
        Integer expected = 5;
        assertEquals(expected, firstOrDefault(list, -1));
    }

    @Test
    public void testFirstOrDefaultSecondValue() {
        List<Integer> list = Arrays.asList(null, 4, 3, 2, 1);
        Integer expected = 4;
        assertEquals(expected, firstOrDefault(list, -1));
    }

    @Test
    public void testFirstOrDefaultDefault() {
        List<Integer> list = Arrays.asList(null, null, null);
        Integer expected = -200;
        assertEquals(expected, firstOrDefault(list, expected));

        list = Arrays.asList();
        expected = -200;
        assertEquals(expected, firstOrDefault(list, expected));

        list = null;
        expected = -200;
        assertEquals(expected, firstOrDefault(list, expected));
    }

    @Test
    public void testLastOrDefaultLastValue() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        Integer expected = 5;
        assertEquals(expected, lastOrDefault(list, -1));
    }

    @Test
    public void testFirstOrDefaultPenultimateValue() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, null);
        Integer expected = 4;
        assertEquals(expected, lastOrDefault(list, -1));
    }

    @Test
    public void testLastOrDefaultDefault() {
        List<Integer> list = Arrays.asList(null, null, null);
        Integer expected = -200;
        assertEquals(expected, lastOrDefault(list, expected));

        list = Arrays.asList();
        expected = -200;
        assertEquals(expected, lastOrDefault(list, expected));

        list = null;
        expected = -200;
        assertEquals(expected, lastOrDefault(list, expected));
    }
}
