/**
 * https://projecteuler.net/problem=24
 */
public class Pb0024 {

    public static void main(String[] args) {
        long start = System.currentTimeMillis();

        // there are 9!=362880 permutations of 9 digits
        // so the millionth starts with a 2 (permutations starting with 0 and 1 are passed)
        // 1_000_000 - 2*362_880 = 274_240th permutation of 8 digits
        // divide by 8! ==> second digit is a 7 and it remains 32_320
        // divide by 7! ==> third digit is a 8 and it remains 2_080
        // divide by 6! ==> fourth digit is a 3 and it remains 640
        // divide by 5! ==> fifth digit is a 9 and it remains 40
        // divide by 4! ==> sixth digit is a 1 and it remains 16
        // divide by 3! ==> seventh digit is a 5 and it remains 4
        // divide by 2! ==> eighth digit is a 4 and it remains 0
        // since it divided evenly, the rest is the last permutation of the remaining digits,  ie 6 then 0
        //
        // final result is thus 2783915460

        boolean[] digits = new boolean[10];
        int permutationNb = 1_000_000;
        int remainingDigits = 10;
        long res = 0;
        for (int i = 0; i < digits.length; ++i) {
            int nextDigitIdx = permutationNb / factorial(remainingDigits - 1);
            int remainder = permutationNb - (nextDigitIdx * factorial(remainingDigits - 1));
            permutationNb = remainder;
            if (remainder == 0) {
                // last permutation of what remains
                for (int j = digits.length - 1; j >= 0; --j) {
                    if (!digits[j]) {
                        res = (res * 10) + j;
                    }
                }
                break;
            } else {
                int notUsedIdx = 0;
                for (int j = 0; j < digits.length; ++j) {
                    if (!digits[j]) {
                        if (notUsedIdx == nextDigitIdx) {
                            digits[j] = true;
                            remainingDigits--;
                            res = (res * 10) + j;
                            break;
                        }
                        notUsedIdx++;
                    }
                }
            }
        }

        System.out.println(String.format("Pb0024: %s in %d ms", res,
                System.currentTimeMillis() - start));
    }

    private static int factorial(int n) {
        if (n == 1) {
            return 1;
        }
        return n * factorial(n - 1);
    }
}