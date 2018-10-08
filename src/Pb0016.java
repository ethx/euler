import java.util.ArrayList;

/**
 * https://projecteuler.net/problem=16
 */
public class Pb0016 {

    public static void main(String[] args) {
        long start = System.currentTimeMillis();

        // right to left
        ArrayList<Integer> digits = new ArrayList<>();
        digits.add(1); // 2^0
        for (int i = 1; i <= 1000; i++) {
            ArrayList<Integer> newDigits = new ArrayList<>();
            int remainder = 0;
            for (int digit : digits) {
                int nextDigit = digit * 2 + remainder;
                remainder = nextDigit > 9 ? 1 : 0;
                newDigits.add(nextDigit%10);
            }
            if (remainder == 1) {
                newDigits.add(1);
            }
            digits = newDigits;
        }

        System.out.println(String.format("Pb0016: %d in %d ms", digits.stream().reduce((a, b)->a+b).orElse(-1),
                System.currentTimeMillis() - start));
    }
}