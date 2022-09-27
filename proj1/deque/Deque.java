package deque;

import java.util.Iterator;

public interface Deque<type> {
    void addFirst(type item);
    void addLast(type item);
    default boolean isEmpty() {
        if (size() == 0) {
            return true;
        }
        return false;
    }
    int size();
    void printDeque();
    type removeFirst();
    type removeLast();
    type get(int index);
}
