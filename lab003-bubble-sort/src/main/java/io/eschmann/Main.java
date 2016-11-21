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

        StdOut.print("Print inversions [y/n]? ");
        answer = StdIn.readString();

        StdOut.println("\nList created:");
        StdOut.println(list);

        StdOut.println("\nCount inversions...");

        long inversions;
        if (answer.equals("y")) {
            inversions = Inversion.countInversionsAndPrint(list);
        }else {
            inversions = Inversion.countInversions(list);
        }

        StdOut.println("Sort list...");
        Stopwatch timer = new Stopwatch();
        long swaps = BubbleSort.sort(list);
        StdOut.println("Sorted list in " + timer.elapsedTime() + " seconds.");

        StdOut.println("\nList sorted:");
        StdOut.println(list);

        StdOut.println("\n# Swaps: " + swaps + ", # Inversions: " + inversions);
    }

    public static int getRandom(int min, int max) {
        return min + (int)(Math.random() * ((max - min) + 1));
    }
}
