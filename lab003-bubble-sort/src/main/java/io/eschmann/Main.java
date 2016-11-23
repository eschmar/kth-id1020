package io.eschmann;

import edu.princeton.cs.algs4.Stopwatch;
import edu.princeton.cs.introcs.StdIn;
import edu.princeton.cs.introcs.StdOut;

/**
 * Created by eschmar on 19/11/16.
 */
public class Main {
    public static void main(String[] args) {
        SinglyLinkedList list;
        StdOut.print("Create random list or run sample list [r/s]? ");
        String answer = StdIn.readString();

        if (answer.equals("s")) {
            StdOut.println("\nSAMPLE LIST");
            int[] a = {1,2,4,3,5,0};
            list = new SinglyLinkedList(a);
        }else {
            StdOut.println("\nRANDOM LIST");
            StdOut.print("Create random list of size n = ");
            int size = StdIn.readInt();

            list = new SinglyLinkedList();
            for (int i = 0; i < size; i++) {
                list.append(new Node(getRandom(0, 99999)));
            }
        }

        StdOut.println("\nList created:");
        StdOut.println(list.toString(10));
        Inversion inv = new Inversion();

        //
        // BRUTE FORCE INVERSIONS
        //

        StdOut.println("\nBrute force count inversions...");
        Stopwatch timer = new Stopwatch();
        long inversions = inv.bruteForceCount(list);
        StdOut.println(inversions + " inversions in " + timer.elapsedTime() + "s.");

        //
        // MERGE SORT
        //
        StdOut.println("\nMerge sort count inversions...");

        timer = new Stopwatch();
        inversions = inv.mergeSortCount(list);

        StdOut.println("List sorted with " + inversions + " inversions in " + timer.elapsedTime() + "s.");
        StdOut.println(inv.sortedList.toString(10));

        //
        // BUBBLE SORT
        //
        StdOut.println("\nBubble sort list...");
        timer = new Stopwatch();
        long swaps = BubbleSort.sort(list);
        StdOut.println("Sorted list in " + timer.elapsedTime() + " seconds.");

        StdOut.println("\nList sorted with " + swaps + " swaps:");
        StdOut.println(list.toString(10));
    }

    public static int getRandom(int min, int max) {
        return min + (int)(Math.random() * ((max - min) + 1));
    }
}
