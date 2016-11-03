package io.eschmann;

import edu.princeton.cs.introcs.StdIn;
import edu.princeton.cs.introcs.StdOut;

/**
 * Created by eschmar on 03/11/16.
 */
public class Driver {
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

            Pascal[] triangles = new Pascal[2];
            triangles[0] = new RecursivePascal(isReverse);
            triangles[1] = new IterativePascal(isReverse);

            StdOut.println();
            for (Pascal tr : triangles) {
                StdOut.printf("%s:\n", tr.getClass().getSimpleName());
                tr.printPascal(n);
                StdOut.println();
            }

            StdOut.print("Repeat [y/n]? ");
            String repeat = StdIn.readString();
            exit = !repeat.equals("y");
        }
    }
}
