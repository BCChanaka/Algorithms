
//Alex Egg - [masc0505]
package data_structures;

import java.util.Iterator;

public class OrderedListPriorityQueue<T> implements PriorityQueue<T> {
public OrderedList<T> list;
    
    public OrderedListPriorityQueue()
    {
        list=new OrderedList<T>();
    }
    
    @Override
    public boolean insert(T object) {
        list.insert(object);
        return true;
    }

    @Override
    public T remove() {
        if (list.isEmpty()) {
            return null;
        }

        return list.removeMin();
        
    }

    @Override
    public T peek() {
       return list.peekMin(); //just returns first item in queue(head)
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean contains(T object) {
        for(T obj : this.list)
        {
            if (((Comparable<T>) object).compareTo(((T) obj)) == 0)
                return true;
        }
        return false;
    }

    @Override
    public int size() {
        return list.getCurrentSize();
    }

    @Override
    public Iterator<T> iterator() {
        return (Iterator<T>) list.iterator();
    }

    @Override
    public void clear() {
        list.clear();
    }

    @Override
    public boolean isEmpty() {
        return this.size() < 1;
    }

    @Override
    public boolean isFull() {
        // should always return false
        return false;
    }

}
