//Alex Egg - [masc0505]
package data_structures;

import java.util.Iterator;

public class UnorderedListPriorityQueue<T> implements PriorityQueue<T> {
    public UnorderedList<T> list;
    
    public UnorderedListPriorityQueue()
    {
        list=new UnorderedList<T>();
    }

    @Override
    public boolean insert(T obj) {
        list.insertLast(obj);
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
       return list.peekMin();
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean contains(T object) {
       if(list.find(object)!=null)
           return true;
       else
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
        return false;
    }

}
