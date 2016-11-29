package io.eschmann;

import edu.princeton.cs.introcs.In;

import java.net.URL;
import java.util.Collections;
import java.util.Iterator;
import java.util.PriorityQueue;

/**
 * Created by eschmar on 28/11/16.
 */
public class Driver {

    public static void main(String[] args) {
        Trie trie = new Trie('-');
        URL url = ClassLoader.getSystemResource("kap1.txt");

        if (url != null) {
            System.out.println("Reading from: " + url);
        } else {
            System.out.println("Couldn't find file: kap1.txt");
        }

        In input = new In(url);

        while (!input.isEmpty()) {
            String line = input.readLine().trim();
            String[] words = line.split("(\\. )|:|,|;|!|\\?|( - )|--|(\' )| ");
            String lastOfLine = words[words.length - 1];

            if (lastOfLine.endsWith(".")) {
                words[words.length - 1] = lastOfLine.substring(0, lastOfLine.length() - 1);
            }

            for (String word : words) {
                String word2 = word.replaceAll("\"|\\(|\\)", "");

                if (word2.isEmpty()) {
                    continue;
                }

                System.out.println(word2);

                // Add the word to the trie
                trie.put(word2);
            }
        }

        //Perform analysis
        System.out.println("\n---");
        System.out.println("Analysis:");
        System.out.println();

        // 1) What are the 10 words with the highest frequency?
        // 2) What are the 10 words with the lowest frequency?

        PriorityQueue<TrieIterator.Word> queue = new PriorityQueue(10);
        PriorityQueue<TrieIterator.Word> queue2 = new PriorityQueue(10, Collections.reverseOrder());

        Iterator it = trie.iterator();
        TrieIterator.Word word;

        while (it.hasNext()) {
            word = (TrieIterator.Word) it.next();
            queue.add(word);
            queue2.add(word);

            if (queue.size() > 10) { queue.poll(); }
            if (queue2.size() > 10) { queue2.poll(); }
        }

        System.out.print("Highest frequency words: ");
        for (TrieIterator.Word w : queue) {
            System.out.print(w + " ");
        }

        System.out.println("\n");

        System.out.print("Lowest frequency words: ");
        for (TrieIterator.Word w : queue2) {
            System.out.print(w + " ");
        }

        System.out.println("\n");

        // 3) Which prefix of length 2 has the highest frequency?
        Trie highestFreq = trie.getHighestCountChild();
        highestFreq = highestFreq.getHighestCountChild();
        System.out.println("The prefix of length 2 with the highest frequency is: '" + highestFreq.parent.k + highestFreq.k + "'\n");

        // 4) What is the letter that the most different words start with? (Not frequency this time.)
        Trie mostDistinct = trie.getMostDistinctChild();

        if (mostDistinct == null) {
            System.out.println("The node you have chosen has no children.");
        }else {
            System.out.println("The letter most different words start with is: '" + mostDistinct.k + "'");
        }
    }
}