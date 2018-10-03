import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import utils.Primes;

/**
 * https://projecteuler.net/problem=51
 */
public class Pb0051 {

    public static void main(String[] args) {
        final int primeFamilySize = 8;

        // Generate possible indexes for each length
        Map<Integer, List<int[]>> indexPossibilities = initIndexPossibilities(6);

        List<Long> primes = Primes.getPrimesUntil(1_000_000);
        Set<Long> primesSet = new HashSet<>(primes);
        for (long prime : primes) {
            byte[] primeDigits = extractDigits(prime);

            // see if an indexes pattern works
            INDEXES:
            for (int[] indexes : indexPossibilities.get(primeDigits.length)) {
                // if the original prime doesnt belong to the family, stop
                int digit = -1;
                for (int index : indexes) {
                    if (digit == -1) {
                        digit = primeDigits[index];
                    } else if(primeDigits[index] != digit) {
                        continue INDEXES;
                    }
                }
                Set<Long> family = new HashSet<>();
                family.add(prime);
                for (byte d = 0; d < 10; d++) {
                    long maybePrime = replaceDigits(primeDigits, d, indexes);
                    // get rid of numbers starting with 0s since it doesn't seem to be in the pb
                    if ((int)Math.log10(maybePrime) != (int)Math.log10(prime)) {
                        continue;
                    }
                    if (primesSet.contains(maybePrime)) {
                        family.add(maybePrime);
                        if (family.size() == primeFamilySize) {
                            System.out.println(String.format("Pb0051: %d with indexes %s %s", prime, Arrays.toString(indexes), family));
                            return;
                        }
                    }
                }
            }
        }
    }

    private static Map<Integer, List<int[]>> initIndexPossibilities(int maxDigits) {
        Map<Integer, List<int[]>> res = new HashMap<>();
        for (int i = 1; i <= maxDigits; i++) {
            res.put(i, initIndexPossibilitiesForGivenSize(i));
        }
        return res;
    }

    private static List<int[]> initIndexPossibilitiesForGivenSize(int size) {
        List<int[]> res = new ArrayList<>();
        for (int i = 1; i <= size; ++i) {
            int[] indexes = new int[i];
            for (int j = 0; j < indexes.length; ++j) {
                indexes[j] = -1;
            }
            res.addAll(initIndexPossibilitiesRecursive(indexes, 0, new HashSet<>(), size));
        }
        return res;
    }

    private static List<int[]> initIndexPossibilitiesRecursive(int[] indexes, int next, Set<Integer> used, int size) {
        if (next >= indexes.length) {
            return Collections.singletonList(indexes.clone());
        }

        List<int[]> res = new ArrayList<>();
        for (int j = 0; j < size; ++j) {
            if (!used.contains(j)) {
                indexes[next] = j;
                used.add(j);
                res.addAll(initIndexPossibilitiesRecursive(indexes, next + 1, used, size));
                used.remove(j);
            }
        }

        return res;
    }


    private static byte[] extractDigits(long nb) {
        byte[] digits = new byte[((int) Math.log10(nb)) + 1];
        for (int i = 0; i < digits.length; ++i) {
            digits[i] = (byte) (nb % 10);
            nb /= 10;
        }
        return digits;
    }

    private static long fromDigits(byte[] digits) {
        long res = 0;
        for (int i = digits.length - 1; i >= 0; --i) {
            res *= 10;
            res += digits[i];
        }
        return res;
    }

    private static long replaceDigits(byte[] nb, byte newDigit, int[] indexes) {
        byte[] copy = nb.clone();
        for (int index : indexes) {
            copy[index] = newDigit;
        }
        return fromDigits(copy);
    }
}