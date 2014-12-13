package data_structures;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class LatinDictionaryTest {
    
    @Before
    public void setUp() throws Exception {
        
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testLatinDictionary() {
        LatinDictionary dict =new LatinDictionary();
        dict.load("Latin.txt");
        assertEquals("fame, renown, glory.", dict.getDefinition("gloria"));
        dict.delete("gloria");
        assertEquals(null, dict.getDefinition("gloria"));
        dict.insert("gloria", "fame, renown, glory.");
        assertEquals("fame, renown, glory.", dict.getDefinition("gloria"));
        assertTrue(dict.containsWord("gloria"));
        dict.insert("asdf", "fdsa");
        assertEquals("fdsa", dict.getDefinition(("asdf")));
    }

    @Test
    public void testLoad() {
       
       
        // not sure how to test yet
        //fail();
    }

    @Test
    public void testInsert() {
        
    }

    @Test
    public void testDelete() {
    }

    @Test
    public void testGetDefinition() {
    }

    @Test
    public void testContainsWord() {
    }

    @Test
    public void testGetRange() {
    }

    @Test
    public void testWords() {
    }

    @Test
    public void testDefinitions() {
    }

}
