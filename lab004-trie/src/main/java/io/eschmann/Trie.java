package io.eschmann;

import java.util.HashMap;
import java.util.Iterator;


/**
 * Created by eschmar on 23/11/16.
 */
public class Trie implements TrieInterface, Iterable {
    private long value;
    private long subTrieSum;
    private Trie parent;
    private HashMap<Character, Trie> children;
    private final long defaultValue = 1;

    public Trie() {
        this.children = new HashMap<Character, Trie>();
    }

    public Iterator iterator() {
        return null;
    }

    public void put(String key) {
        this.put(key, defaultValue);
    }

    public void put(String key, long value) {
        if (key.isEmpty()) {
            this.value += value;
            this.bubbleUpSum(value);
            return;
        }

        char k = key.charAt(0);
        if (!children.containsKey(k)) {
            Trie node = new Trie();
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

    public long get(String key) {
        Trie node = this.getSubTrie(key);
        return node == null ? 0 : node.value;
    }

    public Trie getSubTrie(String key) {
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
        return node.count();
    }

    public long distinct(String key) {
        return 0;
    }

    public long getValue() {
        return this.value;
    }
}
