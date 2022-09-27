package deque;

import java.util.Iterator;

public class ArrayDeque<type> implements Deque<type>, Iterable<type> {
    private type[] items;
    private int size;
    private int Front = 3;
    private int Rear = 4;

    public ArrayDeque() {
        items = (type[]) new Object[8];
        size = 0;
    }

    private void resize(int capacity) {
        if (capacity > items.length) {
            type[] a = (type[]) new Object[capacity];
            for (int i = 0; i < size; i += 1) {
                a[i] = get(i);
            }
            Front = a.length - 1;
            Rear = size;
            items = a;
        } else {
            type[] b = (type[]) new Object[capacity];
            for (int i = 0; i < size; i += 1) {
                b[i] = get(i);
            }
            Front = b.length - 1;
            Rear = size;
            items = b;
        }
    }
    @Override
    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public type get(int i) {
        type returnItem = items[(Front + 1 + i) % items.length];
        return returnItem;
    }

    @Override
    public void addFirst(type a) {
        if (Front == Rear) {
            resize(2 * size);
        }
        items[Front] = a;
        Front = (Front - 1 + items.length) % items.length;
        size += 1;
    }

    @Override
    public void addLast(type a) {
        if (Front == Rear) {
            resize(2 * size);
        }
        items[Rear] = a;
        Rear = (Rear + 1) % items.length;
        size += 1;
    }

    @Override
    public void printDeque() {
        System.out.print("{");
        for (type i: this) {
            System.out.print(i + " ");
        }
        System.out.println("}");
    }

    @Override
    public type removeFirst() {
        type rmItem = items[(Front + 1) % items.length];
        items[(Front + 1) % items.length] = null;
        Front = (Front + 1) % items.length;
        size -= 1;
        if (size < items.length * 0.25) {
            resize(items.length / 4);
        }
        return rmItem;
    }

    @Override
    public type removeLast() {
        type rmItem = items[(Rear - 1 + items.length) % items.length];
        items[(Rear - 1 + items.length) % items.length] = null;
        Rear = (Rear - 1 + items.length) % items.length;
        size -= 1;
        if (size < items.length / 4) {
            resize(items.length / 4);
        }
        return rmItem;
    }

    public class getArrayDequeIterator implements Iterator<type> {
        private int index;

        public getArrayDequeIterator() {
            index = Front;
        }

        public boolean hasNext() {
            if (items[(index + 1) % items.length] != null) {
                return true;
            }
            return false;
        }

        public type next() {
            type returnItem = items[(index + 1) % items.length];
            index = (index + 1) % items.length;
            return returnItem;
        }
    }
    @Override
    public Iterator<type> iterator() {
        return new getArrayDequeIterator();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null) {
            return false;
        }
        if (!(o instanceof ArrayDeque)) {
            return false;
        }
        if (((java.util.ArrayDeque<type>) o).size() != size()) {
            return false;
        }
        for (int i = 0; i < size; i += 1) {
            if (get(i) != ((ArrayDeque<type>) o).get(i)) {
                return false;
            }
        }
        return true;
    }

}

