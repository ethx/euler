import java.util.Set;
import java.util.TreeSet;

import utils.Integers;

/**
 * https://projecteuler.net/problem=23
 */
public class Pb0023 {

    public static void main(String[] args) {
        long start = System.currentTimeMillis();

        final int MAX = 28123;

        // find all abundant numbers below the max
        Set<Integer> abundants = new TreeSet<>();
        for (int i = 1; i <= MAX; ++i) {
            Set<Long> divisors = Integers.divisors(i);
            final int current = i;
            if (divisors.stream().filter(d->d<current).reduce((a,b)->a+b).orElse(0L) > current) {
                abundants.add(current);
            }
        }

        long sum = 0;
        // find numbers that can't be written as a sum of 2 abundant nbs
        for (int i = 1; i <= MAX; ++i) {
            boolean found = false;
            for (Integer currentTry : abundants) {
                if (currentTry > i) {
                    // the set is ordered
                    break;
                }
                int remaining = i - currentTry;
                if (abundants.contains(remaining)) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                sum += i;
            }
        }

        System.out.println(String.format("Pb0023: %s in %d ms", sum,
                System.currentTimeMillis() - start));
    }
}