package deque;

import org.junit.Test;

import javax.swing.*;
import java.util.Collections;
import java.util.Comparator;

public class MaxArrayDeque<type> extends ArrayDeque<type> {

    private Comparator<type> d;
    public MaxArrayDeque(Comparator<type> c){
        super();
        d = c;
    }
    public type max(){
    return max(d);
    }

    public type max(Comparator<type> c){
        if(size == 0)
            return null;
        type max = get(0);
        for(int i = 1 ;i < size ;i ++)
        if(c.compare(max,get(i))==-1)
            max = get(i);
        return max;
    }
}
class ArrayComparator implements Comparator<Integer>{
    @Override
    public int compare(Integer o1, Integer o2) {
        if(o1 == o2){
            return 0;
        }
        else if(o2 < o1){
            return 1;
        }
        else
            return -1;
    }
}

 class  ttt{
    @Test
    public static void main(String[] ar){
        ArrayComparator arrayComparator = new ArrayComparator();
        System.out.println(arrayComparator.compare(1,2));

        MaxArrayDeque a = new MaxArrayDeque(arrayComparator);
        a.addFirst(2);
        a.addFirst(5);
        a.addFirst(4);
        System.out.println(a.max());
    }
}

