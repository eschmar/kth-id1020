package io.eschmann;

/**
 * Created by eschmar on 23/11/16.
 */
public interface TrieInterface {
    public void put(String key);
    public void put(String key, long value);
    public long get(String key);
    public long count(String key);
    public long distinct(String prefix);
}
