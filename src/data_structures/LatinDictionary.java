//egg - masc0505

package data_structures;


import java.util.Iterator;

public class LatinDictionary {
    private DictionaryADT<String, String> dictionary;
    DictionaryEntry[] entries;

    // constructor takes no arguments.  Size depends on the datafile.
    // creates an instance of the DictionaryADT. Use your HashTable 
    // implementation in this class (though all four should work).
    // Methods that make modifications to the dictionary modify the
    // DictionaryADT object, not the datafile.
    public LatinDictionary() {
        //initialize dictionary
       // load("Latin.txt");
       
        
    }

    // reads the key=value pairs from the datafile and builds a dictionary structure
    public void load(String fileName) {
        entries = DictionaryReader.getDictionaryArray(fileName);
        dictionary = new HashTable<String, String>(entries.length + 50);
        for (DictionaryEntry entry : entries) {
            dictionary.add(entry.getKey(), entry.getValue());
        }
    }

    // inserts a new word and its definition
    public boolean insert(String key, String value) {
        return dictionary.add(key, value);
    }

    // removes the key and value that is identified by the key from the dictionary
    public boolean delete(String key) {
        return dictionary.delete(key);
    }

    // looks up the definition of the latinWord key
    public String getDefinition(String latinWord) {
        return dictionary.getValue(latinWord);
    }

    // returns true if the latinWord is already in the dictionary
    public boolean containsWord(String latinWord) {
        return dictionary.contains(latinWord);
    }

    // returns all of the keys in the dictionary within the range start .. finish
    // inclusive, in sorted order. Neither value 'start' or 'finish' need be in the
    // dictionary.  Returns null if there are no keys in the range specified.    
    public String[] getRange(String start, String finish) {
        
        UnorderedList<String> list=new UnorderedList<String>();
        Iterator<String> keys=this.words();
        boolean started=false;
        boolean finished=false;
        
        while(keys.hasNext() && finished==false)
        {
            String next=(String) keys.next();
            if(next.matches("(?i)"+start+"*"))
                started=true;
            if(next.matches("(?i)"+finish+".*"))
                break;
           
            if(started)
                list.insertLast(next);
        }
        
        //copy to array
        String [] array = new String [list.getCurrentSize()];
        int i=0;
        for(String entry: list)
        {
            array[i]=entry;
            i++;
        }
        
        return array;
        
        
        
    }

    // returns an Iterator of the latin words (the keys) in the dictionary,
    // in sorted order.
    public Iterator<String> words() {
        return dictionary.keys();
    }

    // returns the definitions in the dictionary, in exactly the same order
    // as the words() Iterator
    public Iterator<String> definitions() {
        return dictionary.values();
    }
}
