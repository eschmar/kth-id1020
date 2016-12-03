package io.eschmann;

import se.kth.id1020.util.Attributes;
import se.kth.id1020.util.Word;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by eschmar on 01/12/16.
 */
public class IndexNode implements Comparable<IndexNode> {
    public String word;
    public ArrayList<DocumentWrapper> docs;
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
        this.docs = new ArrayList<DocumentWrapper>();
        if (attr == null) return;
        this.addDocument(attr);
    }

    public void addDocument(Attributes attr) {
        count++;
        DocumentWrapper temp = new DocumentWrapper(attr.document, attr.occurrence);
        int pos = Collections.binarySearch(this.docs, temp);

        if (pos < 0) {
            this.docs.add(-pos-1, temp);
            return;
        }

        DocumentWrapper item = this.docs.get(pos);
        if (attr.occurrence < item.firstOccurrence) {
            item.firstOccurrence = attr.occurrence;
        }

        item.occurrences++;
    }

    @Override
    public String toString() {
        return this.word + " (" + this.count + ")";
    }
}
