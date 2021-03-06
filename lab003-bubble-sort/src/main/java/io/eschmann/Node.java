package io.eschmann;

/**
 * Created by eschmar on 19/11/16.
 */
public class Node implements Comparable<Node> {
    public int val;
    public Node next;

    public Node(int val, Node next) {
        this.val = val;
        this.next = next;
    }

    public Node(int val) {
        this.val = val;
    }

    public int compareTo(Node that) {
        if (this.val < that.val) return -1;
        else if (this.val == that.val) return 0;
        else return 1;
    }
}
