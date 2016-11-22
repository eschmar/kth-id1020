package io.eschmann;

/**
 * Created by eschmar on 19/11/16.
 */
public class Inversion {
    public static long bruteForceCount(SinglyLinkedList list) {
        long inversions = 0;
        Node comparator = list.first;
        Node current;

        while (comparator != null) {
            current = comparator.next;

            while (current != null) {
                if (comparator.val > current.val) {
                    inversions++;
                }

                current = current.next;
            }

            comparator = comparator.next;
        }

        return inversions;
    }

    public long inversions;
    public SinglyLinkedList sortedList;

    public long mergeSortCount(SinglyLinkedList list) {
        this.inversions = 0;
        sortedList = mergeSort(list);
        return this.inversions;
    }

    private SinglyLinkedList mergeSort(SinglyLinkedList list) {
        if (list.length < 2) {
            return list;
        }

        int split = (int) (list.length / 2);
        Node splitNode = list.get(split -1);

        SinglyLinkedList a = new SinglyLinkedList(list.first, split);
        SinglyLinkedList b = new SinglyLinkedList(splitNode.next, list.length - split);

        SinglyLinkedList sortedA = mergeSort(a);
        SinglyLinkedList sortedB = mergeSort(b);

        SinglyLinkedList result = new SinglyLinkedList();
        Node left = sortedA.first;
        Node right = sortedB.first;

        int i = 0;
        while (left != null || right != null) {
            if (left == null) {
                result.append(right);
                break;
            }else if (right == null) {
                result.append(left);
                break;
            }else {
                if (left.val <= right.val) {
                    result.append(left);
                    left = left.next;
                    i++;
                }else {
                    result.append(right);
                    right = right.next;

                    // increase inversions if the value of the left item
                    // is larger than theo ne of the right item.
                    inversions += (split - i);
                }
            }
        }

        return result;
    }
}
