package io.eschmann;

import edu.princeton.cs.introcs.StdIn;
import edu.princeton.cs.introcs.StdOut;

/**
 * Created by eschmar on 03/11/16.
 */
public class Main {
    public static void main(String[] args) {
        boolean exit = false;

        while (!exit) {
            StdOut.println("\nRecursive Pascal's Triangle");
            StdOut.println("---------------------------\n");

            StdOut.print("Amount of rows: ");
            int n = StdIn.readInt();

            StdOut.print("Reverse output [y/n]? ");
            String reverse = StdIn.readString();

            StdOut.println("\nTriangle:\n");
            RecursivePascal pt = new RecursivePascal(reverse.equals("y") ? true : false);
            pt.printPascal(n);

            StdOut.print("\nRepeat [y/n]? ");
            String repeat = StdIn.readString();
            exit = !repeat.equals("y");
        }
    }
}
