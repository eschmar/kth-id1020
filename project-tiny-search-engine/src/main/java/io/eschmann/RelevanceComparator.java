package io.eschmann;

import java.util.HashMap;

/**
 * Created by eschmar on 03/12/16.
 */
public class RelevanceComparator extends AbstractComparator {
    public RelevanceComparator(int strategy) {
        super(strategy);
    }

    public int compare(DocumentWrapper o1, DocumentWrapper o2) {
        if (o1.relevance < o2.relevance) return this.strategy * -1;
        if (o1.relevance.equals(o2.relevance)) {
            return o1.doc.name.compareTo(o2.doc.name);
        };
        return this.strategy * 1;
    }
}
