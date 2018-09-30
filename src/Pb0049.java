import java.util.HashSet;
import java.util.Set;

import utils.Primes;
import utils.Strings;

/**
 * https://projecteuler.net/problem=49
 */
public class Pb0049 {

    public static void main(String[] args) {
        Set<Long> primes = new HashSet<>(Primes.getPrimesUntil(10_000)); // max 4 digits primes

        for (long prime : primes) {
            String s = String.format("%04d", prime);
            Set<String> permutations = Strings.getPermutations(s);
            for (String permutation : permutations) {
                if (permutation.equals(s)) {
                    continue;
                }
                long perm = Long.parseLong(permutation, 10);
                if (!primes.contains(perm)) {
                    continue;
                }
                long diff = perm - prime;
                if (diff <= 0) {
                    continue;
                }
                long next = perm + diff;
                if (primes.contains(next) && permutations.contains(String.format("%04d", next))) {
                    System.out.println(String.format("Pb0049: %d %d %d", prime, perm, next));
                }

            }
        }
    }

}