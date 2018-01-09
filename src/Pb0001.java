/**
 * https://projecteuler.net/problem=1
 */
public class Pb0001 {

    public static void main(String[] args) {
        long resBruteForce = bruteForce();
        long resSmart = smart();
        if (resBruteForce == resSmart) {
            System.out.println(String.format("Pb0001: %d", resBruteForce));
        } else {
            throw new RuntimeException(String.format("solution mismatch: %d vs %d", resBruteForce, resSmart));
        }
    }

    /**
     * Just iterate and sum the multiples.
     */
    private static long bruteForce() {
        long sum = 0;
        for (int i = 0; i < 1000; ++i) {
            if (i % 3 == 0 || i % 5 == 0) {
                sum += i;
            }
        }
        return sum;
    }

    /**
     * Compute without iterating with a simple math formula.
     */
    private static long smart() {
        int max = 1000;
        return multiplesSum(3, max)
                + multiplesSum(5, max)
                - multiplesSum(3 * 5, max);
    }

    /**
     * Sum multiples of base in [1, max).
     */
    private static long multiplesSum(int base, int max) {
        /*
         * let m = (max-1)/base
         * let n = base
         *
         * n + 2n + 3n + ... = n * (1 + 2 + 3 + ... + m)
         *                   = n * m(m+1)/2
         */

        long m = (max - 1) / base;

        return base * m * (m + 1) / 2;
    }
}
