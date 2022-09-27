package deque;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;

import java.util.Comparator;

import static org.junit.Assert.*;

public class mainTest {

    public static class originalComparator implements Comparator<String> {
        public int compare(String a, String b) {
            return a.length() - b.length();
        }
    }

    public static class alphabeticComparator implements Comparator<String> {
        public int compare(String a, String b) {
            return a.compareTo(b);
        }
    }

    public static originalComparator getOriginalComparator() {
        return new originalComparator();
    }

    public static alphabeticComparator getAlphabeticComparator() {
        return new alphabeticComparator();
    }

    public static void main(String[] args) {
        originalComparator o = getOriginalComparator();
        alphabeticComparator a = getAlphabeticComparator();
        MaxArrayDeque<String> lld1 = new MaxArrayDeque<>(o);
        lld1.addFirst("Mother");
        lld1.addFirst("Father");
        lld1.addFirst("Brother");
        lld1.addFirst("Sister");
        lld1.addFirst("Uncle");
        lld1.addFirst("Aunt");
        lld1.addFirst("Wife");
        lld1.addFirst("Cousin");

        String m1 = lld1.max();
        String m2 = lld1.max(a);
    }
}
