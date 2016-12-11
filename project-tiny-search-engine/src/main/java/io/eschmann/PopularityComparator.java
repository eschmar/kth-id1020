package io.eschmann;

/**
 * Created by eschmar on 03/12/16.
 */
public class PopularityComparator extends AbstractComparator {
    public PopularityComparator(int strategy) {
        super(strategy);
    }

    public int compare(DocumentWrapper o1, DocumentWrapper o2) {
        if (o1.doc.popularity < o2.doc.popularity) return this.strategy * -1;
        if (o1.doc.popularity == o2.doc.popularity) {
            return o1.doc.name.compareTo(o2.doc.name);
        };
        return this.strategy * 1;
    }
}