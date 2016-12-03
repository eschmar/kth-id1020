package io.eschmann;

import java.util.ArrayList;

/**
 * Created by eschmar on 03/12/16.
 */
public class BubbleSort<T extends Comparable> {
    public void sort(ArrayList<T> list) {
        int R = list.size() - 2;
        boolean swapped = true;

        while (R >= 0 && swapped == true) {
            swapped = false;
            for (int i = 0; i <= R; i++) {
                if (list.get(i).compareTo(list.get(i+1)) == 1) {
                    swapped = true;
                    T temp = list.get(i);
                    list.set(i, list.get(i + 1));
                    list.set(i + 1, temp);
                }
            }

            R--;
        }
    }
}
