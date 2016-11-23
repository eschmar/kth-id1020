package io.eschmann;

import java.util.HashMap;
import java.util.Iterator;


/**
 * Created by eschmar on 23/11/16.
 */
public class Trie implements TrieInterface, Iterable {
    private long value;
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
            return;
        }

        char k = key.charAt(0);
        if (!children.containsKey(k)) {
            Trie node = new Trie();
            children.put(k, node);
        }

        children.get(k).put(key.substring(1), value);
    }

    public long get(String key) {
        Trie current = this;
        for (int i = 0; i < key.length(); i++) {
            if (!current.children.containsKey(key.charAt(i))) {
                return 0;
            }
            current = current.children.get(key.charAt(i));
        }

        return current.value;
    }

    public long count(String key) {
        return 0;
    }

    public long distinct(String key) {
        return 0;
    }
}
