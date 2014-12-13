//egg - masc0505
package data_structures;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class BinarySearchTree<K, V> implements DictionaryADT<K, V> {

    private int currentSize;
    private int modCounter;
    private Node<K, V> root;
    public BinarySearchTree() {
        root = null;
        currentSize = 0;
    }

    private class Node<K, V> {
        private K key;
        private V value;
        private Node<K, V> leftChild;
        private Node<K, V> rightChild;

        public Node(K k, V v) {
            key = k;
            value = v;
            leftChild = rightChild = null;

        }
    }//end Node class

    //example how to traverse tree
    public void print(Node<K, V> n) {
        if (n == null) {
            return;
        }
        print(n.leftChild);
        System.out.println(n);
        print(n.rightChild);
    }

    @Override
    public boolean contains(K key) {
        if(root==null)
            return false;
        Iterator<K> keys = this.keys();
        while (keys.hasNext()) {
            if (keys.next().equals(key)) //TODO: should this use comparable?
                return true;

        }
        return false;
    }

    @Override
    public boolean add(K key, V value) {

        if (root == null)
            root = new Node<K, V>(key, value);
        else
            insert(key, value, root, null, false);
        currentSize++;
        modCounter++;
        return true;

    }

    private void insert(K k, V v, Node<K, V> n, Node<K, V> parent, boolean wasLeft) {
        if (n == null) {
            if (wasLeft)
                parent.leftChild = new Node<K, V>(k, v);
            else
                parent.rightChild = new Node<K, V>(k, v);
        }
        else if (((Comparable<K>) k).compareTo((K) n.key) < 0)
            insert(k, v, n.leftChild, n, true);
        else
            insert(k, v, n.rightChild, n, false);
    }

    @Override
    // this is hard to implement he said, do in class...
    public boolean delete(K key) {
        if (!this.contains(key)) {
            return false;
        }
        
        Node<K, V> node = find(key, root, 0);
        Node<K,V> parent = remove(node, root);
        //System.out.println("(parent:"+parent+") -> (deletee:"+node.key);
        root=parent;
        currentSize--;
        
        return true;
    }




    //delte from subtree
    private Node<K,V> remove( Node<K,V> node_to_delete, Node<K,V> start_node )
    {
        if( start_node == null )
            return start_node;   // Item not found; do nothing
        
        // if (((Comparable<K>) key).compareTo(n.key) < 0) {
        if(((Comparable<K>)node_to_delete.key).compareTo( start_node.key ) < 0 )
            start_node.leftChild = remove( node_to_delete, start_node.leftChild );
        else if(((Comparable<K>)node_to_delete.key).compareTo( start_node.key ) > 0 )
            start_node.rightChild = remove( node_to_delete, start_node.rightChild );
        else if( start_node.leftChild != null && start_node.rightChild != null ) // Two children
        {
            start_node.key = findMin( start_node.rightChild ).key;
            start_node.rightChild = remove( start_node, start_node.rightChild );
        }
        else
            start_node = ( start_node.leftChild != null ) ? start_node.leftChild : start_node.rightChild;
        return start_node;
    }
    
    /**
     * Internal method to find the smallest item in a subtree.
     * @param t the node that roots the tree.
     * @return node containing the smallest item.
     */
    private Node<K,V> findMin( Node<K,V> t )
    {
        if( t == null )
            return null;
        else if( t.leftChild == null )
            return t;
        return findMin( t.leftChild );
    }

    @Override
    public V getValue(K key) {
        return find(key, root);
    }

    private V find(K key, Node<K, V> n) {
        if (n == null)
            return null;

        if (((Comparable<K>) key).compareTo(n.key) < 0) {
            return find(key, n.leftChild); //go left
        }
        else if (((Comparable<K>) key).compareTo(n.key) > 0) {
            return find(key, n.rightChild); //go right
        }
        else
            return (V) n.value; //found!

    }

    private Node<K, V> find(K key, Node<K, V> n, int dummy) {
        if (n == null)
            return null;

        if (((Comparable<K>) key).compareTo(n.key) < 0) {
            return find(key, n.leftChild, 0); //go left
        }
        else if (((Comparable<K>) key).compareTo(n.key) > 0) {
            return find(key, n.rightChild, 0); //go right
        }
        else
            return n; //found!

    }

    @Override
    public K getKey(V value) {

        Iterator<K> k = keys();
        Iterator<V> v = values();
        while (k.hasNext()) {
            K tempK = k.next();
            V tempV = v.next();

            if (((Comparable<V>) tempV).compareTo(value) == 0)
                return tempK;
        }

        return null;
    }

    @Override
    public int size() {
        return currentSize;
    }

    @Override
    public boolean isFull() {
        // should always return false
        return false;
    }

    @Override
    public boolean isEmpty() {
        return this.size() < 1;
    }

    @Override
    public void clear() {
        root = null;
        currentSize = 0;
    }

    @Override
    public Iterator<K> keys() {
        return new KeyIteratorHelper();
    }

    @Override
    public Iterator<V> values() {
        return new ValuesIterator();
    }

    public class KeyIteratorHelper implements Iterator<K> {
        K[] array;
        private int index;
        private int endIndex;

        public KeyIteratorHelper() {
            array = (K[]) new Object[currentSize];

            inOrderFillArray(root);
            index = 0;
            this.endIndex = array.length;//size();
        }

        private void inOrderFillArray(Node<K, V> n) {
            if (n == null)
                return;
            inOrderFillArray(n.leftChild);
            array[index++] = (K) n.key; //this cast is questionable...
            inOrderFillArray(n.rightChild);
        }

        @Override
        public boolean hasNext() {
            return (index < endIndex);
        }

        @Override
        public K next() {
            if (hasNext() == false) {
                throw new NoSuchElementException();
            }
            return (K) array[index++];
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    public class ValuesIterator implements Iterator<V> {
        V[] array;
        private int index;
        private int endIndex;

        public ValuesIterator() {
            array = (V[]) new Object[currentSize];

            inOrderFillArray(root);
            index = 0;
            this.endIndex = array.length;//size();
        }

        private void inOrderFillArray(Node<K, V> n) {
            if (n == null)
                return;
            inOrderFillArray(n.leftChild);
            array[index++] = (V) n.value; //this cast is questionable...
            inOrderFillArray(n.rightChild);
        }

        @Override
        public boolean hasNext() {
            return (index < endIndex);
        }

        @Override
        public V next() {
            if (hasNext() == false) {
                throw new NoSuchElementException();
            }
            return (V) array[index++];
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

}
