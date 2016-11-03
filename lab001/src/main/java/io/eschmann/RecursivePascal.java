package io.eschmann;

import edu.princeton.cs.introcs.StdOut;

import java.util.HashMap;

/**
 * Created by eschmar on 03/11/16.
 */
public class RecursivePascal {
    private boolean reverse = false;
    private HashMap binomMap;

    public RecursivePascal() {
        this.binomMap = new HashMap<String, Integer>();
    }

    public RecursivePascal(boolean reverse) {
        this.reverse = reverse;
        this.binomMap = new HashMap<String, Integer>();
    }

    public void printPascal(int n) {
        if (n < 0) {
            return;
        }

        StringBuilder row = new StringBuilder();

        for (int i = 0; i <= n; i++) {
            row.append(binom(n, i) + " ");
        }

        if (reverse) {
            StdOut.print(row + "\n");
        }

        printPascal(--n);

        if (!reverse) {
            StdOut.print(row + "\n");
        }
    }

    public int binom(int n, int k) {
        if (k == n || k == 0) {
            return 1;
        }

        String key = n + "," + k;

        if (binomMap.containsKey(key)) {
            return (Integer) binomMap.get(key);
        }

        int result = binom(n - 1, k - 1) + binom(n - 1, k);
        binomMap.put(key, result);
        return result;
    }
}
