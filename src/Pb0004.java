/**
 * https://projecteuler.net/problem=4
 */
public class Pb0004 {

    public static void main(String[] args) {
        long largestPalindrome = 0;
        for (int nb1 = 100; nb1 < 1000; nb1++) {
            for (int nb2 = 100; nb2 < 1000; nb2++) {
                long product = nb1 * nb2;
                if (isPalindrome(product) && product > largestPalindrome) {
                    largestPalindrome = product;
                }
            }
        }

        System.out.println(String.format("Pb0004: %d", largestPalindrome));
    }

    /**
     * Check if the given number is a palindrome in base 10
     */
    private static boolean isPalindrome(long nb) {
        // kludgy but simple
        String serialized = Long.toString(nb, 10);
        int length = serialized.length();
        for (int i = 0; i < length / 2; ++i) {
            if (serialized.charAt(i) != serialized.charAt(length - 1 - i)) {
                return false;
            }
        }
        return true;
    }
}
