package io.eschmann;

import java.util.HashMap;

/**
 * Created by eschmar on 03/11/16.
 */
public class RecursivePascal extends ErrorPascal {
    public RecursivePascal() {
        super();
    }

    public RecursivePascal(boolean reverse) {
        super(reverse);
    }

    public void printPascal(int n) {
        validateIsPositive(n);
        String row = getPascalRow(n);

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
