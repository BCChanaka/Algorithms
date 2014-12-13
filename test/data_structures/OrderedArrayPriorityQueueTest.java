package data_structures;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class OrderedArrayPriorityQueueTest {
    PriorityQueue<String> queue;

    @Before
    public void setUp() throws Exception {
        queue=new OrderedArrayPriorityQueue<String>();
    }

    @After
    public void tearDown() throws Exception {
    }



    @Test
    public void testInsert() {
        queue.insert("CCC");
        queue.insert("AAA");
        queue.insert("BBB");
        
        assertEquals(3, queue.size());
        
        queue.clear();
        queue.insert("AAA");
        queue.insert("AAA");
        assertEquals(2, queue.size()); // should this in fact fail???
        //can you insert items w/ same priority?
        //obvious issue here - can't insert 2 items w/ same result from comparable
        //see insert implementation - not sure where in there is the error yet
        
       
        
        //test inserting elements of same priority
        OrderedArrayPriorityQueue<Integer> q=new OrderedArrayPriorityQueue<Integer>();
        assertTrue(q.insert(1));
        assertEquals(1, q.size());
        
        assertTrue(q.insert(1));
        assertEquals(2, q.size());
    }

    @Test
    public void testRemove() {
        queue.insert("CCC");
        queue.insert("AAA");
        queue.insert("BBB");
        
        assertEquals("AAA", queue.remove());
        assertEquals("BBB", queue.remove());
        assertEquals("CCC", queue.remove());
        
        assertEquals(0, queue.size());
        
        
    }

    @Test
    public void testPeek() {
        queue.insert("BBB");
        queue.insert("AAA");
        queue.insert("CCC");
        
        assertEquals("AAA", queue.peek());
        assertEquals(3, queue.size());
    }

    @Test
    public void testContains() {
        queue.insert("BBB");
        queue.insert("AAA");
        queue.insert("CCC");
        
        assertTrue(queue.contains("AAA"));
        assertFalse(queue.contains("missing"));
    }

    @Test
    public void testSize() {
        queue.insert("BBB");
        queue.insert("AAA");
        queue.insert("CCC");
        
        assertEquals(3, queue.size());
        
        queue.remove();
        
        assertEquals(2, queue.size());
        
        queue.clear();
        
        assertEquals(0, queue.size());
        assertTrue(queue.isEmpty());
    }

    @Test
    public void testIterator() {
        
    }

    @Test
    public void testClear() {
        queue.insert("BBB");
        queue.insert("AAA");
        queue.insert("CCC");
        
        queue.clear();
        
        assertEquals(0, queue.size());
        assertTrue(queue.isEmpty());
    }

    @Test
    public void testIsEmpty() {
        assertEquals(0, queue.size());
        assertTrue(queue.isEmpty());
    }

    @Test
    public void testIsFull() {
        queue=new OrderedArrayPriorityQueue<String>(2);
        
        queue.insert("first");
        queue.insert("second");
        assertTrue(queue.isFull());
    }
    

    @Test
    public void should_have_FIFO_functionality() {
        PriorityQueue<Patient> queue=new OrderedArrayPriorityQueue<Patient>();
        queue.insert(new Patient("alex", 1));
        queue.insert(new Patient("cassee", 1));
        
        assertEquals("alex", queue.peek().getName());
        
        
    }
   

}
