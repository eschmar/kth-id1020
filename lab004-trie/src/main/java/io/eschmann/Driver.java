package io.eschmann;

import edu.princeton.cs.introcs.StdIn;
import edu.princeton.cs.introcs.StdOut;

/**
 * Created by eschmar on 23/11/16.
 */
public class Driver {
    public static void main(String[] args) {
        boolean exit = false;

        while (!exit) {
            StdOut.println("\nCounting Trie");
            StdOut.println("---------------\n");

            Trie trie = new Trie();

            StdOut.print("Repeat [y/n]? ");
            String repeat = StdIn.readString();
            exit = !repeat.equals("y");
        }
    }
}