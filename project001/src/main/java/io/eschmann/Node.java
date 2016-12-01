package io.eschmann;

import com.sun.tools.doclint.HtmlTag;
import se.kth.id1020.util.Attributes;
import se.kth.id1020.util.Document;
import se.kth.id1020.util.Word;

import java.util.ArrayList;

/**
 * Created by eschmar on 01/12/16.
 */
public class Node implements Comparable<Node> {
    public Word word;
    public ArrayList<Attributes> attributes;

    public int compareTo(Node o) {
        return word.word.compareTo(o.word.word);
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Word) {
            return this.word.equals(((Word) o).word);
        }

        return false;
    }

    @Override
    public int hashCode() {
        int result = word.word.hashCode();
        return result;
    }

    public Node(Word word, Attributes attr) {
        this.word = word;
        this.attributes = new ArrayList<Attributes>();
        if (attr == null) return;
        this.attributes.add(attr);
    }
}
