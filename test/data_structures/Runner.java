package data_structures;

import static org.junit.Assert.assertEquals;

import java.util.Iterator;

public class Runner {

    /**
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        LatinDictionary dict = new LatinDictionary();
        dict.load("Latin.txt");

        //      Iterator<String> words=dict.words();
        //      while(words.hasNext())
        //      {
        //          System.out.println(words.next());
        //      }
        
        dict.delete("vobis");

        String[] range = dict.getRange("ab", "ac");

        for (String word : range) {
            System.out.println(word);
        }

    }

}
