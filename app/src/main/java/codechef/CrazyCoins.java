package codechef;

import java.util.*;

/**
 * See: https://www.codechef.com/LRNDSA10/problems/CSUMD
 */
public class CrazyCoins {
    
    public static final class Combo {
        public final int num1s;
        public final int num2s;

        public Combo(int num1s, int num2s) {
            this.num1s = num1s;
            this.num2s = num2s;
        }

        @Override
        public boolean equals(Object other) {
            return (null != other) &&
                other instanceof Combo &&
                ((Combo) other).num1s == this.num1s &&
                ((Combo) other).num2s == this.num2s;
        }

        @Override
        public int hashCode() {
            int hash = 13;
            hash += 31 * hash + this.num1s;
            hash += 31 * hash + this.num2s;
            return hash;            
        }
    }

    public static int numCombos(int total) {
        return 1 + Math.floorDiv(total, 2);
    }

    public static int numChooseM(int n, int m) {
        if (m >= n || m < 0 || n < 0) {
            return 1;
        }
        int quot = n;
        for (int i=1; i < m; ++i) {
            quot *= n - i;
        }
        int div = m;
        for (int i=2; i < m; ++i) {
            div *= i;
        }
        return Math.floorDiv(quot, div);
    }

    public static int numPermsWithFlips(int total) {
        // number of ways to get the total with 
        // 1 and 2 cent coins
        final int numCombos = numCombos(total);
        // number of permutations - intialized to all 1 cent coins
        int result = (int) Math.pow(2, total - 1);
        // add permutations for each number of 2 cent coins
        for (int num2s=1; num2s < numCombos; ++num2s) {
            final int totalCoins = num2s + total - 2*num2s;
            // number of ways to flip those coins,
            // leaving the fist one heads up
            if (totalCoins > 1) {
                // number of linear arrangements of coins
                // all heads up
                final int numAllHeads = numChooseM(totalCoins, num2s);
                final int numFlips = (int) Math.pow(2, totalCoins - 1);
                result += numAllHeads * numFlips;
            } else {
                result += 1;
            }
        }
        return result;
    }
}

