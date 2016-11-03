package io.eschmann;

import edu.princeton.cs.introcs.StdOut;

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

    public int binom(int n, int k) {
        validateBinomialCoefficientArguments(n, k);
        if (k == n || k == 0) {
            return 1;
        }

        String key = getBinomHashKey(n, k);

        if (binomMap.containsKey(key)) {
            return (Integer) binomMap.get(key);
        }

        int result = (int) (factorial(n, k) / factorial(n - k));
        //int result = (int) (factorial(n) / (factorial(k) * factorial(n - k)));
        binomMap.put(key, result);
        return result;
    }
}
