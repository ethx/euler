import java.util.ArrayList;
import java.util.List;

import utils.Primes;

/**
 * https://projecteuler.net/problem=41
 */
public class Pb0041 {

    public static void main(String[] args) {
        long res = 0;

        for (int i = 9; i >= 1; --i) {
            boolean[] digits = new boolean[i+1];
            for (int d = 1; d < digits.length; d++) {
                digits[d] = true;
            }
            List<Long> permutations = generatePermutations(0, digits);
            for (Long p : permutations) {
                if (Primes.isPrime(p) && p > res) {
                    res = p;
                }
            }

            // no need to check further since will be inferior
            if (res > 0) {
                break;
            }
        }

        System.out.println(String.format("Pb0041: %d", res));
    }

    private static List<Long> generatePermutations(long current, boolean[] digits) {
        List<Long> res = new ArrayList<>();
        boolean digitRemaining = false;
        for (int i = 1; i < digits.length; i++) {
            if (digits[i]) {
                digitRemaining = true;
                digits[i] = false;
                res.addAll(generatePermutations(current * 10 + i, digits));
                digits[i] = true;
            }
        }
        if (!digitRemaining) {
            res.add(current);
            return res;
        }
        return res;
    }
}