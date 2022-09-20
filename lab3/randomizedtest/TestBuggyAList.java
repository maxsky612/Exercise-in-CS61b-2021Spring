package randomizedtest;

import afu.org.checkerframework.checker.igj.qual.I;
import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by hug.
 */
public class TestBuggyAList {
    // YOUR TESTS HERE
    @Test
    public void testThreeAddThreeRemove() {
        AListNoResizing<Integer> correct = new AListNoResizing<>();
        BuggyAList<Integer> broken = new BuggyAList<>();

        for (int i = 1; i < 4; i += 1) {
            correct.addLast(i);
            broken.addLast(i);
        }
        assertEquals(correct.size(), broken.size());

        for (int i = 0; i < 3; i += 1) {
            assertEquals(correct.removeLast(), broken.removeLast());
        }
    }

    @Test
    public void randomsizedTest(){
        AListNoResizing<Integer> L = new AListNoResizing<>();
        BuggyAList<Integer> M = new BuggyAList<>();

        int N = 5000;
        for (int i = 0; i < N; i += 1){
            int operationNumber = StdRandom.uniform(0, 4);
            if (operationNumber == 0){
                //addLast
                int ranVal = StdRandom.uniform(0, 100);
                L.addLast(ranVal);
                M.addLast(ranVal);
            }
            if (operationNumber == 1){
                //size
                int size1 = L.size();
                int size2 = M.size();
                System.out.println("size1: " + size1 + " size2: " + size2);
                assertEquals(size1,size2);
            }
            if (L.size() > 0 && M.size() > 0){
                if (operationNumber == 2){
                    //getLast
                    int last1 = L.getLast();
                    int last2 = M.getLast();
                    System.out.println("getLast1: " + last1 +" getlast2: " + last2);
                    assertEquals(last1, last2);
                }
                if (operationNumber == 3){
                    //removeLast
                    int r1 = L.removeLast();
                    int r2 = M.removeLast();
                    System.out.println("removeLast1: " + r1 + " removeLast2: " + r2);
                    assertEquals(r1, r2);
                }
            }
        }
    }
}
