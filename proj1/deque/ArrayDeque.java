package deque;

import java.util.Iterator;

public class ArrayDeque<type>implements Deque<type> ,Iterable<type> {
   type [] a ;
   int size;
   int f = 0;
   int l = 0;
   public ArrayDeque(){
        //a = (type[]) new Object[8];
      a = (type[]) new java.lang.Object[8];
       size = 0;
   }
   public ArrayDeque(int cap){
       a = (type[]) new java.lang.Object[cap];
       size = 0;
   }
   public void resize(int capacity){//中间扩张 中间收缩
       int newlength = capacity;
        type[] b = (type[]) new java.lang.Object[newlength];
        if(l == f && l == size){//判断数组满的情况下收尾特殊情况
            for (int i = 0; i < l; i++) {
                b[i] = a[i];
            }
            f = 0;
        }
        else{// f n 不等于size
            for (int i = 0; i < l; i++) {
                b[i] = a[i];
            }
            int m = a.length -1,j =newlength-1;
            for (int i = 0; i < f; i++) {
                b[j--] =a[m--];
            }
        }
       a = b;
   }
   public void resize1(int cap){// 如果不是贴近数组两端，在数组中间，进行缩减
       type[] b = (type[]) new java.lang.Object[cap];
       int c = a.length - f;
       for (int i = 0; i < f + l - a.length; i++) {
         b[i] =  a[c];
         c++;
       }

       l =f + l - a.length;
       f =0;
       a = b;
   }
    public void addFirst(type type) {
       if(size == a.length){//扩容
           resize(a.length*2);
       }
       if(f == a.length)
           f = 0;
       a[a.length-f-1] = type;
       f++;
       size++;
    }
    @Override
    public type removeFirst() {
        if(size == 0)
            return null;
        if(f == 0)
            f = a.length;
       type c = a[(a.length - f)%a.length];
       a[(a.length - f)%a.length] = null;
       f--;
       size --;
        if(size <= a.length/4){//缩减 if sum> a.length 需分析
            if(f + l <a.length){
                resize(a.length/4);//存储再数组两端
            }
            else
                resize1(a.length/4);//中间连续存储
        }
       return c;

   }
    @Override
    public void addLast(type type) {
        if(size == a.length){
            resize(a.length*2);
        }
        if(l == a.length){
            l = 0;
        }
        a[l] = type;
        l++;
        size++;
    }
    @Override
    public type removeLast() {
       if(size == 0)
           return null;
        if(l==0)
            l = a.length;
       type c = a[(l-1)% a.length];
        //a[(l-1+a.length)% a.length] = null;
        a[(l-1)% a.length] = null;
        l--;
        size--;
        if(size <= a.length/4){//缩减 if sum> a.length 需分析
            if(f + l <a.length){
                resize(a.length/4);
            }
            else
                resize1(a.length/4);//连续存储
        }
        return c;
    }

    public type getFirst() {
       if(size == 0)
        return null;
       return a[a.length-f];
    }
    public type getLast() {
       if(size == 0)
        return null;
       return a[l-1];
    }
    public type get(int index){
       if(size == 0)
          return null;
       return a[(a.length-f+index)%a.length];
    }
    public void printDeque(){
        for (int i = 0; i < size ; i += 1) {
            System.out.print(get(i) + " ");
        }
        System.out.println();
   }
    @Override
    public int size() {
        return size;
    }
/*
    @Override
    public boolean isEmpty() {//直接看size
       if(size == a.length)
           return false;
        if((f + l)%a.length==0){
            return true;
        }
        return false;
    }

 */
    //内部类
    private  class Iter implements Iterator<type> {
        private  int index;
        public Iter(){
           index = f;
        }
        @Override
        public boolean hasNext() {
            if(index == 0) index = a.length;
            if(a[(a.length-index)%a.length]!= null)
                return true;
            return false;
        }
        @Override
        public type next() {
            if(hasNext()){
                type c = a[(a.length-index)%a.length];
                index --;
                return c;
            }
            return null;
        }
    }
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
        if (((ArrayDeque<type>) o).size() != size()) {
            return false;
        }
        for (int i = 0; i < size; i += 1) {
            if (get(i) != ((ArrayDeque<type>) o).get(i)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public Iterator<type> iterator() {
        return new Iter();
    }
}
