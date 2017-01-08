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
    public static final int SORT_RELEVANCE = 0;
    public static final int SORT_POPULARITY = 1;
    public static final String SORT_RELEVANCE_TERM = "relevance";
    public static final String SORT_POPULARITY_TERM = "popularity";

    public static final int ORDER_ASC = 1;
    public static final int ORDER_DESC = -1;

    private StringBuilder log;
    private static final int TOGGLE_OUTPUT = 1;

    int sortStrategy = SORT_RELEVANCE;
    int orderStrategy = ORDER_DESC;

    HashMap<String, IndexNode> dictionary;
    HashMap<String, Integer> documentWordCount;
    HashMap<String, ArrayList<DocumentWrapper>> cache;
    Query query;

    public TinySearchEngine() {
        this.dictionary = new HashMap<String, IndexNode>();
        this.documentWordCount = new HashMap<String, Integer>();
        this.cache = new HashMap<String, ArrayList<DocumentWrapper>>();
        this.log = new StringBuilder();
    }

    protected void log(String message) {
        this.log.append(message + "\n");
    }

    protected void clearLog() {
        this.log.setLength(0);
    }

    public void preInserts() {
        System.out.println("...pre inserts");
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

        // keep track of word count per document
        Integer wordCount = sentence.getWords().size();
        if (this.documentWordCount.containsKey(attributes.document.name)) {
            wordCount += this.documentWordCount.get(attributes.document.name);
        }

        this.documentWordCount.put(attributes.document.name, wordCount);
    }

    public void postInserts() {
        System.out.println("...post inserts");
    }

    public List<Document> search(String s) {
        this.clearLog();
        this.query = parseQuery(s);

        if (!this.cache.containsKey(query.toString())) {
            this.log("Cache miss!");
            this.traverseQueryAndCacheUnknowns(query);
        }

        ArrayList<DocumentWrapper> result = this.cache.get(query.toString());

        // sort result
        Comparator comparator;
        if (this.sortStrategy == SORT_POPULARITY) {
            comparator = new PopularityComparator(this.orderStrategy);
        } else {
            comparator = new RelevanceComparator(this.orderStrategy);
        }

        Collections.sort(result, comparator);

        if (TOGGLE_OUTPUT == 1) {
            this.log("Result set:");
            for (DocumentWrapper item : result) {
                this.log(item.toString());
            }

            System.out.println("\n<DEBUG>");
            System.out.print(this.log.toString());
            System.out.println("</DEBUG>\n");
        }

        // convert for output
        List<Document> output = new ArrayList<Document>();
        for (DocumentWrapper wrapper : result) {
            output.add(wrapper.doc);
        }

        return output;
    }

    private void traverseQueryAndCacheUnknowns(Query query) {
        if (this.cache.containsKey(query)) return;

        if (query.left instanceof Query) {
            this.traverseQueryAndCacheUnknowns((Query) query.left);
        }else if (query.left instanceof String) {
            this.queryWordAndCacheResult(query.left.toString());
        }

        if (query.right != null && query.right instanceof Query) {
            this.traverseQueryAndCacheUnknowns((Query) query.right);
        }else if (query.right != null && query.right instanceof String) {
            this.queryWordAndCacheResult(query.right.toString());
        }

        if (this.query.right == null) return;

        ArrayList<DocumentWrapper> leftResult = this.cache.get(query.left.toString());
        ArrayList<DocumentWrapper> rightResult = this.cache.get(query.right.toString());

        if (query.operator.equals("+")) {
            leftResult = SetHelper.intersection(leftResult, rightResult);
        }else if (query.operator.equals("|")) {
            leftResult = SetHelper.union(leftResult, rightResult);
        }else if (query.operator.equals("-")) {
            leftResult = SetHelper.difference(leftResult, rightResult);
        }

        this.log("Cached: " + query.toString());
        this.cache.put(query.toString(), leftResult);
    }

    private void queryWordAndCacheResult(String query) {
        if (this.cache.containsKey(query)) return;

        ArrayList<DocumentWrapper> result = new ArrayList<DocumentWrapper>();
        IndexNode node = this.dictionary.get(query);

        if (node == null) {
            this.cache.put(query, result);
            this.log("Cached: " + query.toString());
            return;
        }

        for (DocumentWrapper doc : node.docs) {
            // calculate relevance and add to result
            DocumentWrapper temp = new DocumentWrapper(doc.doc, doc.firstOccurrence);
            temp.occurrences = doc.occurrences;
            temp.calculateRelevance(this.documentWordCount, node.docs.size());
            result.add(temp);
        }

        this.cache.put(query, result);
        this.log("Cached: " + query.toString());
    }

    private String getSortingStrategy(int strategy) {
        if (strategy == SORT_POPULARITY) {
            return SORT_POPULARITY_TERM;
        }

        return SORT_RELEVANCE_TERM;
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

        if (temp.size() > 1) {
            throw new IllegalArgumentException("Invalid query. Use correct operators.");
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

        if (parts[1].contains(SORT_RELEVANCE_TERM)) {
            this.sortStrategy = SORT_RELEVANCE;
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
