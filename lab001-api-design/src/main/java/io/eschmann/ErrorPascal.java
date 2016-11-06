package io.eschmann;

import java.util.HashMap;

/**
 * Created by eschmar on 03/11/16.
 */
abstract public class ErrorPascal implements Pascal {
    protected boolean reverse = false;
    protected HashMap binomMap;

    public ErrorPascal() {
        this.binomMap = new HashMap<String, Integer>();
    }

    public ErrorPascal(boolean reverse) {
        this.reverse = reverse;
        this.binomMap = new HashMap<String, Integer>();
    }

    abstract public void printPascal(int n);
    abstract public int binom(int n, int k);

    protected void validateIsPositive(int x) {
        if (x < 0) {
            throw new IllegalArgumentException("Argument has to be greater than 0.");
        }
    }

    protected void validateBinomialCoefficientArguments(int n, int k) {
        if (n < 0 || k < 0) {
            throw new IllegalArgumentException("Arguments have to be greater than 0.");
        }else if (k > n) {
            throw new IllegalArgumentException("The second argument can not be greater than the first.");
        }
    }

    protected String getBinomHashKey(int n, int k) {
        // exploits symmetry
        return n + "," + (k > (n / 2) ? (n - k) : k);
    }

    protected String getPascalRow(int n) {
        StringBuilder row = new StringBuilder();

        for (int i = 0; i <= n; i++) {
            row.append(binom(n, i) + " ");
        }

        return row.toString();
    }
}
