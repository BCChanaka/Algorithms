/*  The PriorityQueue ADT may store objects in any order.  However,
    removal of objects from the PQ must follow specific criteria.
    The object of highest priority that has been in the PQ longest
    must be the object returned by the remove() method.  FIFO return
    order must be preserved for objects of identical priority.
   
    Ranking of objects by priority is determined by the Comparable<E>
    interface.  All objects inserted into the PQ must implement this
    interface.
*/   

package data_structures;

import java.util.Iterator;

public interface PriorityQueue<E> extends Iterable<E> {
    public static final int DEFAULT_MAX_CAPACITY = 1000;

    //  Inserts a new object into the priority queue.  Returns true if
    //  the insertion is successful.  If the PQ is full, the insertion
    //  is aborted, and the method returns false.
    public boolean insert(E object);   
   
    //  Removes the object of highest priority that has been in the
    //  PQ the longest, and returns it.  Returns null if the PQ is empty.
    public E remove();
   
    //  Returns the object of highest priority that has been in the
    //  PQ the longest, but does NOT remove it. 
    //  Returns null if the PQ is empty.
    public E peek();  
    
    //  Returns true if an item that matches the parameter E object
    //  is in the PQ, false otherwise.  This method uses the Comparable<E> 
    //  interface, and does not use the equals() method.
    public boolean contains(E object); 
   
    //  Returns the number of objects currently in the PQ.
    public int size();
   
    //  Returns an iterator of the objects in the PQ, in no particular
    //  order.
    public Iterator<E> iterator();
   
    //  Returns the PQ to an empty state.
    public void clear();
   
    //  Returns true if the PQ is empty, otherwise false
    public boolean isEmpty();
   
    //  Returns true if the PQ is full, otherwise false.  List based
    //  implementations should always return false.
    public boolean isFull();              
} 