package deque;

public interface Deque<type> {
    public void addFirst(type item);
    public void addLast(type item);
    default public boolean isEmpty(){
        if(size() == 0){
            return false;
        }
        return true;
    }
    public int size();
    public void printDeque();
    public type removeFirst();
    public type removeLast();
    public type get(int index);
}
