package org.projector.interfaces;

public interface MutableStreamIterator<ValueType> extends StreamIterator<ValueType> {
    public boolean remove();
    public void clear();
}
