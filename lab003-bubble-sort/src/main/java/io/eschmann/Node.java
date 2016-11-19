package io.eschmann;

/**
 * Created by eschmar on 19/11/16.
 */
public class Node {
    public Node(int val, Node next) {
        this.val = val;
        this.next = next;
    }

    public Node(int val) {
        this.val = val;
    }

    public int val;
    public Node next;
}
