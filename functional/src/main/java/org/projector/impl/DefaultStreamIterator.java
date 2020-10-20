package org.projector.impl;

import java.util.NoSuchElementException;

import org.projector.interfaces.Stream;
import org.projector.interfaces.StreamIterator;

public class DefaultStreamIterator<ValueType> implements StreamIterator<ValueType> {
    protected Stream<ValueType> stream;
    protected int index;
    
    public DefaultStreamIterator(Stream<ValueType> stream) {
        this.stream = stream;
        this.index = -1;
    }
    
    @Override
    public synchronized boolean hasNext() {
        try {
            stream.get(index + 1);
        } catch(NoSuchElementException e) {
            return false;
        }
        
        return true;
    }

    @Override
    public synchronized ValueType next() {
        try {
            return stream.get(++index);
        } catch(NoSuchElementException e) {
            return null;
        }
    }

    @Override
    public void skip(int number) {
        index += number;
    }

}
