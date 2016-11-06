package io.eschmann;

import java.util.HashMap;

/**
 * Created by eschmar on 03/11/16.
 */
public class RecursivePascal {
    protected boolean reverse = false;
    protected HashMap binomMap;

    public RecursivePascal() {
        this.binomMap = new HashMap<String, Integer>();
    }

    public RecursivePascal(boolean reverse) {
        this.reverse = reverse;
        this.binomMap = new HashMap<String, Integer>();
    }

    public void printPascal(int n) {
        StringBuilder row = new StringBuilder();

        for (int i = 0; i <= n; i++) {
            row.append(binom(n, i) + " ");
        }

        if (reverse) {
            System.out.println(row);
        }

        if (n > 0) {
            printPascal(n - 1);
        }

        if (!reverse) {
            System.out.println(row);
        }
    }

    public int binom(int n, int k) {
        if (k == n || k == 0) {
            return 1;
        }

        String key = getBinomHashKey(n, k);

        if (binomMap.containsKey(key)) {
            return (Integer) binomMap.get(key);
        }

        int result = binom(n - 1, k - 1) + binom(n - 1, k);
        binomMap.put(key, result);
        return result;
    }

    protected String getBinomHashKey(int n, int k) {
        // exploits symmetry
        return n + "," + (k > (n / 2) ? (n - k) : k);
    }
}
