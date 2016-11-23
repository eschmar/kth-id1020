package io.eschmann;

/**
 * Created by eschmar on 19/11/16.
 */
public class SinglyLinkedList {
    Node first;
    Node last;
    int length = 0;

    public SinglyLinkedList() {}
    public SinglyLinkedList(Node[] nodes) {
        for (int i = 0; i < nodes.length; i++) {
            append(nodes[i]);
        }
    }

    public SinglyLinkedList(Node node, int length) {
        for (int i = 0; i < length; i++) {
            append(new Node(node.val));
            node = node.next;
        }
    }

    public SinglyLinkedList(int[] values) {
        for (int i = 0; i < values.length; i++) {
            append(new Node(values[i]));
        }
    }

    public void append(Node n) {
        length++;

        if (first == null) {
            first = n;
            last = first;
            return;
        }

        last.next = n;
        last = n;
    }

    public void prepend(Node n) {
        length++;

        if (first == null) {
            last = n;
        }

        n.next = first;
        first = n;
    }

    public Node get(int i) {
        if (i >= length || i < 0) {
            throw new IndexOutOfBoundsException("Invalid index given.");
        }else if (i == length - 1) {
            return last;
        }

        Node wanted = first;
        for (int j = 0; j < i; j++) {
            wanted = wanted.next;
        }

        return wanted;
    }

    public void swap(Node current, Node next) {
        int temp = current.val;
        current.val = next.val;
        next.val = temp;
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();

        Node temp = first;
        int i = 0;
        while (temp.next != null) {
            output.append(temp.val + " -> ");
            temp = temp.next;
        }

        output.append(temp.val + " -> NULL");
        return output.toString();
    }

    public String toString(int limit) {
        StringBuilder output = new StringBuilder();

        int i = 0;
        Node temp = first;
        while (temp.next != null && i < limit) {
            output.append(temp.val + " -> ");
            temp = temp.next;
            i++;
        }

        if (i < limit) {
            output.append(temp.val + " -> NULL");
        }else {
            output.append(temp.val + " -> ...");
        }

        return output.toString();
    }
}
