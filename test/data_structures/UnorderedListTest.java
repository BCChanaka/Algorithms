package data_structures;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class UnorderedListTest {
    private UnorderedList<String> string_list;

    @Before
    public void setUp() throws Exception {
        string_list = new UnorderedList<String>();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testFind() {
        //        UnorderedList<String> list=new UnorderedList<String>();
        //        list.insertFirst("asdf");
        //        list.insertFirst("fdsa");
        //        list.insertLast("last");
        //        
        //        String fdsa=list.find("fdsa");
        //        assertEquals("fdsa", fdsa);

    }

    @Test
    public void testInsertFirst() {
        UnorderedList<String> list = new UnorderedList<String>();
        list.insertFirst("asdf");
        list.insertFirst("fdsa");
        list.insertLast("last");
        

        String asdf = list.removeFirst();
        assertEquals("fdsa", asdf);
        assertEquals("asdf", list.removeFirst());
    }

    @Test
    public void testInsertLast() {
        string_list.insertLast("first");
        string_list.insertLast("middle");
        string_list.insertLast("last");

        assertEquals(3, string_list.getCurrentSize());

        assertEquals("first", string_list.removeMin());
        assertEquals("middle", string_list.removeFirst());
        assertEquals("last", string_list.remove("last"));
    }

    @Test
    public void testIsEmpty() {
        UnorderedList<String> list = new UnorderedList<String>();

        assertTrue(list.isEmpty());

        list.insertFirst("first string");

        assertFalse(list.isEmpty());

        list.removeFirst();

        assertTrue(list.isEmpty());

    }

    @Test
    public void testIterator() {
        UnorderedList<String> list = new UnorderedList<String>();
        list.insertFirst("asdf");
        list.insertFirst("fdsa");
        list.insertLast("last");

    }

    @Test
    public void testPeekFirst() {
        // fail("Not yet implemented");
    }

    @Test
    public void testPeekMin() {
        UnorderedList<String> list = new UnorderedList<String>();
        assertEquals(null, list.peekFirst());
        
        assertTrue(list.isEmpty());
        assertEquals(null, list.peekMin());

        String first = "first";

        list.insertLast(first);

        assertEquals(first, list.peekMin());
    }

    @Test
    public void testRemove() {
        UnorderedList<String> list = new UnorderedList<String>();
        list.insertFirst("asdf");
        list.insertFirst("fdsa");
        list.insertLast("last");

        String fdsa = list.remove("fdsa");

        assertEquals(2, list.getCurrentSize());
        assertEquals("fdsa", fdsa);
    }

    @Test
    public void testRemoveFirst() {
        UnorderedList<String> list = new UnorderedList<String>();
        list.insertFirst("asdf");
        list.insertFirst("fdsa");
        list.insertLast("last");

        String fdsa = list.removeFirst();

        assertEquals(2, list.getCurrentSize());
        assertEquals("fdsa", fdsa);
        assertEquals("asdf", list.removeFirst());
        assertEquals("last", list.removeFirst());
        assertEquals(null, list.removeFirst());
    }

    @Test
    public void testRemoveMin() {
        // fail("Not yet implemented");
    }

}
