/**
 * https://projecteuler.net/problem=6
 */
public class Pb0006 {

    public static void main(String[] args) {
        final long max = 100;
        long result = 0;
        for (int i = 0; i <= max; ++i) {
            for (int j = i+1; j <= max; ++j) {
                result += (2 * i * j);
            }
        }

        System.out.println(String.format("Pb0006: %d", result));
    }

}
