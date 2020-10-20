package org.projector.impl;

import java.util.NoSuchElementException;

import org.projector.interfaces.MutableStreamIterator;
import org.projector.interfaces.Stream;

public class DefaultMutableStreamIterator<ValueType> extends DefaultStreamIterator<ValueType> implements MutableStreamIterator<ValueType> {
    public DefaultMutableStreamIterator(Stream<ValueType> stream) {
        super(stream);
    }

    @Override
    public boolean remove() {
        try {
            stream.remove(index--);
        } catch(NoSuchElementException e) {
            return false;
        }
        return true;
    }

    @Override
    public void clear() {
        try {
            while(true) {
                stream.remove(0);
            }
        } catch(NoSuchElementException e) {}
    }

}
