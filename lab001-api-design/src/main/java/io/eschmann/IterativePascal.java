package io.eschmann;

import java.util.HashMap;

/**
 * Created by eschmar on 03/11/16.
 */
public class IterativePascal extends ErrorPascal implements Pascal {
    public IterativePascal() {
        super();
    }

    public IterativePascal(boolean reverse) {
        super(reverse);
    }

    public void printPascal(int n) {
        validateIsPositive(n);
        StringBuilder result = new StringBuilder();

        for (int i = 0; i <= n; i++) {
            String row = getPascalRow(i);

            if (reverse) {
                result.insert(0, row + "\n");
            }else {
                result.append(row).append("\n");
            }
        }

        System.out.println(result);
    }

    public int binom(int n, int k) {
        validateBinomialCoefficientArguments(n, k);
        String key = getBinomHashKey(n, k);

        if (binomMap.containsKey(key)) {
            return (Integer) binomMap.get(key);
        }

        double result = 1;
        for (double i = 1; i <= k; i++) {
            result *= (((double)n - ((double)k - i)) / i);
        }

        binomMap.put(key, (int) result);
        return (int) result;
    }
}
