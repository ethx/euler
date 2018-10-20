package utils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
                if (prime * prime > i) {
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

    /**
     * Generate primes using Eratosthenes sieve.
     */
    public static Set<Integer> getPrimesUntilFast(int max) {
        Set<Integer> primes = new HashSet<>();
        primes.add(2);

        // allocate bit field
        // bit set means not prime
        // to save some space, don't store even numbers
        // the byte index for a given nb is thus nb/2/8
        byte[] bitField = new byte[max/8+1];
        bitField[0] = (byte)(bitField[0] | (1<<0)); // 1 is not prime

        for (int i = 3; i <= max; i+=2) {
            if (i%10_000_000==1) {
                System.out.println("generating primes..." + i);
            }
            int btIdx = i/2/8;
            if ((bitField[btIdx] & (1<<(i/2-btIdx*8))) == 0) {
                // prime
                primes.add(i);
                // all multipliers of this prime are not primes
                for(int multiplier=2; i*multiplier<=max; multiplier++) {
                    int notPrime = multiplier*i;
                    if (notPrime%2==0) {
                        continue;
                    }
                    int byteIdx = notPrime/2/8;
                    bitField[byteIdx] = (byte)(bitField[byteIdx] | (1<<(notPrime/2-byteIdx*8)));
                }
            }
        }

        return primes;
    }


    public static List<Long> getPrimesUntil(long max) {
        List<Long> primes = new ArrayList<>();
        for (long i = 2; i <= max; ++i) {
            if (i%10_000_000==0) {
                System.out.println("generating primes..." + i);
            }
            boolean isPrime = true;
            for (Long prime : primes) {
                if (prime * prime > i) {
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
            }
        }
        return primes;
    }

    public static boolean isPrime(long p) {
        if (p <= 1) {
            return false;
        }

        for (long l = 2; l <= Math.sqrt(p); ++l) {
            if (p % l == 0) {
                return false;
            }
        }
        return true;

    }
}
