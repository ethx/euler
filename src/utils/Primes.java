package utils;

import java.util.ArrayList;

/**
 * Utility methods for primes
 */
public class Primes {

    private Primes() {
    }

    /**
     * Compute the n-th prime.
     */
    public static long getNthPrime(int n) {
        ArrayList<Long> primes = new ArrayList<>();
        for (long i = 2; ; ++i) {
            boolean isPrime = true;
            for (Long prime : primes) {
                if (prime*prime > i) {
                    // Check only until sqrt
                    break;
                }

                if (i % prime == 0) {
                    isPrime = false;
                    break;
                }
            }
            if (isPrime) {
                // prime because not divisible by any previous prime
                primes.add(i);
                if (primes.size() == n) {
                    return i;
                }
            }
        }
    }
}