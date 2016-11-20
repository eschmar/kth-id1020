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
        Node current;

        while (R >= 0 && swapped == true) {
            swapped = false;

            current = list.first;
            while (current != null && current.next != null) {
                if (current.val > current.next.val) {
                    swapped = true;
                    list.swap(current, current.next);
                    swaps++;
                }

                current = current.next;
            }

            R--;
        }

        return swaps;
    }
}
