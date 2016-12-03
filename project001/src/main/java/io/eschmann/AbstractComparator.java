package io.eschmann;

import java.util.Comparator;

/**
 * Created by eschmar on 03/12/16.
 */
public class AbstractComparator implements Comparator<DocumentWrapper> {
    protected int strategy;

    public AbstractComparator() {
        this.strategy = 1;
    }

    public AbstractComparator(int strategy) {
        this.strategy = strategy;
    }

    public int compare(DocumentWrapper o1, DocumentWrapper o2) {
        return o1.doc.name.compareTo(o2.doc.name);
    }
}