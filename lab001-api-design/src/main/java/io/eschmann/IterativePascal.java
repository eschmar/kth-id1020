package io.eschmann;

import java.util.HashMap;

/**
 * Created by eschmar on 03/11/16.
 */
public class IterativePascal extends ErrorPascal {
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

        int[] coeff = new int[n + 1];
        coeff[0] = 1;

        for (int i = 1; i <= n; i++) {
            coeff[i] = 1;

            for (int j = i - 1; j > 0; j--) {
                coeff[j] += coeff[j - 1];
            }
        }

        int result = coeff[k];
        binomMap.put(key, result);
        return result;
    }
}
