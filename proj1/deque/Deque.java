package deque;

import java.util.Iterator;

public interface Deque<type> {
    void addFirst(type item);
    void addLast(type item);
    boolean isEmpty();
    int size();
    void printDeque();
    type removeFirst();
    type removeLast();
    type get(int index);
    Iterator<type> iterator();
    boolean equals(Object o);
}
