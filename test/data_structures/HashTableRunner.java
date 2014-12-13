package data_structures;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Iterator;

public class HashTableRunner {

    /**
     * @param args
     */
    public static void main(String[] args) {
        DictionaryADT<String, String> dictionary = new HashTable<String, String>(10);
        dictionary.add("key1", "value1");
        dictionary.add("key1", "value2");
        
        String key = dictionary.getKey("FAKE_KEY");
        
        System.out.println(key);
        

    }

}
