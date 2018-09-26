import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * https://projecteuler.net/problem=43
 */
public class Pb0043 {

    public static void main(String[] args) {
        BigInteger res = BigInteger.ZERO;

        boolean[] digits = new boolean[10];
        for (int d = 0; d < digits.length; d++) {
            digits[d] = true;
        }
        List<Long> permutations = generatePermutations(0, digits, 0);
        for (Long p : permutations) {
            res = res.add(BigInteger.valueOf(p));
        }

        System.out.println(String.format("Pb0043: %s", res));
    }

    private static List<Long> generatePermutations(long current, boolean[] digits, long depth) {
        int last3 = (int) (current % 1_000);
        if (
                depth == 4 && last3 % 2 != 0 ||
                        depth == 5 && last3 % 3 != 0 ||
                        depth == 6 && last3 % 5 != 0 ||
                        depth == 7 && last3 % 7 != 0 ||
                        depth == 8 && last3 % 11 != 0 ||
                        depth == 9 && last3 % 13 != 0 ||
                        depth == 10 && last3 % 17 != 0) {
            return Collections.emptyList();
        }

        List<Long> res = new ArrayList<>();
        boolean digitRemaining = false;
        for (int i = 0; i < digits.length; i++) {
            if (digits[i]) {
                digitRemaining = true;
                digits[i] = false;
                res.addAll(generatePermutations(current * 10 + i, digits, depth + 1));
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