package io.eschmann;

import se.kth.id1020.util.Document;

/**
 * Created by eschmar on 03/12/16.
 */
public class DocumentWrapper implements Comparable<DocumentWrapper> {
    Document doc;
    int firstOccurrence;
    int occurrences;

    public DocumentWrapper(Document doc, int occurrence) {
        this.doc = doc;
        this.firstOccurrence = occurrence;
        this.occurrences = 1;
    }

    public int compareTo(DocumentWrapper o) {
        return this.doc.name.compareTo(o.doc.name);
    }

    @Override
    public String toString() {
        return "DocumentWrapper{" +
                "doc=" + doc +
                ", firstOccurrence=" + firstOccurrence +
                ", occurrences=" + occurrences +
                '}';
    }
}
