package org.projector.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Map;

import org.junit.Test;

public class FunctionalTest {
    @Test
    public void testMap() {
        Integer[] array = new Integer[]{ 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
        Map<String, Integer> map = Functional.map((v, l) -> String.valueOf(10 - v), array);

        for (Integer v : array) {
            String key = String.valueOf(10 - v);
            assertTrue(map.containsKey(key));
            assertEquals(v, map.get(key));
        }
    }

    @Test(expected = NullPointerException.class)
    public void testNullMapper() {
        Integer[] array = new Integer[]{ 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
        @SuppressWarnings("unused")
		Map<String, Integer> map = Functional.map(null, array);
    }

    @Test(expected = NullPointerException.class)
    public void testMapNullArray() {
        Integer[] array = null;
        @SuppressWarnings("unused")
		Map<String, Integer> map = Functional.map((v, l) -> String.valueOf(10 - v), array);
    }

    @Test
    public void testSelect() {
        Integer[] array = new Integer[]{ 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
        List<Integer> result = Functional.select((v, l) -> (v + 10) * 20, array);
        for (int i = 0; i < array.length; i++) {
            Integer e = (array[i] + 10) * 20;
            assertEquals(e, result.get(i));
        }
    }

    @Test(expected = NullPointerException.class)
    public void testNullSelector() {
    	Integer[] array = new Integer[]{ 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
        @SuppressWarnings("unused")
		List<Integer> result = Functional.select(null, array);
    }

    @Test(expected = NullPointerException.class)
    public void testSelectNullArray() {
        Integer[] array = null;
        @SuppressWarnings("unused")
		List<Integer> result = Functional.select((v, l) -> (v + 10) * 20, array);
    }

    @Test
    public void testSelectLoop() {
        Integer[] array = new Integer[]{ 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
        List<Integer> result = Functional.select((v, l) -> {
            if (l.index() == 5) {
                l.skip();
                return -1;
            }

            if (l.index() == 7) {
                l.stop();
                return -1;
            }

            return (v + 10) * 20;
        }, array);

        /*
         * Stop at 7th element, skip 5th. Total => 6 elements in result
         */
        assertEquals(6, result.size());
        boolean containsFifth = false;

        for (Integer integer : result) {
            containsFifth |= integer == 6;
        }

        assertFalse(containsFifth);
    }

    @Test
    public void testMapLoop() {
        Integer[] array = new Integer[]{ 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
        Map<String, Integer> result = Functional.map((v, l) -> {
            if (l.index() == 5) {
                l.skip();
                return "";
            }

            if (l.index() == 7) {
                l.stop();
                return "";
            }

            return String.valueOf(10 - v);
        }, array);

        /*
         * Stop at 7th element, skip 5th. Total => 6 elements in result
         */
        assertEquals(6, result.size());
        assertFalse(result.containsValue(6));
    }
}
