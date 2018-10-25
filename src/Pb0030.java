/**
 * https://projecteuler.net/problem=30
 */
public class Pb0030 {

    public static void main(String[] args) {
        long start = System.currentTimeMillis();

        long sum = 0;
        // max 6 digits because 9^5 * 7 = 413343 so 7 digits numbers can't be reached
        for (int l = 10; l < 1_000_000; l++) {
            int current = l;
            int sumOfDigits = 0;
            while (current > 0) {
                int digit = current % 10;
                sumOfDigits += digit * digit * digit * digit * digit;
                current /= 10;
            }
            if (sumOfDigits == l) {
                sum += l;
            }

        }
        System.out.println(String.format("Pb0030: %d in %d ms", sum,
                System.currentTimeMillis() - start));


    }

}