package data_structures;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class UnorderedListPriorityQueueTest {
    PriorityQueue<String> queue;

    @Before
    public void setUp() throws Exception {
        queue = new UnorderedListPriorityQueue<String>();
        queue.insert("BBBB");
        queue.insert("AAAA");
        queue.insert("CCCC");
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void should_recreate_timer_problem() {
        PriorityQueue<Integer> queue = new UnorderedListPriorityQueue<Integer>();
        for (int i = 0; i < 10; i++) {
            // build structure first
            queue.clear();
            long start = System.currentTimeMillis(); // capture time to build  
            for (int j = 0; j < 100; j++) {
                int x = (int) (Integer.MAX_VALUE * Math.random());
                assertTrue(queue.insert(x));
            }
            long stop = System.currentTimeMillis();
         
            // time for add/remove cycles   
            start = System.currentTimeMillis();  
            for(int j=0; j < 100; j++) {
                int x = (int) (Integer.MAX_VALUE * Math.random());
                queue.insert(x);            
                queue.remove();
                }
            stop = System.currentTimeMillis();
        }
    }

    @Test
    public void testInsert() {

        PriorityQueue<String> pq = new UnorderedListPriorityQueue<String>();
        pq.insert("AAAA");
        pq.insert("BBBB");
        pq.insert("CCCC");

        assertEquals(3, pq.size());

        PriorityQueue<Integer> pq2 = new UnorderedListPriorityQueue<Integer>();
        pq2.insert(2);
        pq2.insert(1);
        pq2.insert(3);

        assertEquals(3, pq2.size());

    }

    @Test
    public void testRemove() {
        PriorityQueue<String> pq = new UnorderedListPriorityQueue<String>();
        pq.insert("CCCC");
        pq.insert("AAAA");
        pq.insert("BBBB");

        assertEquals("AAAA", pq.remove());
        assertEquals("BBBB", pq.remove());
        assertEquals("CCCC", pq.remove());

        pq.insert("ZZZZ");
        pq.insert("PPPP");
        pq.insert("AAAA");
        pq.insert("CCCC");

        assertEquals("AAAA", pq.remove());
        assertEquals("CCCC", pq.remove());
        assertEquals("PPPP", pq.remove());
        assertEquals("ZZZZ", pq.remove());

    }

    @Test
    public void testPeek() {
        int size_before = 3;
        assertEquals(size_before, queue.size());
        assertEquals("AAAA", queue.peek());
        assertEquals(size_before, queue.size());

        queue.remove();
        assertEquals(size_before - 1, queue.size());
        assertEquals("BBBB", queue.peek());
        assertEquals(size_before - 1, queue.size());
    }

    @Test
    public void testContains() {

        assertTrue(queue.contains("BBBB"));
        assertFalse(queue.contains("asdf"));
    }

    @Test
    public void testSize() {
        PriorityQueue<String> pq = new UnorderedListPriorityQueue<String>();
        assertEquals(0, pq.size());
        pq.insert("AAAA");
        pq.insert("BBBB");
        pq.insert("CCCC");

        assertEquals(3, pq.size());
        assertEquals("AAAA", pq.remove());
        assertEquals(2, pq.size());
        assertEquals("BBBB", pq.remove());
        assertEquals(1, pq.size());
        assertEquals("CCCC", pq.remove());
        assertEquals(0, pq.size());
    }

    @Test
    public void testIterator() {
        PriorityQueue<String> pq = new UnorderedListPriorityQueue<String>();
        pq.insert("AAAA");
        pq.insert("BBBB");
        pq.insert("CCCC");

        for (String string : pq) {
            assertFalse(string == null);
        }
    }

    @Test
    public void testIsEmpty() {
        PriorityQueue<String> pq = new UnorderedListPriorityQueue<String>();
        assertTrue(pq.isEmpty());
        pq.insert("AAAA");
        pq.insert("BBBB");
        pq.insert("CCCC");

        assertEquals("AAAA", pq.remove());
        assertEquals("BBBB", pq.remove());
        assertEquals("CCCC", pq.remove());
        assertTrue(pq.isEmpty());
    }

    @Test
    public void testIsFull() {
        //this doesn't make sense for linked-list
        //but interface says it must return false for lists
        assertFalse(queue.isFull());

        PriorityQueue<String> pq = new UnorderedListPriorityQueue<String>();
        assertFalse(pq.isFull());
    }

    @Test
    public void should_clear_pq() {
        queue = new UnorderedListPriorityQueue<String>();
        queue.insert("AAAA");
        queue.insert("BBBB");
        queue.insert("CCCC");

        assertEquals(3, queue.size());

        queue.clear();

        queue.insert("AAAA");
        queue.insert("BBBB");
        queue.insert("CCCC");

        assertEquals(3, queue.size());
    }

}
