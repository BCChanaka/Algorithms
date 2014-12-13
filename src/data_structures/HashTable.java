//egg - masc0505
package data_structures;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class HashTable<K, V> implements DictionaryADT<K, V> {
    //wrapper class to hold key and value to put in list
    class DictionaryNode<K, V> implements Comparable<DictionaryNode<K, V>> {
        K key;
        V value;

        public DictionaryNode(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public int compareTo(DictionaryNode<K, V> node) {
            return ((Comparable<K>) key).compareTo(node.key);
        }

    }

    public class KeyIterator implements Iterator<K> {
        private DictionaryNode<K, V>[] nodes;
        private int idx;

        public KeyIterator() {
            nodes = new DictionaryNode[currentSize];
            idx = 0;
            int j = 0;
            for (int i = 0; i < tableSize; i++)//fill in aux array
            {
                for (DictionaryNode<K, V> n : list[i]) {
                    nodes[j++] = n;
                }

                nodes = nodes; //must sort this
            }
            Sorters.quicksort(nodes);
        }

        @Override
        public boolean hasNext() {
            return idx < currentSize;
        }

        @Override
        public K next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            return nodes[idx++].key;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();

        }

    }

    private static class Sorters {

        static <T extends Comparable<? super T>> void quicksort(T[] array) {

            quicksort(array, 0, array.length - 1);

        }

        static <T extends Comparable<? super T>> void quicksort(T[] array, int left0, int right0) {
            int left = left0;
            int right = right0 + 1;
            T pivot, temp;

            pivot = array[left0];

            do {
                do
                    left++;
                while (left <= right0 && array[left].compareTo(pivot) < 0);

                do
                    right--;
                while (array[right].compareTo(pivot) > 0);

                if (left < right) {
                    temp = array[left];
                    array[left] = array[right];
                    array[right] = temp;
                }

            }
            while (left <= right);

            temp = array[left0];
            array[left0] = array[right];
            array[right] = temp;

            if (left0 < right)
                quicksort(array, left0, right);
            if (left < right0)
                quicksort(array, left, right0);

        }
    }

    public class ValueIterator implements Iterator<V> {

        private Iterator<K> k;

        public ValueIterator() {
            k = keys();

        }

        @Override
        public boolean hasNext() {
            return k.hasNext();
        }

        @Override
        public V next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return getValue(k.next());
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();

        }

    }

    private int currentSize, maxSize, tableSize;

    private UnorderedList<DictionaryNode<K, V>>[] list;

    DictionaryNode<K, V> AVAILABLE = new DictionaryNode<K, V>(null, null);

    public HashTable(int n) {
        currentSize = 0;
        maxSize = n;
        tableSize = (int) (maxSize * 13f);//table that is about %30 larger

        //chaining - if collision just add to list
        //alternative is probing heruistic
        list = new UnorderedList[tableSize];

        for (int i = 0; i < tableSize; i++) {
            list[i] = new UnorderedList<DictionaryNode<K, V>>();
        }
    }

    @Override
    public boolean add(K key, V value) {
        if (this.isFull()) {
            return false;
        }
        if (this.contains(key)) {

        }

        DictionaryNode<K, V> newNode = new DictionaryNode<K, V>(key, value);

        int index = getIndex(key);

        // list[index].clear();
        list[index].insertLast(newNode);
        currentSize++;

        return true;
    }

    @Override
    public void clear() {
        currentSize = 0;
        list = new UnorderedList[tableSize];

        for (int i = 0; i < tableSize; i++) {
            list[i] = new UnorderedList<DictionaryNode<K, V>>();
        }

    }

    @Override
    public boolean contains(K key) {
        UnorderedList<DictionaryNode<K, V>> value = find(key);

        if (value.getCurrentSize() > 0)
            return true;
        else
            return false;

    }

    @Override
    public boolean delete(K key) {
        if (this.contains(key)) {
            int index = getIndex(key);
            int children_count = list[index].getCurrentSize();
            list[index].clear();
            currentSize = currentSize - children_count;
            return true;
            //i'll keep this old code here just in case...
            //create new array
            //            UnorderedList <DictionaryNode<K,V>> [] newArray = new UnorderedList[tableSize];
            //            System.arraycopy(list, 0, newArray, 0, index);
            //            System.arraycopy(list, index+1, newArray, index, list.length - index-1);
            //            list=newArray;
            //            return true;
        }
        return false;
    }

    public UnorderedList<DictionaryNode<K, V>> find(K key) {
        int index = getIndex(key);
        UnorderedList<DictionaryNode<K, V>> value = this.list[index];
        return value;

    }

    public int getIndex(K key) {
        //non-negative
        return (key.hashCode() & 0x7FFFFFFF) % tableSize; // largest positive number in 32 bit hex
    }

    @Override
    public K getKey(V value) {
        for (int i = 0; i < list.length; i++) {

            Iterator<DictionaryNode<K, V>> list_items = list[i].iterator();
            while (list_items.hasNext()) {
                DictionaryNode<K, V> temp = list_items.next();
                if (temp != null && temp.value.equals(value))
                    return temp.key;
            }

        }
        return null;
    }

    @Override
    public V getValue(K key) {
        int index = getIndex(key);
        DictionaryNode<K, V> fake_node = new DictionaryNode<K, V>(key, null);
        DictionaryNode<K, V> temp = list[index].find(fake_node);
        if (temp == null)
            return null;

        return temp.value;

    }

    @Override
    public boolean isEmpty() {
        return this.size() < 1;
    }

    @Override
    public boolean isFull() {
        return (this.size() >= this.maxSize);
    }

    @Override
    public Iterator<K> keys() {
        return new KeyIterator();
    }

    @Override
    public int size() {
        return currentSize;
    }

    @Override
    public Iterator<V> values() {
        return new ValueIterator();
    }

}
