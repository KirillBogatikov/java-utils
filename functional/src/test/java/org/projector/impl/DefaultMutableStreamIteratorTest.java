package org.projector.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.projector.interfaces.MutableStreamIterator;
import org.projector.interfaces.StreamIterator;

public class DefaultMutableStreamIteratorTest {
    @Test
    public void testNextSingleIterator() {
        DefaultStream<String> stream = new DefaultStream<>("Hello", "world", "man");
        stream.setMutable(true);
        
        MutableStreamIterator<String> iterator = stream.iterate();
        assertEquals("Hello", iterator.next());
        assertEquals("world", iterator.next());
        assertEquals("man", iterator.next());
    }
    
    @Test
    public void testNextMultiIterator() {
        DefaultStream<String> stream = new DefaultStream<>("Hello", "world", "man");
        stream.setMutable(true);
        
        MutableStreamIterator<String> firstIterator = stream.iterate();
        assertEquals("Hello", firstIterator.next());
                
        StreamIterator<String> secondIterator = stream.iterate();
        assertEquals("Hello", secondIterator.next());
        assertEquals("world", secondIterator.next());
        assertEquals("man", secondIterator.next());
        
        assertEquals("world", firstIterator.next());
        assertEquals("man", firstIterator.next());
    }

    @Test
    public void testHasNextSingleIterator() {
        DefaultStream<String> stream = new DefaultStream<>("Hello", "world", "man");
        stream.setMutable(true);
        
        MutableStreamIterator<String> iterator = stream.iterate();
        assertTrue(iterator.hasNext()); //next is Hello
        
        iterator.next();
        assertTrue(iterator.hasNext()); //next is world

        iterator.next();
        assertTrue(iterator.hasNext()); //next is man
        
        iterator.next();
        assertFalse(iterator.hasNext()); //no next
    }
    
    @Test
    public void testHasNextMultiIterator() {
        DefaultStream<String> stream = new DefaultStream<>("Hello", "world", "man");
        stream.setMutable(true);

        //part 1
                
        MutableStreamIterator<String> firstIterator = stream.iterate();
        assertTrue(firstIterator.hasNext()); //next is Hello
        firstIterator.next();
        assertTrue(firstIterator.hasNext()); //next is world

        MutableStreamIterator<String> secondIterator = stream.iterate();
        assertTrue(secondIterator.hasNext()); //next is Hello
        secondIterator.next();
        assertTrue(firstIterator.hasNext()); //next is world
        
        //part 2
        
        firstIterator.next();
        assertTrue(firstIterator.hasNext()); //next is man
        
        firstIterator.next();
        assertFalse(firstIterator.hasNext()); //no next
    }

    @Test
    public void testSkipFromZero() {
        DefaultStream<String> stream = new DefaultStream<>("Hello", "world", "man"); 
        stream.setMutable(true);      
        MutableStreamIterator<String> firstIterator = stream.iterate();
        firstIterator.skip(2);
        
        assertEquals("man", firstIterator.next());
    }

    @Test
    public void testSkipFromIndex() {
        DefaultStream<String> stream = new DefaultStream<>("Hello", "world", "man", "how are you?");
        stream.setMutable(true);
        MutableStreamIterator<String> firstIterator = stream.iterate();
        firstIterator.next();
        firstIterator.skip(2);
        
        assertEquals("how are you?", firstIterator.next());
    }
}
