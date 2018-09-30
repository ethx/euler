import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * https://projecteuler.net/problem=47
 */
public class Pb0047 {

    public static void main(String[] args) {
        final int N = 4;

        long res = 0;
        int foundCount = 0;
        List<Long> primes = new ArrayList<>();
        primes.add(2L);
        for (long nextTry = 2; ;nextTry++) {
            if (hasNDistinctPrimeFactors(nextTry, N, primes)) {
                foundCount++;
                if (foundCount == N) {
                    res = nextTry - N + 1;
                    break;
                }
            } else {
                foundCount = 0;
            }
        }

        System.out.println(String.format("Pb0047: %d", res));
    }

    private static boolean hasNDistinctPrimeFactors(long nb, int N, List<Long> primesCache) {
        Set<Long> primeFactors = new HashSet<>((int)nb);

        for (long current = nb; current != 1;) {
            if (primesCache.contains(current)) {
                // last prime factor
                primeFactors.add(current);
                break;
            }
            for (long prime : primesCache) {
                if (prime * prime > current) {
                    // nothing divides the current number
                    primeFactors.add(current);
                    primesCache.add(current);
                    break;
                }
                if (current % prime == 0) {
                    primeFactors.add(prime);
                    current = current / prime;
                    break;
                }
            }
        }

        return primeFactors.size() == N;
    }
}