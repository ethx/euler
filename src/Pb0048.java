/**
 * https://projecteuler.net/problem=48
 */
public class Pb0048 {

    public static void main(String[] args) {
        // only need the last 10 digits of the solution
        final long RES_MODULO = 10_000_000_000L;

        long res = 0;
        for (int i = 1; i < 1_000; ++i) {
            long tmp = 1;
            for (int j = 0; j < i; ++j) {
                tmp = (tmp * i) % RES_MODULO;
            }
            res = (res + tmp) % RES_MODULO;
        }
        System.out.println(String.format("Pb0048: %d", res));
    }
}