package io.eschmann;

import edu.princeton.cs.introcs.StdOut;

/**
 * Created by eschmar on 03/11/16.
 */
public class PascalTriangle {
    public void printPascal(int n) {
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= i; j++) {
                StdOut.printf("%d ", binom(i, j));
            }

            StdOut.println("");
        }
    }

    public int binom(int n, int k) {
        if (k == n || k == 0) {
            return 1;
        }

        return binom(n - 1, k - 1) + binom(n - 1, k);
    }
}
