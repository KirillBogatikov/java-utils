package org.projector.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.NoSuchElementException;

import org.junit.Test;
import org.projector.interfaces.MutableStreamIterator;

public class DefaultMutableStreamIteratorTest {

    @Test
    public void testClearEmpty() {
        DefaultStream<String> stream = new DefaultStream<>();
        stream.setMutable(true);
        
        MutableStreamIterator<String> iterator = stream.iterate();
        iterator.clear();
        
        try {
            stream.get(0);
            fail("Should be empty");
        } catch(NoSuchElementException e) {}
    }

    @Test
    public void testClearFromZero() {
        DefaultStream<String> stream = new DefaultStream<>("Kirill", "Andrew", "Alex", "Ivan", "Sosiska");
        stream.setMutable(true);
        
        MutableStreamIterator<String> iterator = stream.iterate();
        iterator.clear();
        
        try {
            stream.get(0);
            fail("Should be empty");
        } catch(NoSuchElementException e) {}
    }

    @Test
    public void testClearFromIndex() {
        DefaultStream<String> stream = new DefaultStream<>("Kirill", "Andrew", "Alex", "Ivan", "Sosiska");
        stream.setMutable(true);
        
        MutableStreamIterator<String> iterator = stream.iterate();
        iterator.next();
        iterator.next();
        iterator.clear();
        
        try {
            stream.get(0);
            fail("Should be empty");
        } catch(NoSuchElementException e) {}
    }
    
    @Test
    public void testRemoveEmpty() {
        DefaultStream<String> stream = new DefaultStream<>();
        stream.setMutable(true);
        
        MutableStreamIterator<String> iterator = stream.iterate();
        assertFalse(iterator.remove());
    }
    
    @Test
    public void testRemoveCorrect() {
        DefaultStream<String> stream = new DefaultStream<>("Kirill", "Andrew", "Alex", "Ivan", "Sosiska");
        stream.setMutable(true);
        
        MutableStreamIterator<String> iterator = stream.iterate();
        iterator.next();
        iterator.next();
        assertTrue(iterator.remove());
        assertEquals("Alex", iterator.next());
    }
    
    @Test
    public void testRemoveOverLength() {
        DefaultStream<String> stream = new DefaultStream<>("Kirill", "Andrew", "Alex", "Ivan", "Sosiska");
        stream.setMutable(true);
        
        MutableStreamIterator<String> iterator = stream.iterate();
        iterator.next();
        iterator.next();
        iterator.next();
        iterator.next();
        iterator.next();
        iterator.next();
        assertFalse(iterator.remove());
    }
}
