package deque;

public class LinkedListDeque<type> {
    public class Node {
        type item;
        Node prev;
        Node next;

        public Node(type x, Node y, Node z){
            item = x;
            prev = y;
            next = z;
        }
    }
    private int size = 0;
    private Node sentinel;

    public LinkedListDeque(type a){
        sentinel = new Node(null, null, null);
        sentinel.next = new Node(a, sentinel, sentinel);
        sentinel.prev = sentinel.next;
        size += 1;
    }

    public LinkedListDeque(){
        sentinel = new Node(null, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
    }

    public boolean isEmpty(){
        if (sentinel.next != sentinel){
            return false;
        }
        return true;
    }

    public void addFirst(type a){
        size += 1;
        sentinel.next = new Node(a, sentinel, sentinel.next);
        sentinel.next.next.prev = sentinel.next;
    }

    public void addLast(type a){
        size += 1;
        sentinel.prev = new Node(a, sentinel.prev, sentinel);
        sentinel.prev.prev.next = sentinel.prev;
    }

    public type getFirst(){
        return sentinel.next.item;
    }

    public type getLast(){
        return sentinel.prev.item;
    }

    public int size(){
        return size;
    }

    public type removeFirst(){
        if (size > 0) {
            size -= 1;
            type a = sentinel.next.item;
            sentinel.next = sentinel.next.next;
            sentinel.next.prev = sentinel;
            return a;
        }
        return null;
    }

    public type removeLast(){
        if (size > 0) {
            size -= 1;
            type a = sentinel.prev.item;
            sentinel.prev = sentinel.prev.prev;
            sentinel.prev.next = sentinel;
            return a;
        }
        return null;
    }

    public static void main(String[] args){
        LinkedListDeque a = new LinkedListDeque();
        LinkedListDeque b = new LinkedListDeque(1);
        for (int i = 0; i < 5; i += 1){
            a.addFirst(i + 1);
        }
        for (int i = 1; i < 5; i += 1){
            b.addFirst(i + 1);
        }
        for (int i = 0; i < 3; i += 1){
            a.addLast(i + 1);
            b.addLast(i + 1);
        }
        a.removeFirst();
        a.removeLast();
        b.removeFirst();
        b.removeLast();
    }
}
