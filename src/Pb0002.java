/**
 * https://projecteuler.net/problem=2
 */
public class Pb0002 {

    public static void main(String[] args) {
        int prevFibo = 1;
        int fibo = 2;
        long sum = 0;
        while (fibo <= 4_000_000) {
            // Add to the sum if even
            if (fibo % 2 == 0) {
                sum += fibo;
            }

            // compute next fibonacci number
            int tmp = fibo;
            fibo += prevFibo;
            prevFibo = tmp;
        }

        System.out.println(String.format("Pb0002: %d", sum));
    }
}