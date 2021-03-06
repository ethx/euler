package utils;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Utility methods for integers
 */
public class Integers {

    /**
     * A square is either 0, 1, 4 or 9 modulo 16. Shift this number to the mod 16 of the number
     * and compare the first bit to 1 to know if this is the case.
     */
    private static final long SQUARES_MOD_16 = (1 << 0) | (1 << 1) | (1 << 4) | (1 << 9);
    public static final BigInteger NOT_SQUARE = BigInteger.valueOf(-1);

    private Integers() {
    }

    public static BigInteger sqrtIntegral(BigInteger input) {
        return sqrtIntegral(input, BigInteger.valueOf(100));
    }

    /**
     * Returns -1 if the input is not a square number. Otherwise returns the square root.
     */
    public static BigInteger sqrtIntegral(BigInteger input, BigInteger initialEstimate) {
        // fast exclusion of numbers with a bad modulo 16
        if ((
                (SQUARES_MOD_16 >> (input.intValue() & 0xF)) // don't care if input is truncated, we just want mod 16
                        & 1)
                != 1) {
            return NOT_SQUARE;
        }

        // Apply Newton's method to find the sqrt
        // f(x) = x2 - input
        // fPrime(x) = 2x
        Set<BigInteger> alreadyTried = new HashSet<>();
        BigInteger estimate = initialEstimate;
        for (int i = 0; i < 1_000; ++i) {
            BigInteger fEstimate = estimate.multiply(estimate).subtract(input);
            if (fEstimate.equals(BigInteger.ZERO)) {
                return estimate;
            }
            // not the answer, try another one
            alreadyTried.add(estimate);
            BigInteger fPrimeEstimate = estimate.add(estimate); // 2x
            BigInteger move = fEstimate.divide(fPrimeEstimate); // f(x)/f'(x)
            if (move.signum() != -1) {
                move = move.add(BigInteger.ONE);
            }
            estimate = estimate.subtract(move);
            if (alreadyTried.contains(estimate)) {
                return NOT_SQUARE;
            }
        }
        throw new RuntimeException("still not found after 1_000 iterations: " + input);
    }

    public static boolean isSquare(long in) {
        long sqrt = (long) Math.sqrt(in);
        return (sqrt*sqrt == in);
    }

    public static Set<Long> primeFactors(long in) {
        if (in < 2) {
            return new HashSet<>();
        }
        Set<Long> primeFactors = new HashSet<>();
        List<Long> primesCache = Primes.getPrimesUntil((long)Math.sqrt(in));

        for (long current = in; current != 1;) {
            if (Primes.isPrime(current)) {
                // last prime factor
                primeFactors.add(current);
                break;
            }
            for (long prime : primesCache) {
                if (prime * prime > current) {
                    // nothing divides the current number
                    primeFactors.add(current);
                    break;
                }
                if (current % prime == 0) {
                    primeFactors.add(prime);
                    current = current / prime;
                    break;
                }
            }
        }

        return primeFactors;
    }

    public static Map<Long, Integer> primeDecomposition(long in) {
        if (in < 2) {
            return new HashMap<>();
        }
        Map<Long, Integer> primeFactors = new HashMap<>();
        List<Long> primesCache = Primes.getPrimesUntil((long)Math.sqrt(in));

        for (long current = in; current != 1;) {
            if (Primes.isPrime(current)) {
                // last prime factor
                primeFactors.compute(current, (k, v) -> 1 + (v == null ? 0 : v));
                break;
            }
            for (long prime : primesCache) {
                if (prime * prime > current) {
                    // nothing divides the current number
                    primeFactors.compute(current, (k, v) -> 1 + (v == null ? 0 : v));
                    break;
                }
                if (current % prime == 0) {
                    primeFactors.compute(prime, (k, v) -> 1 + (v == null ? 0 : v));
                    current = current / prime;
                    break;
                }
            }
        }

        return primeFactors;
    }

    public static Set<Long> divisors(long in) {
        Set<Long> divisors = new HashSet<>();
        for (long i = 1; i*i <= in; ++i) {
            if (in % i == 0) {
                divisors.add(i);
                divisors.add(in/i);
            }
        }
        return divisors;
    }

    public static List<Integer> extractDigits(long l) {
        List<Integer> res = new ArrayList<>();
        while (l>0) {
            res.add((int)(l%10));
            l/=10;
        }
        return res;
    }
}
