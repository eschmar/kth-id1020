package io.eschmann;

import edu.princeton.cs.introcs.StdIn;
import edu.princeton.cs.introcs.StdOut;

/**
 * Created by eschmar on 06/11/16.
 */
public class Main {
    public static void main(String[] args) {
        boolean exit = false;

        while (!exit) {
            StdOut.println("\nPascal's Triangle");
            StdOut.println("-----------------\n");

            StdOut.print("Amount of rows: ");
            int n = StdIn.readInt();

            StdOut.print("Reverse output [y/n]? ");
            String reverse = StdIn.readString();
            boolean isReverse = reverse.equals("y") ? true : false;

            StdOut.println();
            RecursivePascal triangle = new RecursivePascal(isReverse);
            triangle.printPascal(n);

            StdOut.print("\nRepeat [y/n]? ");
            String repeat = StdIn.readString();
            exit = !repeat.equals("y");
        }
    }
}
