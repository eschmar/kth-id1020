package io.eschmann;

import edu.princeton.cs.introcs.StdOut;

/**
 * Created by eschmar on 03/11/16.
 */
public class Main {
    public static void main(String[] args) {
        StdOut.println("Pew!");
        PascalTriangle pt = new PascalTriangle();
        pt.printPascal(5);
    }
}
