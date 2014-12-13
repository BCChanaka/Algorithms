package data_structures;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class OrderedListPriorityQueueTest {
    PriorityQueue<String> queue;

    @Before
    public void setUp() throws Exception {
        queue = new OrderedListPriorityQueue<String>();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testInsert() {
        assertTrue(queue.insert("first"));
        assertTrue(queue.insert("second"));
        assertTrue(queue.insert("third"));
    }

    @Test
    public void testRemove() {
        queue.insert("CCCC");
        queue.insert("AAAA");
        queue.insert("BBBB");
        
        
        assertEquals("AAAA", queue.remove());
        assertEquals("BBBB", queue.remove());
        assertEquals("CCCC", queue.remove());
    }

    @Test
    public void testPeek() {
        queue.insert("CCCC");
        queue.insert("AAAA");
        queue.insert("BBBB");
        int len_before=3;
        
        assertEquals(len_before, queue.size());
        
        assertEquals("AAAA", queue.peek());
        
        assertEquals(len_before, queue.size());
        
    }

    @Test
    public void testContains() {
       queue.insert("alex");
       queue.insert("cassee");
       queue.insert("last");
       
       assertTrue(queue.contains("cassee"));
       assertFalse(queue.contains("missing"));
    }

    @Test
    public void testSize() {
        assertEquals(0, queue.size());
        queue.insert("CCCC");
        queue.insert("AAAA");
        queue.insert("BBBB");
        assertEquals(3, queue.size());
        queue.remove();
        queue.remove();
        queue.remove();
        assertEquals(0, queue.size());
    }

    @Test
    public void testIterator() {
        queue.insert("CCCC");
        queue.insert("AAAA");
        queue.insert("BBBB");
        
        for(String strings : queue)
        {
            assertFalse(strings==null);
        }
    }

    @Test
    public void testClear() {
        queue.insert("CCCC");
        queue.insert("AAAA");
        queue.insert("BBBB");
        
        queue.clear();
        
        assertEquals(0,queue.size());
        assertTrue(queue.isEmpty());
        
        queue.insert("asdf");
        assertEquals(1,queue.size());
    }

    @Test
    public void testIsEmpty() {
        assertTrue(queue.isEmpty());
        queue.insert("CCCC");
        assertFalse(queue.isEmpty());
        queue.remove();
        assertTrue(queue.isEmpty());
    }

    @Test
    public void testIsFull() {
        //should always return false for list
        assertFalse(queue.isFull());
    }

}
