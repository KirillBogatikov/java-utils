package org.projector.utils;

import static org.projector.utils.Nullable.checkNotNull;
import static org.projector.utils.Nullable.checkAllNotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.projector.impl.DefaultStream;
import org.projector.interfaces.Selector;
import org.projector.interfaces.Stream;

public class Functional {

    @SafeVarargs
    public static <K, V> Map<K, V> map(Selector<V, K> selector, V... values) {
        return createStream(values).map(selector);
    }

    @SafeVarargs
    public static <V, O> List<O> select(Selector<V, O> selector, V... values) {
        return createStream(values).select(selector).toList();
    }

    @SafeVarargs
    public static <T> Stream<T> createStream(T... values) {
        checkNotNull(values, "Values array", "can not be null, but can be empty");

        ArrayList<T> list = new ArrayList<>(Arrays.asList(values));
        return new DefaultStream<>(list);
    }

    @SafeVarargs
    public static <T> Stream<T> createJoinedStream(Collection<T>... parts) {
        checkAllNotNull(parts, "Parts");

        ArrayList<T> list = new ArrayList<>();

        for (Collection<T> c : parts) {
            list.addAll(c);
        }

        return new DefaultStream<T>(list);
    }
}
