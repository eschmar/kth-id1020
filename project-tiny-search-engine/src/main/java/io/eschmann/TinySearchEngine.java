package io.eschmann;

import se.kth.id1020.TinySearchEngineBase;
import se.kth.id1020.util.Attributes;
import se.kth.id1020.util.Document;
import se.kth.id1020.util.Sentence;
import se.kth.id1020.util.Word;

import java.util.*;

/**
 * Created by eschmar on 01/12/16.
 */
public class TinySearchEngine implements TinySearchEngineBase {
    public static final int SORT_COUNT = 0;
    public static final int SORT_OCCURRENCE = 1;
    public static final int SORT_POPULARITY = 2;
    public static final String SORT_COUNT_TERM = "count";
    public static final String SORT_OCCURRENCE_TERM = "occurrence";
    public static final String SORT_POPULARITY_TERM = "popularity";

    public static final int ORDER_ASC = 1;
    public static final int ORDER_DESC = -1;

    private static final int TOGGLE_OUTPUT = 0;

    int sortStrategy = SORT_COUNT;
    int orderStrategy = ORDER_DESC;

    HashMap<String, IndexNode> dictionary;
    Query query;

    public TinySearchEngine() {
        this.dictionary = new HashMap<String, IndexNode>();
    }

    public void preInserts() {
        System.out.println("preInserts");
    }

    public void insert(Sentence sentence, Attributes attributes) {
        IndexNode current;
        for (Word w : sentence.getWords()) {
            current = this.dictionary.get(w.word);

            if (current == null) {
                this.dictionary.put(w.word, new IndexNode(w.word, attributes));
                continue;
            }

            current.addDocument(attributes);
        }
    }

    public void postInserts() {
        System.out.println("postInserts");
    }

    public List<Document> search(String s) {
        this.query = parseQuery(s);
        return null;
    }

    private void union(ArrayList list, DocumentWrapper wrapper) {
        BinarySearch<DocumentWrapper> bs = new BinarySearch<DocumentWrapper>();
        int pos = bs.search(wrapper, list);
        if (pos < 0) {
            list.add(-pos-1, wrapper);
            return;
        }

        DocumentWrapper temp = (DocumentWrapper) list.get(pos);
        temp.occurrences += wrapper.occurrences;
        if (wrapper.firstOccurrence < temp.firstOccurrence) {
            temp.firstOccurrence = wrapper.firstOccurrence;
        }
    }

    private void printArrayList(List list) {
        for (Object o : list) {
            System.out.println(o);
        }
    }

    private String getSortingStrategy(int strategy) {
        if (strategy == SORT_OCCURRENCE) {
            return SORT_OCCURRENCE_TERM;
        }else if (strategy == SORT_POPULARITY) {
            return SORT_POPULARITY_TERM;
        }

        return SORT_COUNT_TERM;
    }

    private String getOrderStrategy(int strategy) {
        return this.orderStrategy == ORDER_DESC ? "desc" : "asc";
    }

    private Query parseQuery(String query) {
        String[] parts = query.split("orderby");
        String[] terms = parts[0].split(" ");

        Stack<Comparable<String>> source = new Stack<Comparable<String>>();
        Stack<Comparable<String>> temp = new Stack<Comparable<String>>();

        for (int i = 0; i < terms.length; i++) {
            source.push(terms[i]);
        }

        Comparable<String> current;
        String term;
        while (!source.empty()) {
            term = (String) source.pop();

            if (term.length() == 1 && Query.OPERATORS.contains(term)) {
                Comparable<String> a = temp.pop();
                Comparable<String> b = temp.pop();
                temp.push(new Query(a, term, b));
            }else {
                temp.push(term);
            }
        }

        // handle cases without any operator.
        if (temp.size() > 1) {
            StringBuilder merger = new StringBuilder();
            while (!temp.empty()) {
                merger.append(temp.pop() + " ");
            }

            temp.push(merger.toString().trim());
        }

        Comparable<String> computed = temp.pop();
        Query parsed;

        if (computed instanceof String) {
            parsed = new Query(computed);
        }else {
            parsed = (Query) computed;
        }

        if (parts.length < 2) return parsed;

        if (parts[1].contains("desc")) {
            this.orderStrategy = ORDER_DESC;
        }else if (parts[1].contains("asc")) {
            this.orderStrategy = ORDER_ASC;
        }

        if (parts[1].contains(SORT_COUNT_TERM)) {
            this.sortStrategy = SORT_COUNT;
        } else if (parts[1].contains(SORT_OCCURRENCE_TERM)) {
            this.sortStrategy = SORT_OCCURRENCE;
        }else if (parts[1].contains(SORT_POPULARITY_TERM)) {
            this.sortStrategy = SORT_POPULARITY;
        }

        return parsed;
    }

    public String infix(String s) {
        Query query = this.parseQuery(s);
        query.setFormat(Query.FORMAT_INFIX);

        String parsed = query.toString();
        parsed += " orderby " + getSortingStrategy(this.sortStrategy);
        parsed += " " + getOrderStrategy(this.orderStrategy);
        return parsed;
    }
}
