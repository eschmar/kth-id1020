package io.eschmann;

import java.util.Comparator;
import java.util.List;

/**
 * Created by eschmar on 03/12/16.
 */
public class BubbleSort {
    Comparator comparator;

    public BubbleSort(Comparator comparator) {
        this.comparator = comparator;
    }

    public void sort(List list) {
        int R = list.size() - 2;
        boolean swapped = true;

        while (R >= 0 && swapped == true) {
            swapped = false;
            for (int i = 0; i <= R; i++) {
                if (comparator.compare(list.get(i), list.get(i+1)) == 1) {
                    swapped = true;
                    Object temp = list.get(i);
                    list.set(i, list.get(i + 1));
                    list.set(i + 1, temp);
                }
            }

            R--;
        }
    }
}
