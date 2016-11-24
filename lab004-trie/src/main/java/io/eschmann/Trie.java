package io.eschmann;

import java.util.Iterator;

/**
 * Created by eschmar on 23/11/16.
 */
public class Trie implements TrieInterface, Iterable, Comparable<Trie> {
    public char k;
    public Trie next;
    public int value;
    private int subTrieSum;
    private int subTrieDistinctSum;
    public Trie parent;
    public SortedList children;

    private final int defaultValue = 1;

    public Trie(char k) {
        this.k = k;
        this.children = new SortedList();
    }

    public int compareTo(Trie that) {
        if ((int) this.k < (int) that.k) return -1;
        else if (this.k == that.k) return 0;
        else return 1;
    }

    public void put(String key) {
        this.put(key, defaultValue);
    }

    public void put(String key, int value) {
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
            children.add(node);
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

    public int get(String key) {
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

    public int count() {
        return this.subTrieSum;
    }

    public int count(String prefix) {
        Trie node = this.getSubTrie(prefix);
        if (node == null) return 0;
        return node.count();
    }

    public int distinct(String prefix) {
        Trie node = this.getSubTrie(prefix);
        if (node == null) return 0;
        return node.distinct();
    }

    public int distinct() {
        return this.subTrieDistinctSum;
    }

    public int getValue() {
        return this.value;
    }

    @Override
    public String toString() {
        StringBuilder out = new StringBuilder();
        out.append(this.k).append(" -> ");

        Trie temp = this.children.head;
        while (temp != null) {
            out.append(temp.k).append(" ");
            temp = temp.next;
        }

        return out.toString();
    }

    public Iterator iterator() {
        return this.iterator("-");
    }
    public Iterator iterator(String prefix) {
        return new TrieIterator(this.getSubTrie(prefix));
    }
}
