package deque;

import java.util.Iterator;

public class LinkedListDeque<type> implements Iterable<type>, Deque<type>{
    public class Node {
        type item;
        Node prev;
        Node next;

        public Node(type x, Node y, Node z) {
            item = x;
            prev = y;
            next = z;
        }
    }
    private int size = 0;
    private Node sentinel;

    public LinkedListDeque(type a) {
        sentinel = new Node(null, null, null);
        sentinel.next = new Node(a, sentinel, sentinel);
        sentinel.prev = sentinel.next;
        size += 1;
    }

    public LinkedListDeque() {
        sentinel = new Node(null, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
    }

    public boolean isEmpty() {
        if (sentinel.next != sentinel) {
            return false;
        }
        return true;
    }

    public void addFirst(type a) {
        size += 1;
        sentinel.next = new Node(a, sentinel, sentinel.next);
        sentinel.next.next.prev = sentinel.next;
    }

    public void addLast(type a) {
        size += 1;
        sentinel.prev = new Node(a, sentinel.prev, sentinel);
        sentinel.prev.prev.next = sentinel.prev;
    }

    public type getFirst() {
        return sentinel.next.item;
    }

    public type getLast() {
        return sentinel.prev.item;
    }

    public int size() {
        return size;
    }

    public type removeFirst() {
        if (size > 0) {
            size -= 1;
            type a = sentinel.next.item;
            sentinel.next = sentinel.next.next;
            sentinel.next.prev = sentinel;
            return a;
        }
        return null;
    }

    public type removeLast() {
        if (size > 0) {
            size -= 1;
            type a = sentinel.prev.item;
            sentinel.prev = sentinel.prev.prev;
            sentinel.prev.next = sentinel;
            return a;
        }
        return null;
    }

    private class LLDequeIterator implements Iterator<type> {
        private Node wizPos;

        public LLDequeIterator() {
            wizPos = sentinel.next;
        }

        public boolean hasNext() {
            if (wizPos != sentinel) {
                return true;
            }
            return false;
        }

        public type next() {
            type returnItem = wizPos.item;
            wizPos = wizPos.next;
            return returnItem;
        }
    }

    public Iterator<type> iterator() {
        return new LLDequeIterator();
    }

    public type get(int i) {
        type returnItem = null;
        int index = 0;
        for (type j: this) {
            returnItem = j;
            index += 1;
            if (index == i + 1) {
                break;
            }
        }
        return returnItem;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null) {
            return false;
        }
        if (!(o instanceof LinkedListDeque)) {
            return false;
        }
        if (((LinkedListDeque<type>) o).size() != size()) {
            return false;
        }
        for (int i = 0; i < size; i += 1) {
            if (get(i) != ((LinkedListDeque<type>) o).get(i)) {
                return false;
            }
        }
        return true;
    }

    public void printDeque() {
        System.out.print("{");
        for (int i = 0; i < size - 1; i += 1) {
            System.out.print(get(i) + " ");
        }
        System.out.println(get(size - 1) + "}");
    }
}
