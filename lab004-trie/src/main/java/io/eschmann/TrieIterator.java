package io.eschmann;

import java.util.Iterator;
import java.util.Map;

/**
 * Created by eschmar on 24/11/16.
 */
public class TrieIterator implements Iterator<Map.Entry<String, Integer>> {
    Trie root;
    Trie current;
    Trie previous;

    public TrieIterator(Trie node) {
        this.root = node;
        this.current = node;
        this.previous = null;
    }

    public boolean hasNext() {
        Trie result = findNext();
        if (result != null && result.value > 0) return true;
        return false;
    }

    private Trie findNext() {
        Trie temp = current;
        if (temp.children.length == 0) {
            while (temp.next == null && temp.parent != null && temp.parent != root) {
                temp = temp.parent;
            }

            temp = temp.next;
        }

        while (temp != null && temp.children.length > 0) {
            if (temp.value > 0 && temp != previous) {
                break;
            }

            temp = temp.children.head;
        }

        return temp;
    }

    public Map.Entry<String, Integer> next() {
        current = findNext();
        previous = current;
        return new Word(this.extractKey(current), current.value);
    }

    private String extractKey(Trie node) {
        StringBuilder out = new StringBuilder();
        while (node.parent != null) {
            out.insert(0, node.k);
            node = node.parent;
        }

        return out.toString();
    }

    public class Word implements Map.Entry<String, Integer>, Comparable<Word> {
        String key;
        Integer value;

        public Word(String key, Integer value) {
            this.key = key;
            this.value = value;
        }

        public String getKey() {
            return this.key;
        }

        public Integer getValue() {
            return this.value;
        }

        public Integer setValue(Integer value) {
            this.value = value;
            return null;
        }

        public boolean equals(Object o) {
            return this.key.equals(o);
        }

        public int hashCode() {
            return 0;
        }

        @Override
        public String toString() {
            return "[" + key + "," + value + "]";
        }

        public int compareTo(Word that) {
            if ((int) this.value < (int) that.value) return -1;
            else if (this.value == that.value) return 0;
            else return 1;
        }
    }
}
