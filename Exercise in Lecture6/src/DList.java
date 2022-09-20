public class DList<type>{
    public class Node {
        type item;
        Node prev;
        Node next;

        public Node(type x, Node y, Node z) {
            item = x;
            y = prev;
            z = next;
        }
    }

    int size = 0;
    Node sentFront;
    Node sentBack;

    public DList(type a){
        size += 1;
        sentFront = new Node(null, null, null);
        sentBack = new Node(null, null, null);
        sentFront.next = new Node(a, sentFront, sentBack);
        sentBack.prev = sentFront.next;
    }

    public DList(){
        sentFront = new Node(null, null, null);
        sentBack = new Node(null, null, null);
        sentFront.next = sentBack;
        sentBack.prev = sentFront;
    }

    public type getFirst(){
        return sentFront.next.item;
    }

    public void addFirst(type a){
        size += 1;
        sentFront.next = new Node(a, sentFront, sentFront.next);
        sentFront.next.next.prev = sentFront.next;
    }

    public void addLast(type a){
        size += 1;
        sentBack.prev = new Node(a, sentBack.prev, sentBack);
        sentBack.prev.prev.next = sentBack.prev;
    }

    public type getLast(){
        return sentBack.prev.item;
    }

    public type removeFirst(){
        type a = sentFront.next.item;
        size -= 1;
        sentFront.next = sentFront.next.next;
        sentFront.next.next.prev = sentFront;
        return a;
    }

    public type removeLast(){
        type a = sentBack.prev.item;
        size -= 1;
        sentBack.prev = sentBack.prev.prev;
        sentBack.prev.prev.next = sentBack;
        return a;
    }


}

