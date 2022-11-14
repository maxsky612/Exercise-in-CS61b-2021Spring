package hashmap;

import org.checkerframework.checker.units.qual.C;

import java.util.*;

/**
 *  A hash table-backed Map implementation. Provides amortized constant time
 *  access to elements via get(), remove(), and put() in the best case.
 *
 *  Assumes null keys will never be inserted, and does not resize down upon remove().
 *  @author Hao Wang
 */
public class MyHashMap<K, V> implements Map61B<K, V> {

    @Override
    public void clear() {
        buckets = createTable(DEFAULT_INITIAL_SIZE);
        size = 0;
    }

    @Override
    public boolean containsKey(K key) {
        if (get(key) != null) {
            return true;
        }
        return false;
    }

    @Override
    public V get(K key) {
        Collection<Node> searchBucket = buckets[Math.floorMod(key.hashCode(), buckets.length)];
        for(Node a: searchBucket) {
            if (a.key.equals(key)) {
                return a.value;
            }
        }
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void put(K key, V value) {
        if (containsKey(key)) {
            getNode(key).value = value;
            return;
        }
        if (!isOverflow(size + 1)) {
            Node insertNode = createNode(key, value);
            int hash = key.hashCode();
            buckets[Math.floorMod(hash, buckets.length)].add(insertNode);
            size += 1;
        } else {
            resize(2 * arraySize);
            Node insertNode = createNode(key, value);
            int hash = key.hashCode();
            buckets[Math.floorMod(hash, buckets.length)].add(insertNode);
            size += 1;
        }

    }

    private Node getNode(K key) {
        int hash = key.hashCode();
        int index = Math.floorMod(hash, buckets.length);
        for(Node a: buckets[index]) {
            if (a.key.equals(key)) {
                return a;
            }
        }
        return null;
    }

    @Override
    public Set<K> keySet() {
        Set<K> keys = new TreeSet<>();
        for(K key: this) {
            keys.add(key);
        }
        return keys;
    }

    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<K> iterator() {
        return new mapIterator();
    }


    private boolean isOverflow(int a) {
        if (a / arraySize >= loadFactor) {
            return true;
        }
        return false;
    }

    private void resize(int reSize) {
       Collection<Node>[] temp = createTable(reSize);
       for (int i = 0; i < buckets.length; i ++) {
           for (Node node: buckets[i]) {
               int index = Math.floorMod(node.key.hashCode(), reSize);
               temp[index].add(node);
           }
       }
       buckets = temp;
    }
    /**
     * Protected helper class to store key/value pairs
     * The protected qualifier allows subclass access
     */
    protected class Node {
        K key;
        V value;

        Node(K k, V v) {
            key = k;
            value = v;
        }
    }

    /* Instance Variables */
    private Collection<Node>[] buckets;
    private static final int DEFAULT_INITIAL_SIZE = 16;
    private static final double DEFAULT_MAX_LOAD_FACTOR = 0.75;
    private double loadFactor;
    private int size;
    private int arraySize;
    /** Constructors */
    public MyHashMap() {
        this(DEFAULT_INITIAL_SIZE, DEFAULT_MAX_LOAD_FACTOR);
    }

    public MyHashMap(int initialSize) {
        this(initialSize, DEFAULT_MAX_LOAD_FACTOR);
    }

    /**
     * MyHashMap constructor that creates a backing array of initialSize.
     * The load factor (# items / # buckets) should always be <= loadFactor
     *
     * @param initialSize initial size of backing array
     * @param maxLoad maximum load factor
     */
    public MyHashMap(int initialSize, double maxLoad) {
        buckets = createTable(initialSize);
        loadFactor = maxLoad;
        arraySize = initialSize;
        size = 0;
    }

    /**
     * Returns a new node to be placed in a hash table bucket
     */
    private Node createNode(K key, V value) {
        return new Node(key, value);
    }

    /**
     * Returns a data structure to be a hash table bucket
     *
     * The only requirements of a hash table bucket are that we can:
     *  1. Insert items (`add` method)
     *  2. Remove items (`remove` method)
     *  3. Iterate through items (`iterator` method)
     *
     * Each of these methods is supported by java.util.Collection,
     * Most data structures in Java inherit from Collection, so we
     * can use almost any data structure as our buckets.
     *
     * Override this method to use different data structures as
     * the underlying bucket type
     *
     * BE SURE TO CALL THIS FACTORY METHOD INSTEAD OF CREATING YOUR
     * OWN BUCKET DATA STRUCTURES WITH THE NEW OPERATOR!
     */
    protected Collection<Node> createBucket() {
        return new LinkedList<>();
    }

    /**
     * Returns a table to back our hash table. As per the comment
     * above, this table can be an array of Collection objects
     *
     * BE SURE TO CALL THIS FACTORY METHOD WHEN CREATING A TABLE SO
     * THAT ALL BUCKET TYPES ARE OF JAVA.UTIL.COLLECTION
     *
     * @param tableSize the size of the table to create
     */
    protected Collection<Node>[] createTable(int tableSize) {
        Collection<Node>[] buckets = new Collection[tableSize];
        for (int i = 0; i < buckets.length; i += 1) {
            buckets[i] = createBucket();
        }
        return buckets;
    }

    private class mapIterator implements Iterator<K> {
        Queue<K> queue;
        public mapIterator() {
            queue = new LinkedList<>();
            for (Collection<Node> items : buckets) {
                for (Node node : items) {
                    queue.add(node.key);
                }
            }
        }

        @Override
        public K next() {
            return queue.poll();
        }

        @Override
        public boolean hasNext() {
            return !queue.isEmpty();
        }
    }
}
