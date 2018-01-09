/**
 * https://projecteuler.net/problem=5
 */
public class Pb0005 {

    public static void main(String[] args) {
        long result = 1;
        OUTER:
        for (; ; ++result) {
            for (int i = 1; i <= 20; ++i) {
                if (result % i != 0) {
                    continue OUTER;
                }
            }
            break;
        }

        System.out.println(String.format("Pb0005: %d", result));
    }

}
