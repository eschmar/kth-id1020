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
        // check if already calculated (symmetry!)
        return n + "," + (k > (n / 2) ? (n - k) : k);
    }

    protected int factorial(int x) {
        return factorial(x, 0);
    }

    protected int factorial(int x, int until) {
        if (x <= 1) {
            return 1;
        }

        int result = x;
        for (int i = x - 1; i > until; i--) {
            result *= i;
        }

        return result;
    }
}
