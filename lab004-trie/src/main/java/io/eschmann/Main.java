package io.eschmann;

import edu.princeton.cs.introcs.StdIn;
import edu.princeton.cs.introcs.StdOut;

import java.util.Iterator;
import java.util.Map;

/**
 * Created by eschmar on 23/11/16.
 */
public class Main {
    public static void main(String[] args) {
        StdOut.println("\nCounting Trie");
        StdOut.println("---------------");
        StdOut.print("Set: ");

        boolean exit = false;

        Trie trie = new Trie('-');
        String[] keys = {"ab", "ac", "ac", "b", "bad", "baf", "baf", "baf", "bc", "bc", "bc", "bc"};
        for (String k : keys) {
            trie.put(k);
            StdOut.print(k + ", ");
        }

        String query;
        StdOut.println();

        while (!exit) {
            StdOut.print("\nSearch for: ");
            query = StdIn.readString();

            StdOut.println("Your query \"" + query + "\" yielded:");
            StdOut.println("value = " + trie.get(query));
            StdOut.println("count = " + trie.count(query));
            StdOut.println("distinct = " + trie.distinct(query));
            StdOut.println("Children chars: " + trie.getSubTrie(query));

            StdOut.println("Keys found:");
            Iterator iterator = trie.iterator(query);
            TrieIterator.Word word;

            while (iterator.hasNext()) {
                word = (TrieIterator.Word) iterator.next();
                StdOut.println(word);
            }

            StdOut.print("\nNew query [y/n]? ");
            String repeat = StdIn.readString();
            exit = !repeat.equals("y");
        }
    }
}