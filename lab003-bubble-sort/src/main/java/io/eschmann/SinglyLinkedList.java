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

    public void swapWithNextNode(int i) {
        if (i == 0) {
            Node temp = first;
            Node second = first.next;
            temp.next = second.next;
            first = second;
            first.next = temp;
            return;
        }

        Node previous = get(i - 1);
        Node current = previous.next;
        Node next = current.next;

        previous.next = next;
        current.next = next.next;
        next.next = current;
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();

        Node temp = first;
        while (temp.next != null) {
            output.append(temp.val + " -> ");
            temp = temp.next;
        }

        output.append(temp.val + " -> NULL");
        return output.toString();
    }
}
