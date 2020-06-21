package org.projector.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.projector.utils.Nullable.checkNotNull;

public class Functional {
    public interface Mapper<K, V> {
        public K keyFor(V value, LoopController loop);
    }

    public interface Selector<V, O> {
        public O select(V input, LoopController loop);
    }

    public interface LoopController {
        void skip();
        void stop();
        int index();
    }

    private static class DefaultLoopController implements LoopController {
        static final int ADD = 0, SKIP = 1, STOP = 2;

        int index;
        int action;

        public void skip() {
            action = SKIP;
        }

        public void stop() {
            action = STOP;
        }

        public int index() {
            return index;
        }
    }

    @SafeVarargs
	public static <K, V> Map<K, V> map(Mapper<K, V> mapper, V... values) {
        checkNotNull(mapper, "Mapper", null);
        checkNotNull(mapper, "Values array", "can not be null, but can be empty");
        Map<K, V> result = new HashMap<>();

		DefaultLoopController loop = new DefaultLoopController();
        for (int i = 0; i < values.length; i++) {
            loop.index = i;
            loop.action = DefaultLoopController.ADD;
            K key = mapper.keyFor(values[i], loop);
			
			if (loop.action == DefaultLoopController.ADD) result.put(key, values[i]);
            else if (loop.action == DefaultLoopController.STOP) break;
        }
        return result;
    }

    @SafeVarargs
	public static <V, O> List<O> select(Selector<V, O> selector, V... values) {
        checkNotNull(selector, "Selector", null);
        List<O> result = new ArrayList<>();

        DefaultLoopController loop = new DefaultLoopController();
        for (int i = 0; i < values.length; i++) {
            loop.index = i;
            loop.action = DefaultLoopController.ADD;
            O out = selector.select(values[i], loop);

            if (loop.action == DefaultLoopController.ADD) result.add(out);
            else if (loop.action == DefaultLoopController.STOP) break;
        }
        return result;
    }
}
