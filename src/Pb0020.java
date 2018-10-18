import java.math.BigInteger;

/**
 * https://projecteuler.net/problem=20
 */
public class Pb0020 {

    public static void main(String[] args) {
        long start = System.currentTimeMillis();

        BigInteger nb = BigInteger.ONE;
        for (int i=1; i<=100; ++i) {
            nb = nb.multiply(BigInteger.valueOf(i));
        }

        int sum = 0;
        for(;;) {
            BigInteger[] div = nb.divideAndRemainder(BigInteger.valueOf(10));
            sum += div[1].intValue();
            nb = div[0];
            if (nb.equals(BigInteger.ZERO)) {
                break;
            }
        }

        System.out.println(String.format("Pb0020: %s in %d ms", sum,
                System.currentTimeMillis() - start));
    }
}