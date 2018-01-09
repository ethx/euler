/**
 * https://projecteuler.net/problem=3
 */
public class Pb0003 {

    public static void main(String[] args) {
        long input = 600851475143L;
        long largestPrimeFactor = 0;

        long cursor = 2; // first prime number
        while (cursor <= input) {
            if (input % cursor == 0) {
                // found a prime factor: if it wasn't a prime,
                // the input would have been already reduced previously
                largestPrimeFactor = cursor;
                // reduce the input by removing components of this prime factor
                while (input % cursor == 0) {
                    input /= cursor;
                }
            }
            cursor++;
        }

        System.out.println(String.format("Pb0003: %d", largestPrimeFactor));
    }

}
