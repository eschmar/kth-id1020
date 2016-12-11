package io.eschmann;

import se.kth.id1020.TinySearchEngineBase;
import se.kth.id1020.util.Attributes;
import se.kth.id1020.util.Document;
import se.kth.id1020.util.Sentence;
import se.kth.id1020.util.Word;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Stack;

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

    public static final String OPERATORS = "+|-";

    private static final int TOGGLE_OUTPUT = 0;

    ArrayList<IndexNode> index;
    int sortStrategy = SORT_COUNT;
    int orderStrategy = ORDER_DESC;

    public TinySearchEngine() {
        this.index = new ArrayList<IndexNode>();
    }

    public void insert(Word word, Attributes attributes) {
        IndexNode node = new IndexNode(word.word, attributes);
        BinarySearch<IndexNode> bs = new BinarySearch<IndexNode>();
        int pos = bs.search(node, this.index);

        if (pos < 0) {
            this.index.add(-pos-1, node);
            return;
        }

        this.index.get(pos).addDocument(attributes);
    }

    public List<Document> search(String s) {
        String[] terms = parseQuery(s);
        ArrayList<DocumentWrapper> result = new ArrayList<DocumentWrapper>();
        BinarySearch<IndexNode> bs = new BinarySearch<IndexNode>();

        // execute search for each query term
        for (String query : terms) {
            IndexNode node = new IndexNode(query, null);
            int pos = bs.search(node, this.index);

            if (pos < 0) continue;

            for (DocumentWrapper doc : this.index.get(pos).docs) {
                union(result, doc);
            }
        }

        if (TOGGLE_OUTPUT == 1) {
            System.out.println("\n<debug>");
            printArrayList(result);
        }

        // sort result
        Comparator comparator;
        if (this.sortStrategy == SORT_OCCURRENCE) {
            comparator = new OccurrenceComparator(this.orderStrategy);
        } else if (this.sortStrategy == SORT_POPULARITY) {
            comparator = new PopularityComparator(this.orderStrategy);
        } else {
            comparator = new CountComparator(this.orderStrategy);
        }

        BubbleSort bubble = new BubbleSort(comparator);
        bubble.sort(result);

        if (TOGGLE_OUTPUT == 1) {
            System.out.println("post sort:");
            printArrayList(result);
            System.out.println("</debug>\n");
        }

        // convert for output
        List<Document> output = new ArrayList<Document>();
        for (DocumentWrapper wrapper : result) {
            output.add(wrapper.doc);
        }

        return output;
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

    private String[] parseQuery(String query) {
        String[] parts = query.split("orderby");
        String[] result = parts[0].split(" ");

        if (parts.length < 2) return result;

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

        return result;
    }

    public void preInserts() {
        System.out.println("preInserts");
    }

    public void insert(Sentence sentence, Attributes attributes) {

    }

    public void postInserts() {
        System.out.println("postInserts");
    }

    public String infix(String s) {
        String[] terms = this.parseQuery(s);
        Stack<String> operands = new Stack<String>();
        Stack<String> temp = new Stack<String>();

        for (int i = 0; i < terms.length; i++) {
            operands.push(terms[i]);
        }

        String current;
        while (!operands.empty()) {
            current = operands.pop();

            // check if operator
            if (current.length() == 1 && OPERATORS.contains(current)) {
                String a = temp.pop();
                String b = temp.pop();

                temp.push("(" + a + " " + current + " " + b + ")");
            }else {
                temp.push(current);
            }
        }

        String parsed = temp.pop();
        parsed += " orderby " + getSortingStrategy(this.sortStrategy);
        parsed += " " + getOrderStrategy(this.orderStrategy);

        return parsed;
    }
}
