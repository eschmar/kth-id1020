package io.eschmann;

import java.util.ArrayList;

public class BinarySearch<T extends Comparable> {
    public int search(T key, ArrayList<T> a) {
        return search(key, a, 0, a.size());
    }

    public int search(T key, ArrayList<T> a, int lo, int hi) {
        if (hi <= lo) return -hi - 1;
        int mid = lo + (hi - lo) / 2;
        int cmp = a.get(mid).compareTo(key);
        if      (cmp > 0) return search(key, a, lo, mid);
        else if (cmp < 0) return search(key, a, mid+1, hi);
        else              return mid;
    }
}

