package deque;

public class main {
    public static void main(String[] args) {
        ArrayDeque a1 = new ArrayDeque<String>();
        a1.addFirst("Father");
        a1.addFirst("Mother");
        a1.addFirst("Sister");
        a1.addLast("Brother");
        a1.addLast("Uncle");
        a1.addLast("Aunt");
        a1.addFirst("A");
        a1.addFirst("B");
        a1.addFirst("C");
        a1.addFirst("D");
        a1.addFirst("E");
        a1.addFirst("F");
        a1.addFirst("G");
        a1.addLast("H");
        a1.addLast("I");
        a1.addLast("J");
        a1.addLast("K");
        a1.get(0);
        a1.get(2);
        a1.get(12);
        a1.printDeque();
        a1.removeFirst();
        a1.removeFirst();
        a1.removeLast();
        a1.removeLast();

    }
}
