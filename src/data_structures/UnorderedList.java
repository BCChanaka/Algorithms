//Alex Egg - [masc0505]
package data_structures;

import java.util.Iterator;
import java.util.NoSuchElementException;

//generic unordered linked list implementation
public class UnorderedList<T> implements Iterable<T> {
    private Node<T> head = null;
    private Node<T> tail;
    private int currentSize;



    //insert
    public void insertFirst(T obj) {
        //first thing is make new node
        Node<T> newNode = new Node<T>(obj);
        if (isEmpty()) {
            head = tail = newNode;
        }
        else {
            newNode.next = tail;
            head = newNode;
        }

        currentSize++;

    }
    
    @SuppressWarnings("unchecked")
    public T find(T object)
    {
        for(T obj : this)
        {
            if (((Comparable<T>) object).compareTo(((T) obj)) == 0)
                return obj;
        }
        return null;
    }

    public void insertLast(T obj) {
        Node<T> newNode = new Node<T>(obj);

        if (isEmpty()) {
            head = tail = newNode;
        }
        else {
            tail.next = newNode;
            tail = newNode;
        }

        currentSize++;

    }

    public T peekMin() {
        if (isEmpty()) {
            return null;
        }
        return _peekMin().data;
    }

    public T peekFirst() {
        return null;
    }

    //delete

    public T removeMin() {

        if (isEmpty()) {
            return null;
        }

        Node<T> current = head, currentBest = head;
        Node<T> previous = null, previousBest = null;

        while (current != null) {
            if (((Comparable<T>) current.data).compareTo(currentBest.data) < 0) {
                previousBest = previous;
                currentBest = current;

            }
            previous = current;
            current = current.next;
        }

        //Node<T> currentBest = _peekMin();

        if (head == tail) {
            head = tail = null; //or call clear method
        }
        else if (currentBest == head) {
            head = head.next;
        }
        else if (currentBest == tail) {
            previousBest.next = null;
            tail = previousBest;
        }
        else {
            previousBest.next = currentBest.next;
        }

        currentSize--;

        return currentBest.data;
    }

    @SuppressWarnings("unchecked")
    private Node<T> _peekMin() {
       
        
        Node<T> current = head, currentBest = head;
        //Node<T> previous = null;//, previousBest = null;

        while (current != null) {
            if (((Comparable<T>) current.data).compareTo(currentBest.data) < 0) {
                //previousBest = previous;
                currentBest = current;

            }
            //previous = current;
            current = current.next;
        }
        return currentBest;
    }

    @SuppressWarnings("unchecked")
    public T remove(T obj) {
        //traverse list 'till we find obj
        Node<T> previous = null, current = head;

        while (current != null && ((Comparable<T>) obj).compareTo(current.data) != 0) {
            previous = current;
            current = current.next;
        }

        if (current == null)
            return null;

        if (head == tail)//there's only 1 item in list (or empty null==null)
        {
            head = tail = null;
        }
        else if (previous == null) {
            head = head.next;
        }
        else if (current == tail) {
            previous.next = null;
            tail = previous;
        }
        else // 2 or more nodes and it's in middle
        {
            previous.next = current.next;

        }

        currentSize--;
        return current.data;

    }

    public void clear() {
        head = null;
        currentSize = 0;

    }

    public T removeFirst() {
        if (isEmpty())
            return null;

        T temp = (T) head.data;

        if (head == tail) {
            //head=head.next;
            head = tail = null;
        }
        else
            head = head.next;

        currentSize--;
        return temp;

    }

    //search

    public Iterator<T> iterator() {
        return new ListIterator();

    }

    public boolean isEmpty() {
        return this.getCurrentSize() < 1;
    }

    public int getCurrentSize() {
        return currentSize;
    }

    //can use this for ordered list too
    public class ListIterator implements Iterator<T> {

        Node<T> iterPtr;

        public ListIterator() {
            iterPtr = head;
        }

        @Override
        public boolean hasNext() {
            return iterPtr != null;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            T temp = iterPtr.data;
            iterPtr = iterPtr.next;
            return temp;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();

        }

    }
    
    public class Node<T> {

        public Node(T obj) {
            this.data=obj;
        }
        public Node<T> next;
        public Node<T> previous;
        public T data;

    }
}
