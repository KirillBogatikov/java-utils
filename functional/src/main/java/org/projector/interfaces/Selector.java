package org.projector.interfaces;

public interface Selector<V, O> {
    public O select(V input, Loop loop);
}
