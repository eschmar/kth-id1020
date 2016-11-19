package io.eschmann;

/**
 * Created by eschmar on 19/11/16.
 */
public class BubbleSort {
    private BubbleSort() {}

    public static void sort(SinglyLinkedList list) {

        int R = list.length - 2;
        boolean swapped = true;

        while (R >= 0 && swapped == true) {
            swapped = false;
            for (int i = 0; i < R; i++) {
                Node current = list.get(i);
                if (current.val > current.next.val) {
                    swapped = true;
                    list.swapWithNextNode(i);
                }
            }

            R--;
        }
    }
}
