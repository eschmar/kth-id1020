package io.eschmann;

import edu.princeton.cs.introcs.StdOut;

import java.util.HashMap;

/**
 * Created by eschmar on 03/11/16.
 */
public class IterativePascal implements Pascal {
    private boolean reverse = false;
    private HashMap binomMap;

    public IterativePascal() {
        this.binomMap = new HashMap<String, Integer>();
    }

    public IterativePascal(boolean reverse) {
        this.reverse = reverse;
        this.binomMap = new HashMap<String, Integer>();
    }

    public void printPascal(int n) {
        if (n < 0) {
            return;
        }

        StringBuilder result = new StringBuilder();

        for (int i = 0; i <= n; i++) {
            StringBuilder row = new StringBuilder();

            for (int j = 0; j <= i; j++) {
                row.append(binom(i, j) + " ");
            }

            if (reverse) {
                result.insert(0, row + "\n");
            }else {
                result.append(row).append("\n");
            }
        }

        StdOut.println(result);
    }

    private int factorial(int x) {
        if (x <= 1) {
            return 1;
        }

        int result = x;
        for (int i = 1; i < x; i++) {
            result *= i;
        }

        return result;
    }

    public int binom(int n, int k) {
        if (k == n || k == 0) {
            return 1;
        }

        // check if already calculated (symmetry!)
        String key = n + "," + (k > (n / 2) ? (n - k) : k);

        if (binomMap.containsKey(key)) {
            return (Integer) binomMap.get(key);
        }

        int result = (int) (factorial(n) / (factorial(k) * factorial(n - k)));
        binomMap.put(key, result);
        return result;
    }
}
