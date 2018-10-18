import java.math.BigInteger;

/**
 * https://projecteuler.net/problem=25
 */
public class Pb0025 {

    public static void main(String[] args) {
        long start = System.currentTimeMillis();

        BigInteger currentValue = BigInteger.ONE;
        BigInteger prevValue = BigInteger.ONE;
        for (long idx = 3; ; idx++) {
            BigInteger newValue = currentValue.add(prevValue);
            prevValue = currentValue;
            currentValue = newValue;
            if (currentValue.toString(10).length() >= 1000 ) {
                System.out.println(String.format("Pb0025: %d in %d ms", idx,
                        System.currentTimeMillis() - start));
                return;
            }
        }


    }

}