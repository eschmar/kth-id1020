package io.eschmann;

/**
 * Created by eschmar on 19/11/16.
 */
public class Inversion {
    public static long countInversions(SinglyLinkedList list) {
        long inversions = 0;
        Node comparator = list.first;
        Node current;

        while (comparator != null) {
            current = comparator.next;

            while (current != null) {
                if (comparator.val > current.val) {
                    inversions++;
                }

                current = current.next;
            }

            comparator = comparator.next;
        }

        return inversions;
    }

    public static long countInversionsAndPrint(SinglyLinkedList list) {
        long inversions = 0;
        boolean swapped = true;

        Node comparator = list.first;
        Node current = comparator.next;
        int i = 0;
        int j = 1;

        while (comparator != null) {
            j = i + 1;
            current = comparator.next;

            while (current != null) {
                if (comparator.val > current.val) {
                    System.out.print("[" + i + "," + j + "] ");
                    inversions++;

                    if (inversions % 20 == 0) {
                        System.out.println();
                    }
                }

                current = current.next;
                j++;
            }

            comparator = comparator.next;
            i++;
        }

        System.out.println();
        return inversions;
    }
}
