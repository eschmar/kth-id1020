package io.eschmann;

import edu.princeton.cs.introcs.StdOut;

/**
 * Created by eschmar on 03/11/16.
 */
public class Main {
    public static void main(String[] args) {
        StdOut.println("Pew!");
        RecursivePascal pt = new RecursivePascal();
        pt.printPascal(5);
    }
}
