package io.eschmann;

import edu.princeton.cs.introcs.StdOut;

public class Main {
    public static void main(String[] args) {
        // write your code here
        StdOut.println("Pew!");

//        Node first = new Node("first", null);
//        String[] arr = {"r", "e", "d", "i", "v", "i", "x", "y", "r"};
//        for (int i = 0; i < arr.length; i++) {
//            Node temp = first;
//            while (temp.next != null) {
//                temp = temp.next;
//            }
//
//            temp.next = new Node(arr[i], null);
//        }
//
//
//        StdOut.println(Node.printNode(first));
//        Node magic = Node.fun(first);
//        StdOut.println(Node.printNode(magic));


        int previous = 0;
        for (int i = 1; i < 50; i++) {
            int temp = pew1(i);
            StdOut.print((temp - previous) + " - ");
            previous = temp;
        }
    }

    public static int pew1(int x) {
        int sum = 0;
        for (int n = x; n > 0; n /= 2)
            for (int i = 0; i < n; i++)
                sum++;

        return sum;
    }

    public static int pew2(int x) {
        int sum = 0;
        for (int i = 1; i < x; i *= 2)
            for(int j = 0; j < i; j++)
                sum++;

        return sum;
    }
}
