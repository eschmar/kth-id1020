package io.eschmann;

import se.kth.id1020.TinySearchEngineBase;
import se.kth.id1020.util.Attributes;
import se.kth.id1020.util.Document;
import se.kth.id1020.util.Word;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by eschmar on 01/12/16.
 */
public class TinySearchEngine implements TinySearchEngineBase {
    public static final int SORT_COUNT = 0;
    public static final int SORT_OCCURENCE = 1;
    public static final int SORT_POPULARITY = 2;

    public static final int ORDER_ASC = 0;
    public static final int ORDER_DESC = 1;

    private static final int TOGGLE_OUTPUT = 1;

    ArrayList<IndexNode> index;
    int sortStrategy = SORT_COUNT;
    int orderStrategy = ORDER_ASC;

    public TinySearchEngine() {
        this.index = new ArrayList<IndexNode>();
    }

    public void insert(Word word, Attributes attributes) {
        IndexNode node = new IndexNode(word.word, attributes);
        int pos = Collections.binarySearch(this.index, node);

        if (pos < 0) {
            this.index.add(-pos-1, node);
        }else {
            this.index.get(pos).addAttribute(attributes);
        }
    }

    public List<Document> search(String s) {
        List<Document> result = new ArrayList<Document>();
        String[] terms = parseQuery(s);

        for (String query : terms) {
            IndexNode node = new IndexNode(query, null);
            int pos = Collections.binarySearch(this.index, node);

            if (pos < 0) continue;

            for (Attributes attr : this.index.get(pos).attributes) {
                if (!result.contains(attr.document)) {
                    result.add(attr.document);
                }
            }
        }

        System.out.println(this.getParsedQueryVisualisation(terms));
        return result;
    }

    private String getParsedQueryVisualisation(String[] terms) {
        StringBuilder out = new StringBuilder();
        out.append("Parsed query: [" + terms[0]);

        for (int i = 1; i < terms.length; i++) {
            out.append(", " + terms[i]);
        }

        out.append("] orderby " + getSortingStrategy(this.sortStrategy));
        out.append(" " + getOrderStrategy(this.orderStrategy));
        return out.toString();
    }

    private String getSortingStrategy(int strategy) {
        if (strategy == SORT_OCCURENCE) {
            return "occurence";
        }else if (strategy == SORT_POPULARITY) {
            return "popularity";
        }

        return "count";
    }

    private String getOrderStrategy(int strategy) {
        return this.orderStrategy == ORDER_DESC ? "desc" : "asc";
    }

    private String[] parseQuery(String query) {
        if (query.contains(":desc")) {
            this.orderStrategy = ORDER_DESC;
        }else if (query.contains(":asc")) {
            this.orderStrategy = ORDER_ASC;
        }

        String[] parts = query.split(":orderby");
        String[] result = parts[0].split(" ");
        if (parts.length < 2) return result;

        if (parts[1].contains("count")) {
            this.sortStrategy = SORT_COUNT;
        } else if (parts[1].contains("occurence")) {
            this.sortStrategy = SORT_OCCURENCE;
        }else if (parts[1].contains("popularity")) {
            this.sortStrategy = SORT_POPULARITY;
        }

        return result;
    }
}
