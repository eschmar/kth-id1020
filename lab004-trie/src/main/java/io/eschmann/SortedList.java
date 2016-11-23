package io.eschmann;

/**
 * Created by eschmar on 23/11/16.
 */
public class SortedList {
    Trie head;
    Trie tail;
    int length = 0;

    public SortedList() {}

    public void add(Trie node) {
        length++;

        if (head == null) {
            head = node;
            tail = head;
            return;
        }else if (node.compareTo(head) == -1) {
            node.next = head;
            head = node;
            return;
        }


        Trie previous = head;
        Trie temp = previous.next;

        while (temp != null && node.compareTo(temp) > -1) {
            previous = temp;
            temp = temp.next;
        }

        node.next = temp;
        previous.next = node;

        if (node.next == null) {
            tail = node;
        }
    }

    public Trie get(int index) {
        if (head == null) return null;

        Trie temp = head;
        while (temp.k != index && temp.next != null) {
            temp = temp.next;
        }

        if (temp.k == index) {
            return temp;
        }

        return null;
    }

    public Trie getNext(int index) {
        Trie temp = this.get(index);
        return temp != null ? temp.next : null;
    }

    public boolean containsKey(int key) {
        return this.get(key) != null ? true : false;
    }
}
