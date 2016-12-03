package io.eschmann;

import se.kth.id1020.util.Document;

/**
 * Created by eschmar on 03/12/16.
 */
public class DocumentWrapper {
    Document doc;
    int firstOccurence;
    int hits;

    public DocumentWrapper(Document doc, int occurence) {
        this.doc = doc;
        this.firstOccurence = occurence;
        this.hits = 1;
    }
}
