package data_structures;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class OrderedListTest {
    OrderedList<String> string_list;

    @Before
    public void setUp() throws Exception {
        string_list = new OrderedList<String>();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testInsert() {
        OrderedList<String> list = new OrderedList<String>();

        list.insert("asdf");
        list.insert("fdsa");

        assertEquals("asdf", list.removeMin());
        assertEquals("fdsa", list.removeMin());
    }

    @Test
    public void testRemoveFirst() {
        string_list.insert("first");
        string_list.insert("second");
        assertEquals("first", string_list.removeFirst());
    }

    @Test
    public void testRemove() {
        String second="second";
        string_list.insert("first");
        string_list.insert(second);
        string_list.insert("third");
        
        int len_before=3;
        assertEquals(len_before, string_list.getCurrentSize());
        assertEquals(second, string_list.remove(second));
        assertEquals(len_before-1, string_list.getCurrentSize());
    }

    @Test //same as removeFirst right? - since it's ordered..
    public void testRemoveMin() {
        string_list.insert("first");
        string_list.insert("second");
        assertEquals("first", string_list.removeFirst());
    }

    @Test
    public void testFind() {
//        string_list.insert("first");
//        string_list.insert("second");
//        string_list.insert("third");
//        
//        assertEquals("second", string_list.find("second"));
//        assertEquals(null, string_list.find("asdf"));
    }
    
    @Test
    public void should_clear_list()
    {
        string_list.insert("first");
        string_list.insert("second");
        string_list.insert("third");
        
        assertEquals(3, string_list.getCurrentSize());
        
        string_list.clear();
        
        assertEquals(0, string_list.getCurrentSize());
       
        string_list.insert("first");
        string_list.insert("second");
        string_list.insert("third");
        
        assertEquals(3, string_list.getCurrentSize());
        
        string_list.clear();
        
        assertEquals(0, string_list.getCurrentSize());
    }

    @Test
    public void testIterator() {
      
        string_list.insert("CCC");
        string_list.insert("BBB");
        string_list.insert("AAA");
        
        for(String string : string_list)
        {
            assertFalse(string==null);
        }
    }

    @Test
    public void testIsEmpty() {
        assertTrue(string_list.isEmpty());
    }

}
