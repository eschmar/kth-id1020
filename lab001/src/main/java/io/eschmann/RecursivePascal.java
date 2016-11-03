package io.eschmann;

import edu.princeton.cs.introcs.StdOut;

/**
 * Created by eschmar on 03/11/16.
 */
public class RecursivePascal extends ErrorPascal implements Pascal {
    public RecursivePascal() {
        super();
    }

    public RecursivePascal(boolean reverse) {
        super(reverse);
    }

    public void printPascal(int n) {
        validateIsPositive(n);
        StringBuilder row = new StringBuilder();

        for (int i = 0; i <= n; i++) {
            row.append(binom(n, i) + " ");
        }

        if (reverse) {
            StdOut.print(row + "\n");
        }

        if (--n >= 0) {
            printPascal(n);
        }

        if (!reverse) {
            StdOut.print(row + "\n");
        }
    }

    public int binom(int n, int k) {
        validateBinomialCoefficientArguments(n, k);

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
}
