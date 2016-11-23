package io.eschmann;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;


/**
 * Created by eschmar on 23/11/16.
 */
public class Trie implements TrieInterface, Iterable, Comparable<Trie> {
    public char k;
    private long value;
    private long subTrieSum;
    private long subTrieDistinctSum;
    private Trie parent;
    private HashMap<Character, Trie> children;

    private SortedList<Trie> childrn;

    private final long defaultValue = 1;

    public Trie(char k) {
        this.k = k;
        this.children = new HashMap<Character, Trie>();
        this.childrn = new SortedList<Trie>();
    }

    public int compareTo(Trie that) {
        if (this.k < that.k) return -1;
        else if (this.k == that.k) return 0;
        else return 1;
    }

    public Iterator iterator() {
        return null;
    }

    public void put(String key) {
        this.put(key, defaultValue);
    }

    public void put(String key, long value) {
        if (key.isEmpty()) {
            if (this.value == 0) {
                this.bubbleUpDistinct();
            }

            this.value += value;
            this.bubbleUpSum(value);
            return;
        }

        char k = key.charAt(0);



        if (!children.containsKey(k)) {
            Trie node = new Trie(k);
            node.parent = this;
            children.put(k, node);
        }

        children.get(k).put(key.substring(1), value);
    }

    private void bubbleUpSum(long value) {
        this.subTrieSum += value;
        if (this.parent != null) {
            this.parent.bubbleUpSum(value);
        }
    }

    private void bubbleUpDistinct() {
        this.subTrieDistinctSum++;
        if (this.parent != null) {
            this.parent.bubbleUpDistinct();
        }
    }

    public long get(String key) {
        Trie node = this.getSubTrie(key);
        return node == null ? 0 : node.value;
    }

    public Trie getSubTrie(String key) {
        // check if root is queried
        if (key.equals("-") && this.k == '-') return this;

        // traverse tree recursively to queried key
        Trie current = this;
        for (int i = 0; i < key.length(); i++) {
            if (!current.children.containsKey(key.charAt(i))) {
                return null;
            }
            current = current.children.get(key.charAt(i));
        }

        return current;
    }

    public long count() {
        return this.subTrieSum;
    }

    public long count(String prefix) {
        Trie node = this.getSubTrie(prefix);
        if (node == null) return 0;
        return node.count();
    }

    public long distinct(String prefix) {
        Trie node = this.getSubTrie(prefix);
        if (node == null) return 0;
        return node.distinct();
    }

    public long distinct() {
        return this.subTrieDistinctSum;
    }

    public long getValue() {
        return this.value;
    }
}
