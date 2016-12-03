package io.eschmann;

import se.kth.id1020.util.Attributes;
import se.kth.id1020.util.Word;

import java.util.ArrayList;

/**
 * Created by eschmar on 01/12/16.
 */
public class IndexNode implements Comparable<IndexNode> {
    public String word;
    public ArrayList<Attributes> attributes;
    public int count;

    public int compareTo(IndexNode o) {
        return word.compareTo(o.word);
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Word) {
            return this.word.equals(((Word) o).word);
        }

        return false;
    }

    @Override
    public int hashCode() {
        int result = word.hashCode();
        return result;
    }

    public IndexNode(String word, Attributes attr) {
        this.word = word;
        this.attributes = new ArrayList<Attributes>();
        if (attr == null) return;
        this.attributes.add(attr);
        count++;
    }

    public void addAttribute(Attributes attr) {
        this.attributes.add(attr);
        count++;
    }

    @Override
    public String toString() {
        return this.word + " (" + this.count + ")";
    }
}
