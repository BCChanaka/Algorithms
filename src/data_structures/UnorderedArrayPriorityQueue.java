//Alex Egg - [masc0505]
package data_structures;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class UnorderedArrayPriorityQueue<T> implements PriorityQueue<T> {

    private int currentSize;
    private int maxSize;
    private T[] storage;

    public UnorderedArrayPriorityQueue() {
        this(DEFAULT_MAX_CAPACITY);
    }

    @SuppressWarnings("unchecked")
    public UnorderedArrayPriorityQueue(int size) {
        currentSize = 0;
        maxSize = size;
        storage = (T[]) new Object[maxSize];
    }

    @Override
    public boolean insert(T object) {
        if (isFull()) {
            return false;
        }
        storage[currentSize++] = object;
        return true;
    }

    //THIS METHOD IS FLAWED....
    @Override
    public T remove() {
        if (isEmpty()) //this is not good solution
        {
            return null;
        }

        int highest_priority_index = getHighestPriorityIndex();

        T highest_priority = this.getAtIndex(highest_priority_index);

        //remove from collection
        int i, j;
        for (i = j = 0; j < storage.length; ++j) {
            if (highest_priority_index != j) {
                storage[i++] = storage[j];
            }
        }
        storage = Arrays.copyOf(storage, i);

        //make collection smaller by 1
        currentSize--;

        return highest_priority;
    }

    @Override
    public T peek() {
        int hightest_priority_index = getHighestPriorityIndex();

        return getAtIndex(hightest_priority_index);

    }

    private T getAtIndex(int index) {
        if (isEmpty()) //this is not good solution
        {
            return null;
        }

        T element = storage[index];

        return element;

    }

    @SuppressWarnings("unchecked")
    private int getHighestPriorityIndex() {
        int highest_priority_index = 0;
        for (int i = 0; i < currentSize; i++) {
            T obj = storage[i];
            if (((Comparable<T>) storage[highest_priority_index]).compareTo(((T) obj)) > 0) {
                highest_priority_index = i;
            }
        }
        return highest_priority_index;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean contains(T object) {
        for (int i = 0; i < currentSize; i++)
            if (((Comparable<T>) object).compareTo(((T) storage[i])) == 0)
                return true;
        return false;
    }

    @Override
    public int size() {
        return currentSize;

    }

    @Override
    public Iterator<T> iterator() {

        return new ArrayIterator<T>();
    }

    @Override
    public void clear() {
        currentSize = 0;

    }

    @Override
    public boolean isEmpty() {
        return this.size() < 1;
    }

    @Override
    public boolean isFull() {

        return currentSize == maxSize;
    }

    public class ArrayIterator<T> implements Iterator<T> {
        public ArrayIterator() {
            this.endIndex = size();
            this.index = 0;
        }

        protected int endIndex = 0;
        /** The current iterator index */
        protected int index = 0;

        @Override
        public boolean hasNext() {
            return (index < endIndex);
        }

        @Override
        public T next() {
            if (hasNext() == false) {
                throw new NoSuchElementException();
            }
            //return Array.get(array, index++);
            return (T) storage[index++];
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();

        }

    }

}
