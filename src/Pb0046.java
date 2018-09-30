import java.util.ArrayList;
import java.util.List;

import utils.Integers;

/**
 * https://projecteuler.net/problem=46
 */
public class Pb0046 {

    public static void main(String[] args) {
        long res = 0;
        List<Long> primes = new ArrayList<>();
        primes.add(2L);
        for (long current = 3; ; current += 2) {
            if (!isPrime(current, primes) &&
                    !Integers.isSquare(current)) {
                boolean found = false;
                for (long prime : primes) {
                    if (prime == 2) {
                        // current - 2 cannot be even
                        continue;
                    }
                    long supposedSquare = (current - prime)/2;
                    if (Integers.isSquare(supposedSquare)) {
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    res = current;
                    break;
                }
            }
        }

        System.out.println(String.format("Pb0046: %d", res));
    }

    private static boolean isPrime(long nb, List<Long> primesCache) {
        if (primesCache.contains(nb)) {
            return true;
        }
        for (long prime : primesCache) {
            if (prime * prime > nb) {
                primesCache.add(nb);
                return true;
            }
            if (nb % prime == 0) {
                return false;
            }
        }
        return false;
    }
}