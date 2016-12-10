package io.eschmann;

/**
 * Created by eschmar on 03/12/16.
 */
public class OccurrenceComparator extends AbstractComparator {
    public OccurrenceComparator(int strategy) {
        super(strategy);
    }

    public int compare(DocumentWrapper o1, DocumentWrapper o2) {
        if (o1.firstOccurrence < o2.firstOccurrence) return this.strategy * -1;
        if (o1.firstOccurrence == o2.firstOccurrence) {
            return o1.doc.name.compareTo(o2.doc.name);
        };
        return this.strategy * 1;
    }
}
