package io.eschmann;

import java.util.HashMap;

/**
 * Created by eschmar on 03/12/16.
 */
public class RelevanceComparator extends AbstractComparator {
    protected HashMap<String, Integer> documentWordCount;
    protected int resultCount;

    public RelevanceComparator(int strategy, HashMap<String, Integer> documentWordCount, int resultCount) {
        super(strategy);
        this.documentWordCount = documentWordCount;
        this.resultCount = resultCount;
    }

    protected void calculateRelevance(DocumentWrapper item) {
        if (item.relevance != null) return;

        Double tfidf = tf(item.occurrences, this.documentWordCount.get(item.doc.name));
        tfidf *= idf(this.documentWordCount.size(), this.resultCount);

        item.relevance = tfidf;
    }

    protected double tf(int occurrences, int wordCount) {
        return (double) occurrences / (double) wordCount;
    }

    protected double idf(int totalDocuments, int documentsContainingTerm) {
        return Math.log10((double) totalDocuments / (double) documentsContainingTerm);
    }

    public int compare(DocumentWrapper o1, DocumentWrapper o2) {
        this.calculateRelevance(o1);
        this.calculateRelevance(o2);

        if (o1.relevance < o2.relevance) return this.strategy * -1;
        if (o1.relevance.equals(o2.relevance)) {
            return o1.doc.name.compareTo(o2.doc.name);
        };
        return this.strategy * 1;
    }
}
