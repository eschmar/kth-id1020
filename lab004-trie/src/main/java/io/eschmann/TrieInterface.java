package io.eschmann;

/**
 * Created by eschmar on 23/11/16.
 */
public interface TrieInterface {
    public void put(String key);
    public void put(String key, int value);
    public int get(String key);
    public int count(String key);
    public int distinct(String prefix);
}
