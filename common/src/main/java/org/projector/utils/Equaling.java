package org.projector.utils;

import java.util.Arrays;

public class Equaling {

    public static boolean equals(Object... values) {
        if (values.length < 2) {
            return true;
        }

        Object last = values[0];

        for (int i = 1; i < values.length; i++) {
            Object current = values[i];

            if (last == current) {
                continue;
            }

            if (last == null) {
                return false;
            }
            if (current == null) {
                return false;
            }

            if (!last.equals(current)) {
                return false;
            }
        }

        return true;
    }

    @SafeVarargs
	public static <T> boolean equalsArrays(T[]... arrays) {
        if (arrays.length < 2) {
            return true;
        }

        T[] first = arrays[0];
        for (int i = 1; i < arrays.length; i++) {
            T[] current = arrays[i];

            if ((first == null && current == null) ||
                Arrays.equals(first, current)) {
                continue;
            }

            return false;
        }

        return true;
    }
}
