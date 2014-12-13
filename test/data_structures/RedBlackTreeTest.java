package data_structures;

import static org.junit.Assert.*;

import java.awt.List;
import java.util.Iterator;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class RedBlackTreeTest {
    RedBlackTree<String,String> ht;

    @Before
    public void setUp() throws Exception {
        ht = new RedBlackTree<String, String>();
    }

    @After
    public void tearDown() throws Exception {
    }

   

    @Test
    public void testGetIndex() {
        //assertEquals(54, ht.getIndex("asdf"));
    }

    @Test
    public void testContains() {
        assertFalse(ht.contains("a"));

        ht.add("email", "eggie5@gmail.com");
        assertTrue(ht.contains("email"));

        ht.delete("email");
        assertFalse(ht.contains("email"));

    }

    @Test
    public void testAdd() {

        // Returns true if addition succeeded.
        assertTrue(ht.add("asdf", "fdsa"));
        assertEquals(1, ht.size());

        // returns false if the key is a duplicate
        //assertFalse("can't add dup. key", ht.add("asdf", "INVALID"));

        //Returns false if the dictionary is full
        RedBlackTree<String, String> _ht = new RedBlackTree<String, String>();
        assertTrue(_ht.add("alex", "24"));
        assertTrue(_ht.add("cassee", "23"));
        assertTrue(_ht.add("wes", "18"));       
      assertTrue(_ht.add("leon", "29"));
        
        assertEquals(4, _ht.size());
        
        
        
    }
    @Test
    public void test_should_add_multiptle_values_to_key()
    {
        RedBlackTree<String, Integer> _ht = new RedBlackTree<String, Integer>();
        _ht.add("nicknames", 1);
        _ht.add("nicknames", 2);
        _ht.add("nicknames", 3);
        assertEquals("nicknames", _ht.getKey(3));
        assertEquals("nicknames", _ht.getKey(1));
        assertEquals("nicknames", _ht.getKey(1));
        assertEquals("nicknames", _ht.getKey(2));
        assertEquals("nicknames", _ht.getKey(2));
        assertEquals("nicknames", _ht.getKey(3));
        assertEquals("nicknames", _ht.getKey(2));
        assertEquals(null, _ht.getKey(4));
    }
    
    @Test
    public void should_simulate_dictionary_tester()
    {

        RedBlackTree<String, Integer> _ht = new RedBlackTree<String, Integer>();
        _ht.add("JAA000", new Integer(1));
        _ht.add("IAA000", new Integer(2));
        _ht.add("HAA000", new Integer(3));
        _ht.add("BAA000", new Integer(4));
        _ht.add("EAA000", new Integer(5));
        _ht.add("DAA000", new Integer(6));
        _ht.add("CAA000", new Integer(7));
        _ht.add("AAA000", new Integer(8));
        _ht.add("GAA000", new Integer(9));
        
        assertEquals("JAA000", _ht.getKey(new Integer(1)));
        assertEquals("IAA000", _ht.getKey(2));
        assertEquals("HAA000", _ht.getKey(3));
        assertEquals("BAA000", _ht.getKey(4));
        assertEquals("EAA000", _ht.getKey(5));
    }

    @Test
    public void testDelete() {
        ht.add("name", "alex");
        assertEquals(1, ht.size());
        ht.add("job", "engineer");
        assertEquals(2, ht.size());

        assertTrue(ht.delete("job"));
        assertEquals(1, ht.size());
        assertFalse(ht.contains("job"));
        assertTrue(ht.contains("name"));
        
        
        assertFalse("shouldn't delete non-existant key", ht.delete("FAKE"));
        assertEquals(1, ht.size());
        assertTrue(ht.delete("name"));
        assertEquals("empty list should be 0 size", 0, ht.size());
        assertTrue(ht.isEmpty());
        ht.add("asdf", "fdsa");
        ht.delete("asdf"); 
        assertTrue(ht.isEmpty());
    }

    @Test
    public void testGetValue() {
      //return null if the dictionary is empty
        assertEquals(null,ht.getValue(("FAKEKEY")));
        
     // Returns the value associated with the parameter key.
     
        ht.add("asdf", "fdsa");

        assertEquals("fdsa", ht.getValue("asdf"));

        ht.add("name", "alex");
        assertEquals("alex", ht.getValue("name"));
        
     //return  null if the key is not found .
        assertEquals(null, ht.getValue("FAKE_KEY"));
        
        //should allow multiple values under 1 key
        assertTrue(ht.add("bucket", "first"));
        assertTrue(ht.add("bucket", "second"));
        
        
    }

    @Test
    public void testGetKey() {
     // Returns the key associated with the parameter value.  Returns
     // null if the value is not found in the dictionary.  If more 
     // than one key exists that matches the given value, returns the
     // first one found. 
        
        assertEquals(null,ht.getKey("FAKE"));
        
        ht.add("alex", "egg");
        assertEquals("alex", ht.getKey("egg"));
        
        ht.add("cassee", "egg");
        assertEquals("alex", ht.getKey("egg"));
        
        DictionaryADT<String,Integer> dictionary = new RedBlackTree<String, Integer>();
        dictionary.add("age", 24);
        dictionary.add("age", 25);
        assertEquals("age", dictionary.getKey(24));
        
        assertEquals(null,ht.getKey("FAKE"));

    }

    @Test
    public void testSize() {
     // Returns the number of key/value pairs currently stored 
     // in the dictionary
        assertEquals(0, ht.size());
        ht.add("key", "value");
        assertEquals(1, ht.size());
        ht.add("name", "age");
        assertEquals(2,ht.size());
        
        ht.add("age", "twenty");
        ht.add("job", "engineer");
        ht.add("car", "honda");
        
        assertEquals(5, ht.size());
        
        for(int i=0;i<1000;i++)
        {
            ht.add("key_"+i, "value_"+i);
        }
        
        assertEquals(1005, ht.size());
    }


    @Test
    public void testIsEmpty() {
        assertTrue(ht.isEmpty());
        ht.add("age", "23");
        assertFalse(ht.isEmpty());
        ht.clear();
        assertTrue(ht.isEmpty());
    }

    @Test
    public void testClear() {
        ht.add("city", "san diego");
        ht.clear();
        assertTrue(ht.isEmpty());
        assertEquals(0, ht.size());
        assertEquals(null,ht.getValue("city"));
    }

    @Test
    public void testKeys() {
        ht.add("city", "barstow");
        ht.add("state", "california");
        
        Iterator<String> keysi=ht.keys();
        
        assertTrue(keysi.hasNext());
        
        assertEquals("city", keysi.next());
        assertEquals("state", keysi.next()); // they must be sorted
        
    }


    
    @Test
    public void testValues() {
        ht.add("city", "barstow");
        ht.add("state", "california");
        
        Iterator<String> valuesi=ht.values();
        assertTrue(valuesi.hasNext());
        
        assertEquals("barstow", valuesi.next());
    }

}
