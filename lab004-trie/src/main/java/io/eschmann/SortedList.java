package io.eschmann;

/**
 * Created by eschmar on 23/11/16.
 */
public class SortedList<T> {
    Node head;
    Node tail;
    int length = 0;

    public SortedList() {}
    public void add(Node<? extends T> node) {
        length++;

        if (head == null) {
            head = node;
            tail = head;
            return;
        }else if (node.compareTo(head) == -1) {
            node.next = head;
            head = node;
        }

        Node temp = head;
        while (temp.next != null && node.compareTo(temp.next) < 1) {
            temp = temp.next;
        }

        node.next = temp.next;
        temp.next = node;

        if (node.next == null) {
            tail = node;
        }
    }

    public Node get(int index) {
        Node temp = head;
        while (temp.key != index && temp.next != null) {
            temp = temp.next;
        }

        if (temp.key == index) {
            return temp;
        }

        return null;
    }

    public Node getNext(int index) {
        Node temp = this.get(index);
        return temp != null ? temp.next : null;
    }

    public class Node<T extends Comparable<T>> implements Comparable<Node<T>> {
        public int key;
        public T val;
        public Node next;

        public Node(int key, T val, Node<T> next) {
            this.key = key;
            this.val = val;
            this.next = next;
        }

        public Node(int key, T val) {
            this.key = key;
            this.val = val;
        }

        public int compareTo(Node<T> that) {
            if (this.val.compareTo(that.val) < 0) return -1;
            else if (this.val == that.val) return 0;
            else return 1;
        }
    }
}
