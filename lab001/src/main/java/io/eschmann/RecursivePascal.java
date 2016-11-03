package io.eschmann;

import edu.princeton.cs.introcs.StdOut;

/**
 * Created by eschmar on 03/11/16.
 */
public class RecursivePascal {
    private boolean reverse = false;

    public RecursivePascal(boolean reverse) {
        this.reverse = reverse;
    }

    public RecursivePascal() {}

    public void printPascal(int n) {
        if (n < 0) {
            return;
        }

        for (int i = 0; i <= n; i++) {
            StdOut.printf("%d ", binom(n, i));
        }

        StdOut.println("");
        printPascal(--n);
    }

    public int binom(int n, int k) {
        if (k == n || k == 0) {
            return 1;
        }

        return binom(n - 1, k - 1) + binom(n - 1, k);
    }
}
