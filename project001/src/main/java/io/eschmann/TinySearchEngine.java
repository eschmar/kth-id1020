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
    ArrayList<Node> index;

    public TinySearchEngine() {
        this.index = new ArrayList<Node>();
    }

    public void insert(Word word, Attributes attributes) {
        Node node = new Node(word, attributes);
        this.index.add(node);
        return;
    }

    public List<Document> search(String s) {
        List<Document> result = new ArrayList<Document>();

        // reduce
        for (Node n : this.index) {
            if (n.word.word.equals(s)) {
                if (!result.contains(n.attributes.get(0).document)) {
                    result.add(n.attributes.get(0).document);
                }
            }
        }

        return result;
    }
}
