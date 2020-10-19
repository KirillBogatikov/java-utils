package org.projector.impl;

import static org.projector.utils.Nullable.checkNotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.projector.interfaces.Consumer;
import org.projector.interfaces.Selector;
import org.projector.interfaces.Stream;
import org.projector.interfaces.VoidConsumer;
import org.projector.types.Duet;
import org.projector.types.NotNullValue;
import org.projector.utils.Equaling;

public class DefaultStream<ValueType> implements Stream<ValueType> {
    private List<ValueType> values;
    private Iterator<ValueType> iterator;
    private boolean mutable;

    public DefaultStream(List<ValueType> values) {
        checkNotNull(values, "Values list");

        this.values = values;
        this.iterator = values.iterator();
    }

    @Override
    public boolean isMutable() {
    	return mutable;
    }
    
    @Override
    public void setMutable(boolean mutable) {
    	this.mutable = mutable;
    }
    
    @Override
    public ValueType remove(int index) {
    	return values.remove(index);
    }
    
    @Override
    public boolean remove(int index, ValueType value) {
    	ValueType valueAtIndex = values.get(index);
    	if (Equaling.equals(value, valueAtIndex)) {
    		values.remove(index);
    		return true;
    	}
    	
    	return false;
    }
    
    @Override
    public ValueType next() {
        return iterator.next();
    }

    @Override
    public boolean hasNext() {
        return iterator.hasNext();
    }

    @Override
    public void foreach(VoidConsumer<ValueType> consumer) {
        checkNotNull(consumer, "Consumer");
        for (ValueType value : values) {
            consumer.consume(value);
        }
    }

    @Override
    public void foreach(Consumer<ValueType, ValueType> consumer) {
        checkNotNull(consumer, "Consumer");
        for (int i = 0; i < values.size(); i++) {
            values.set(i, consumer.consume(values.get(i)));
        }
    }

    @Override
    public <OutType> Stream<OutType> select(Selector<ValueType, OutType> selector) {
        checkNotNull(selector, "Selector");
        ArrayList<OutType> list = new ArrayList<>();

        DefaultLoop loop = new DefaultLoop();
        for (int i = 0; i < values.size(); i++) {
            loop.setIndex(i);
            loop.setAction(DefaultLoop.ADD);

            OutType value = selector.select(values.get(i), loop);
            if (loop.getAction() == DefaultLoop.ADD) {
                list.add(value);
            } else if (loop.getAction() == DefaultLoop.STOP) {
                break;
            }
        }
        ;

        return new DefaultStream<>(list);
    }

    @Override
    public <KeyType> Map<KeyType, ValueType> map(Selector<ValueType, KeyType> selector) {
        checkNotNull(selector, "Selector");

        HashMap<KeyType, ValueType> result = new HashMap<>();

        DefaultLoop loop = new DefaultLoop();
        for (int i = 0; i < values.size(); i++) {
            loop.setIndex(i);
            loop.setAction(DefaultLoop.ADD);

            KeyType key = selector.select(values.get(i), loop);
            if (loop.getAction() == DefaultLoop.ADD) {
                result.put(key, values.get(i));
            } else if (loop.getAction() == DefaultLoop.STOP) {
                break;
            }
        }
        ;

        return result;
    }

    @Override
    public Stream<ValueType> where(Consumer<ValueType, Boolean> consumer) {
        checkNotNull(consumer, "Consumer");
        ArrayList<ValueType> list = new ArrayList<>();

        for (ValueType value : values) {
            if (consumer.consume(value)) {
                list.add(value);
            }
        }

        return new DefaultStream<>(list);
    }

    @Override
    public <OutType> Stream<OutType> group(Consumer<Duet<ValueType, OutType>, OutType> groupEditor,
            Consumer<ValueType, Integer> consumer) {
        checkNotNull(consumer, "Consumer");
        ArrayList<OutType> list = new ArrayList<>();

        for (ValueType value : values) {
            int id = consumer.consume(value);
            if (id < 0) {
                throw new IllegalStateException("Consumer returns negative group ID");
            }

            boolean ensured = id >= list.size();
            if (ensured) {
                for (int i = list.size(); i < id + 1; i++) {
                    list.add(null);
                }
            }

            OutType out = list.get(id);
            out = groupEditor.consume(new Duet<>(value, out));
            list.set(id, out);
        }

        return new DefaultStream<>(list);
    }

    @Override
    public boolean any(Consumer<ValueType, Boolean> consumer) {
        checkNotNull(consumer, "Consumer");
        for (ValueType value : values) {
            if (consumer.consume(value)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean all(Consumer<ValueType, Boolean> consumer) {
        checkNotNull(consumer, "Consumer");
        return !any(v -> !consumer.consume(v));
    }

    @Override
    public Float sumFloat(Consumer<ValueType, Float> consumer) {
        checkNotNull(consumer, "Consumer");
        NotNullValue<Float> sum = new NotNullValue<>(0.0f);

        foreach(v -> {
            sum.set(sum.get() + consumer.consume(v));
        });

        return sum.get();
    }

    @Override
    public Double sumDouble(Consumer<ValueType, Double> consumer) {
        checkNotNull(consumer, "Consumer");
        NotNullValue<Double> sum = new NotNullValue<>(0.0);

        foreach(v -> {
            sum.set(sum.get() + consumer.consume(v));
        });

        return sum.get();
    }

    @Override
    public Integer sumInt(Consumer<ValueType, Integer> consumer) {
        checkNotNull(consumer, "Consumer");
        NotNullValue<Integer> sum = new NotNullValue<>(0);

        foreach(v -> {
            sum.set(sum.get() + consumer.consume(v));
        });

        return sum.get();
    }

    @Override
    public Long sumLong(Consumer<ValueType, Long> consumer) {
        checkNotNull(consumer, "Consumer");
        NotNullValue<Long> sum = new NotNullValue<>(0L);

        foreach(v -> {
            sum.set(sum.get() + consumer.consume(v));
        });

        return sum.get();
    }

    @Override
    public Double average(Consumer<ValueType, Double> consumer) {
        checkNotNull(consumer, "Consumer");
        NotNullValue<Double> sum = new NotNullValue<>(0.0);

        foreach(v -> {
            sum.set(sum.get() + consumer.consume(v));
        });

        return sum.get() / values.size();
    }

    @Override
    public Double max(Consumer<ValueType, Double> consumer) {
        checkNotNull(consumer, "Consumer");
        NotNullValue<Double> max = new NotNullValue<>(-Double.MAX_VALUE);

        foreach(v -> {
            double result = consumer.consume(v);
            if (max.get() < result) {
                max.set(result);
            }
        });

        return max.get();
    }

    @Override
    public Double min(Consumer<ValueType, Double> consumer) {
        checkNotNull(consumer, "Consumer");
        NotNullValue<Double> min = new NotNullValue<>(Double.MAX_VALUE);

        foreach(v -> {
            double result = consumer.consume(v);
            if (min.get() > result) {
                min.set(result);
            }
        });

        return min.get();
    }

    @Override
    public List<ValueType> toList() {
        return Collections.unmodifiableList(values);
    }
}
