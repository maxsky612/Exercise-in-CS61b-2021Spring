package deque;

import java.util.Collection;
//import java.util.Deque;
import java.util.Iterator;


public class LinkedListDeque<T> implements Deque<T>, Iterable<T> {
    class DLList{
    DLList prev;
    T item;
    DLList next;
    public DLList(DLList prev,T item,DLList next) {
        this.item = item;
        this.next = next;
        this.prev = prev;
    }
    }
    DLList sentinel ;
   // DLList<T> last;
    int size = 0;

    public LinkedListDeque(){
        sentinel = new DLList(null,null,null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
    }
    public void addFirst(T item){
        //sentinel = new DLList<>(null,null,null);
        sentinel.next = new DLList(sentinel, item,sentinel.next);
        sentinel.next.next.prev = sentinel.next;
        size ++;
    }
    public void addLast(T item){
        sentinel.prev = new DLList(sentinel.prev,item,sentinel);
        sentinel.prev.prev.next = sentinel.prev;
        size ++;
    }
    /*
    public boolean isEmpty(){
        //if(sentinel.next == sentinel.prev)
        if(sentinel.next == sentinel)
            return true;
        return false;
    }
     */
    public int size(){
        return size;
    }


    public T removeFirst(){
        if(sentinel.next == sentinel){
            return null;
        }
        T p = sentinel.next.item;
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;
        size--;
        return p;

    }
    public T removeLast(){
        if(sentinel.next == sentinel){
            return null;
        }
        T t = sentinel.prev.item;
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.next = sentinel;
        size--;
        return t;
    }
    public T getFirst() {
        if(sentinel.next != sentinel)
            return sentinel.next.item;
        return null;
    }

    public T getLast() {
        if(sentinel.prev != sentinel)
            return sentinel.prev.item;
        return null;
    }

    public T get(int index){
        int a = 0;
        Iterator<T> t = new Iter();
        while(t.hasNext()){
            if(a == index){
                T m = t.next();
                return m;
            }
            t.next();
            a++;
        }
        return null;
    }
    private class Iter implements Iterator<T>{
        DLList head ;
        public Iter(){
            head = sentinel;
        }
        @Override
        public boolean hasNext() {
            if(head.next!= sentinel){
                //head = head.next;
                return true;
            }
            return false;
        }

        @Override
        public T next() {
            T t = head.next.item;
            head = head.next;
            return t;
        }
    }
    public Iterator<T> iterator(){
        return new Iter();
    }

    public  boolean equals(Object o){
    if((o instanceof Deque)&&(((LinkedListDeque<T>)o).size() == size())){
           for(int i = 0; i< size();i ++){
              if( this.get(i)!=((LinkedListDeque<T>) o).get(i))
                  return false;
           }
           return true;
    }
        return false;
    }
       public void printDeque(){
        for(int i = 0 ;i<size();i++){
            System.out.print(get(i)+ " ");
        }
           System.out.println();
    }
    //迭代 hasnext and next

    public T getRecursive(int index){
        return null;
    }
}
