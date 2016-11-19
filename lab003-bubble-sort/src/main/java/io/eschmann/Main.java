package io.eschmann;

import edu.princeton.cs.introcs.StdIn;
import edu.princeton.cs.introcs.StdOut;

/**
 * Created by eschmar on 19/11/16.
 */
public class Main {
    public static void main(String[] args) {
        int[] a = {5, 6, 4, 3, 1, 2, 3, 4, 5, 6, 7};
        SinglyLinkedList list = new SinglyLinkedList(a);
        StdOut.println(list);
        BubbleSort.sort(list);
        StdOut.println(list);
    }
}
