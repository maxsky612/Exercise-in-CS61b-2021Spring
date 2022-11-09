package bstmap;


import jh61b.junit.In;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.TreeSet;


public class BSTMap<K extends Comparable, V> implements Map61B<K, V> {
    @Override
    public Iterator<K> iterator() {
        throw new UnsupportedOperationException();
    }

    private class BSTNode {
        private BSTNode left;
        private BSTNode right;
        private K key;
        private V value;

        BSTNode(K key, V value) {
            this.key = key;
            this.value = value;
            this.left = null;
            this.right = null;
        }
        BSTNode(K key, V value, BSTNode left, BSTNode right) {
            this.key = key;
            this.value = value;
            this.left = left;
            this.right = right;
        }
        public K getKey() {
            return key;
        }
        public V getValue() {
            return value;
        }
        public BSTNode getLeft() {
            return left;
        }
        public BSTNode getRight() {
            return right;
        }
    }
    private BSTNode map;
    private int size;

    public BSTMap() {
        size = 0;
    }
    @Override
    public void clear() {
        size = 0;
        map = null;
    }

    @Override
    public boolean containsKey(K key) {
        return containsKey(map, key);
    }

    private boolean containsKey(BSTNode x, K key) {
        if (x == null) {
            return false;
        }
        if (key.compareTo(x.key) < 0) {
            return containsKey(x.left, key);
        }
        if (key.compareTo(x.key) > 0) {
            return containsKey(x.right, key);
        }
        return true;
    }

    @Override
    public V get(K key) {
        return get(map, key);
    }


    private V get(BSTNode x, K key) {
        if (key == null) {
            return null;
        }
        if (x == null) {
            return null;
        }
        if (key.compareTo(x.key) < 0) {
            return get(x.left, key);
        }
        if (key.compareTo(x.key) > 0) {
            return get(x.right, key);
        }
        return x.value;
    }
    @Override
    public int size() {
        return size;
    }

    @Override
    public void put(K key, V value) {
        if (key == null) {
            throw new IllegalArgumentException("the input key cannot be null");
        }
        map = put(key, value, map);
    }

    public BSTNode put(K key, V value, BSTNode x) {
        if (x == null) {
            size += 1;
            return new BSTNode(key, value);
        }
        if (key.compareTo(x.key) < 0) {
            x.left = put(key, value, x.left);
        }
        else if (key.compareTo(x.key) > 0) {
            x.right = put(key, value, x.right);
        } else {
            x.value = value;
        }
        return x;
    }

    private void Inorder(BSTNode x) {
        if (x == null) {
            return;
        }
        Inorder(x.left);
        System.out.print(x.key + ": ");
        System.out.println(x.value);
        Inorder(x.right);
    }
    public void printInorder() {
        Inorder(map);
    }
    @Override
    public Set<K> keySet() {
        TreeSet<K> keys = new TreeSet<>();
        addKeys(map, keys);
        return keys;
    }

    private void addKeys(BSTNode x, Set<K> set) {
        if (x == null) {
            return;
        }
        set.add(x.key);
        addKeys(x.left, set);
        addKeys(x.right, set);
    }

    @Override
    public V remove(K key) {
        if (containsKey(key)) {
            V removeValue = get(key);
            map = remove(map, key);
            size -= 1;
            return removeValue;
        }
        return null;
    }

    @Override
    public V remove(K key, V value) {
        if (containsKey(key)) {
            V removeValue = get(key);
            if (removeValue.equals(value)) {
                map = remove(map, key);
                size -= 1;
                return removeValue;
            }
        }
        return null;
    }

    private BSTNode remove(BSTNode x, K key) {
        if (x == null) {
            return null;
        }
        if (key.compareTo(x.key) < 0) {
            x.left = remove(x.left, key);
        } else if (key.compareTo(x.key) > 0) {
            x.right = remove(x.right, key);
        }
        else {
            if (x.right == null) {
                return x.left;
            }
            BSTNode t = x;
            x = min(t.right);
            x.right = removeMin(t.right);
            x.left = t.left;
        }
        return x;
    }

    private BSTNode min(BSTNode x) {
        if (x.left == null) {
            return x;
        }
        return min(x.left);
    }
    private BSTNode removeMin(BSTNode x) {
        if (x.left == null) {
            return x.right;
        }
        x.left = removeMin(x.left);
        return x;
    }

}
