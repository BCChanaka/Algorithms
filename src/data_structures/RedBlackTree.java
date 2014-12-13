//egg - masc0505
package data_structures;

import java.util.Iterator;
import java.util.Map.Entry;
import java.util.TreeMap;

public class RedBlackTree<K,V> implements DictionaryADT<K,V> {
    
    TreeMap<K, V> storage;
    public RedBlackTree()
    {
        storage= new TreeMap<K, V>();
     
    }

    @Override
    public boolean contains(K key) {
       return storage.containsKey(key);
    }

    @Override
    public boolean add(K key, V value) {
        storage.put(key, value);
        return true;
    }

    @Override
    public boolean delete(K key) {
        V status=storage.remove(key);
        if(status!=null)
            return true;
        else
            return false;
    }

    @Override
    public V getValue(K key) {
        return storage.get(key);
    }

    @Override
    public K getKey(V value) {
        for (Entry<K, V> entry : storage.entrySet()) {
            if (value.equals(entry.getValue())) {
                return entry.getKey();
            }
        }
        return null;
    }

    @Override
    public int size() {
        return storage.size();
    }

    @Override
    public boolean isFull() {
        return false;
    }

    @Override
    public boolean isEmpty() {
        return (this.size()<1);
    }

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public Iterator<K> keys() {
       return storage.keySet().iterator();
    }

    @Override
    public Iterator<V> values() {
        return storage.values().iterator();
    }
}


