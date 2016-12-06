package io.eschmann;

/**
 * Created by eschmar on 03/12/16.
 */
public class CountComparator extends AbstractComparator {
    public CountComparator(int strategy) {
        super(strategy);
    }

    public int compare(DocumentWrapper o1, DocumentWrapper o2) {
        if (o1.occurrences < o2.occurrences) return this.strategy * -1;
        if (o1.occurrences == o2.occurrences) {
            return o1.doc.name.compareTo(o2.doc.name);
        };
        return this.strategy * 1;
    }
}
