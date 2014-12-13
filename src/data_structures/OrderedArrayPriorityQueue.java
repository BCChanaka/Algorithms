//Alex Egg - [masc0505]

package data_structures;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class OrderedArrayPriorityQueue<T> implements PriorityQueue<T> {

    private T[] storage;
    private int currentSize;
    private int maxSize;

    public int getMaxSize() {
        return maxSize;
    }

    public OrderedArrayPriorityQueue() {
        this(DEFAULT_MAX_CAPACITY);
    }

    @SuppressWarnings("unchecked")
    public OrderedArrayPriorityQueue(int size) {
        currentSize = 0;
        maxSize = size;
        storage = (T[]) new Object[maxSize];
    }

    @SuppressWarnings("unchecked")
    public int binarySearch(T obj, int low, int high) {
        //find middle e.g. 0+array size/2
        int mid = (low + high) / 2;
        if (high < low)
            return -1; // not found
        mid = low + (high - low) / 2;
        if (((Comparable<T>) obj).compareTo(storage[mid]) == 0)
            return mid;
        else if (((Comparable<T>) obj).compareTo(storage[mid]) < 0)
            return binarySearch(obj, low, mid - 1);

        return binarySearch(obj, mid + 1, high);
    }

    @Override
    public boolean insert(T object) {
        if (isFull()) {
            return false;
        }

        int index = find_last_match_index(object);
        int insertIndex = index;
        int shift=insertIndex+1;
        if (index < 0) {
            insertIndex = -index - 1;
            shift=insertIndex+1;
        }

        T[] newSortedArray = (T[]) new Object[storage.length + 1];
        System.arraycopy(storage, 0, newSortedArray, 0, insertIndex);
        
        //puts old array at shift of new array
        System.arraycopy(storage, insertIndex, newSortedArray, shift, storage.length - insertIndex);
        newSortedArray[insertIndex] = object;
        storage = newSortedArray;

        currentSize++;

        return true;
    }

    private int find_last_match_index(T object) {
        int last_good=0;
        int index = last_good= Arrays.binarySearch(storage, 0, currentSize, object);
        
        while(index>-1 && index<currentSize)
        {
            index=Arrays.binarySearch(storage, index+1, currentSize, object);
            
            if(index>0)
                last_good=index;
            if(index<0)
                last_good=-index-1;
        }
        
        return last_good;
    }

    @Override
    public T remove() {
        if (isEmpty()) //this is not good solution
        {
            return null;
        }

        T first_element = storage[0];

        //shift all elements left by 1
        System.arraycopy(storage, 1, storage, 0, storage.length - 1);

        //make collection smaller by 1
        currentSize--;

        return first_element;
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
