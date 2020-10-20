package org.projector.impl;

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
        } catch(IllegalStateException e) {
            return false;
        }
        return true;
    }

    @Override
    public void clear() {
        int i = 0;
        try {
            while(true) {
                stream.remove(i++);
            }
        } catch(IllegalStateException e) {}
    }

}
