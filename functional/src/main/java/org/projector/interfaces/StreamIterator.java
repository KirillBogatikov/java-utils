package org.projector.interfaces;

public interface StreamIterator<ValueType> {
    public boolean hasNext();
    public ValueType next();
    public void skip(int number);
}
