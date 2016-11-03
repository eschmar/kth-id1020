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

        StringBuilder row = new StringBuilder();

        for (int i = 0; i <= n; i++) {
            row.append(binom(n, i) + " ");
        }

        if (reverse) {
            StdOut.print(row + "\n");
        }

        printPascal(--n);

        if (!reverse) {
            StdOut.print(row + "\n");
        }
    }

    public int binom(int n, int k) {
        if (k == n || k == 0) {
            return 1;
        }

        return binom(n - 1, k - 1) + binom(n - 1, k);
    }
}
