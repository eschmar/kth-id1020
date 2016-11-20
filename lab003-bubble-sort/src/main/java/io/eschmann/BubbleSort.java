package io.eschmann;

/**
 * Created by eschmar on 19/11/16.
 */
public class BubbleSort {
    private BubbleSort() {}

    public static long sort(SinglyLinkedList list) {
        long swaps = 0;
        int R = list.length - 2;
        boolean swapped = true;

        while (R >= 0 && swapped == true) {
            swapped = false;
            for (int i = 0; i <= R; i++) {
                Node current = list.get(i);
                if (current.val > current.next.val) {
                    swapped = true;
                    list.swapNodeValues(current, current.next);
                    swaps++;
                }
            }

            R--;
        }

        return swaps;
    }
}
