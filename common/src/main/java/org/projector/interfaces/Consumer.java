package org.projector.interfaces;

public interface Consumer<V, O> {
    public O consume(V value);
}
