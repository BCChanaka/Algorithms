package data_structures;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class UnorderedArrayPriorityQueueTests {

    PriorityQueue<String> queue;

    @Before
    public void setUp() throws Exception {
        queue = new OrderedArrayPriorityQueue<String>();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testShouldAddToList() {

        PriorityQueue<Patient> pq = new UnorderedArrayPriorityQueue<Patient>();
        Patient cancer_patient = new Patient("Cancer Patient Bob", 5); //out of 10
        Patient healthy = new Patient("Not in queue but same ID as cancer patient", 5);

        pq.insert(cancer_patient); //didn't insert the other Patient

        assertEquals(1, pq.size());

        //should pass 'cause it's in the queue
        assertTrue(pq.contains(cancer_patient));

        //even though healthy isn't in the queue
        //this evals to true b/c there aleady
        //exists an object on the queue w/ the same
        //priority value, and contains checks by priority value.
        assertTrue(pq.contains(healthy));

    }

    @Test
    public void should_get_hightest_priority_w_peek() {
        PriorityQueue<Patient> pq = new UnorderedArrayPriorityQueue<Patient>();

        Patient cancer_patient = new Patient("Cancer Patient Bob", 1);
        Patient healthy = new Patient("Sore throat patient Alex", 10);
        Patient fracture = new Patient("Fractured bone Wes", 5);

        pq.insert(cancer_patient);
        pq.insert(healthy);
        pq.insert(fracture);

        assertEquals(3, pq.size());

        Patient highest_priority = pq.peek();

        assertEquals(cancer_patient.getName(), highest_priority.getName());

        assertEquals("Ensure the size didn't change since we just peeked", 3, pq.size());
    }

    @Test
    public void should_remove() {
        PriorityQueue<Patient> pq = new UnorderedArrayPriorityQueue<Patient>();

        Patient cancer_patient = new Patient("Cancer Patient Bob", 1);
        Patient healthy = new Patient("Sore throat patient Alex", 10);
        Patient fracture = new Patient("Fractured bone Wes", 5);

        pq.insert(cancer_patient);
        pq.insert(healthy);
        pq.insert(fracture);

        int size_before = 3;
        assertEquals(size_before, pq.size());

        Patient highest_priority = pq.remove();

        assertEquals(cancer_patient.getName(), highest_priority.getName());

        assertEquals("Queue should shrink by 1", size_before - 1, pq.size());

    }

    @Test
    public void should_create_empty_queue() {
        PriorityQueue<Patient> pq = new UnorderedArrayPriorityQueue<Patient>();
        assertEquals(0, pq.size());
        assertTrue(pq.isEmpty());

        //add something
        pq.insert(new Patient("asf", 1));

        assertEquals(1, pq.size());
        assertFalse(pq.isEmpty());

        //remove the thing

        pq.remove();

        assertEquals(0, pq.size());
        assertTrue(pq.isEmpty());
    }

    @Test
    public void should_output_string_in_order() {
        PriorityQueue<String> pq = new UnorderedArrayPriorityQueue<String>();

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
    public void should_loop_with_itterator() {
        PriorityQueue<Patient> pq = new UnorderedArrayPriorityQueue<Patient>();

        pq.insert(new Patient("A", 5));
        pq.insert(new Patient("B", 7));
        pq.insert(new Patient("C", 2));
        pq.insert(new Patient("D", 10));
        pq.insert(new Patient("E", 5));
        pq.insert(new Patient("F", 1));
        pq.insert(new Patient("G", 5));
        pq.insert(new Patient("H", 1));

        //order doesn't matter for iterator
        for (Patient patient : pq) {

        }

    }

    @Test
    public void should_max_fill_queue() {
        int size = 4;
        PriorityQueue<Patient> pq = new UnorderedArrayPriorityQueue<Patient>(size);
        for (int i = 0; i < size; i++) {
            pq.insert(new Patient("Patient " + size, Math.round(10)));
        }

        assertTrue("queue should be full", pq.isFull());

        //try to exceed the capacity
        assertFalse("full queue should reject insert", pq.insert(new Patient("asf", 5)));
    }

    @Test
    public void should_clear_queue() {
        PriorityQueue<Patient> pq = new UnorderedArrayPriorityQueue<Patient>();

        pq.insert(new Patient("A", 5));
        pq.insert(new Patient("B", 7));

        pq.clear();

        for (Patient p : pq) {
            Patient patient = p;
            fail("should not iterate empty queue");
        }

    }

    @Test
    public void should_test_ints() {
        int size = 100;
        PriorityQueue<Integer> pq = new UnorderedArrayPriorityQueue<Integer>(size);

        int[] array = new int[size];
        for (int i = 0; i < size; i++)
            array[i] = i + 1;
        // now scramble array order
        for (int i = 0; i < size; i++) {
            int idx = (int) (size * Math.random());
            int tmp = array[i];
            array[i] = array[idx];
            array[idx] = tmp;
        }

        for (int i = 0; i < size; i++)
            pq.insert(array[i]);

        for (int i = 0; i < size; i++)
            assertEquals("out of order removal", (i + 1), (int) pq.remove());

        assertEquals("removal from empty pq did not return null", null, pq.remove());
        assertTrue("isEmpty reports false, but pq is empty", pq.isEmpty());

    }

    //this is a bad test - but I just copied it from his website so it needs to pass
    @Test
    public void should_act_as_fifo_stack() {
        int SIZE = 100;
        PriorityQueue<PrioritizedItem> pq2 = new UnorderedArrayPriorityQueue<PrioritizedItem>(SIZE);
        int size = 10;
        pq2.clear();
        int sequenceNumber = 0;
        int midPoint = size >> 1;

        for (int i = 0; i < midPoint; i++)
            pq2.insert(new PrioritizedItem(2, sequenceNumber++));
        for (int i = midPoint; i < size; i++)
            pq2.insert(new PrioritizedItem(1, sequenceNumber++));

        PrioritizedItem item = pq2.peek();

        assertFalse("peek returns wrong element", item.getPriority() != 1);
        assertFalse("peek returns wrong element", item.getSequenceNumber() != 5);

        sequenceNumber = midPoint;
        for (int i = 0; i < midPoint; i++) {
            PrioritizedItem tmp = pq2.remove();
            assertFalse("out of order removal", tmp.getPriority() != 1);

            assertFalse("out of order removal", tmp.getSequenceNumber() != (sequenceNumber++));

        }

        sequenceNumber = 0;
        for (int i = midPoint; i < size; i++) {
            PrioritizedItem tmp = pq2.remove();
            assertFalse("out of order removal", tmp.getPriority() != 2);
            assertFalse("out of order removal", tmp.getSequenceNumber() != (sequenceNumber++));

        }

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
        UnorderedArrayPriorityQueue<Integer> q = new UnorderedArrayPriorityQueue<Integer>();
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
        queue = new UnorderedArrayPriorityQueue<String>(2);

        queue.insert("first");
        queue.insert("second");
        assertTrue(queue.isFull());
    }


    @Test
    public void test4() {
        int SIZE = 100;
        PriorityQueue<PrioritizedItem> pq2 = new UnorderedArrayPriorityQueue<PrioritizedItem>(SIZE);
        int size = 10;
        pq2.clear();
        int sequenceNumber = 0;
        int midPoint = size >> 1;

        for (int i = 0; i < midPoint; i++)
            pq2.insert(new PrioritizedItem(2, sequenceNumber++));
        for (int i = midPoint; i < size; i++)
            pq2.insert(new PrioritizedItem(1, sequenceNumber++));

        pq2.clear();
        sequenceNumber = 0;
        // System.out.println("\nNow checking iterators, output is below.");
        //System.out.println("NOTE: No specific order is required for these iterators.");
        for (int i = 0; i < 5; i++)
            pq2.insert(new PrioritizedItem(10, sequenceNumber++));
        for (int i = 0; i < 5; i++)
            pq2.insert(new PrioritizedItem(1, sequenceNumber++));
        for (int i = 0; i < 5; i++)
            pq2.insert(new PrioritizedItem(5, sequenceNumber++));

        for (PrioritizedItem item : pq2) {
            //System.out.println(item);
        }

        //System.out.println("\nNow removing items, they should be in proper order.");
        while (!pq2.isEmpty()) {
            //System.out.println(pq2.remove());
            pq2.remove();
        }

        assertEquals(0, pq2.size());
        assertFalse("size is wrong.", pq2.size() != 0);

    }
    @Test
    public void should_have_FIFO_functionality() {
        PriorityQueue<Patient> queue=new UnorderedArrayPriorityQueue<Patient>();
        queue.insert(new Patient("alex", 1));
        queue.insert(new Patient("cassee", 1));
        
        assertEquals("alex", queue.peek().getName());
        
        
    }
}
