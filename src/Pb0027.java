import java.util.Set;

import utils.Primes;

/**
 * https://projecteuler.net/problem=27
 */
public class Pb0027 {

    public static void main(String[] args) {
        long start = System.currentTimeMillis();

        final int PRIMES_CACHE = 100_000;
        Set<Integer> primes = Primes.getPrimesUntilFast(PRIMES_CACHE);
        int bestA = 0;
        int bestB = 0;
        int bestNbOfPrimes = 0;
        for (int a = -999; a < 1_000; ++a) {
            if (a%100==0) {
                System.out.println("a=" + a);
            }
            for (int b = -999; b < 1_000; ++b) {
                // if not more than bestNbOfPrimes: don't bother
                if (!Primes.isPrime(formula(bestNbOfPrimes, a, b))) {
                    continue;
                }
                for (int n = 0; ; ++n) {
                    long value = formula(n, a, b);
                    if (value > PRIMES_CACHE) {
                        throw new RuntimeException("primes cache too small for value=" + value);
                    }
                    if (!primes.contains((int)value)) {
                        // finished generating primes
                        if (n + 1 > bestNbOfPrimes) {
                            bestA = a;
                            bestB = b;
                            bestNbOfPrimes = n + 1;
                        }
                        break;
                    }
                }
            }
        }

        System.out.println(String.format("Pb0027: a=%d, b=%d, nb of primes=%d in %d ms", bestA, bestB, bestNbOfPrimes,
                System.currentTimeMillis() - start));


    }

    private static long formula(int n, int a, int b) {
        return (n * n) + (a * n) + b;
    }

}