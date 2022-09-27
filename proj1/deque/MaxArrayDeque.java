package deque;

import java.util.Comparator;

public class MaxArrayDeque<type> extends ArrayDeque<type> {
    private Comparator<type> self;

    public MaxArrayDeque(Comparator<type> c) {
         self = c;
    }

    public type max() {
        type maxItem = this.get(0);
        for (int i = 0; i < size(); i += 1) {
            if (self.compare(maxItem, get(i)) < 0) {
                maxItem = get(i);
            }
        }
        return maxItem;
    }

    public type max(Comparator<type> c) {
        type maxItem = this.get(0);
        for (int i = 0; i < size(); i += 1) {
            if (c.compare(maxItem, get(i)) < 0) {
                maxItem = get(i);
            }
        }

        return maxItem;
    }

}
