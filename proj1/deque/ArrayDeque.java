package deque;

public class ArrayDeque<type> {
    private type[] items;
    private int size;
    private int first = 0;

    public ArrayDeque(){
        items = (type[]) new Object[8];
        size = 0;
    }

    private void resize(int capacity){
        type[] a = (type[]) new Object[capacity];
        System.arraycopy(items, 0, a, 0, size);
        items = a;
    }

    public type get(int i){
        return items[items.length - i];
    }

    public void addFirst(type a){
        if (size < items.length){
            items[first] = a;
            if (first == 0){
                first = items.length - 1;
                return;
            }
            first -= 1;
        }
        else{
            resize(size * 2);
            items[first] = a;
            if (first == 0){
                first = items.length - 1;
                return;
            }
            first -= 1;
        }
        size += 1;
    }
}
