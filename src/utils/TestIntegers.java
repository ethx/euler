package utils;

import java.math.BigInteger;

public class TestIntegers {

    public static void main(String[] args) {

        // Test sqrtIntegral
        for (long i = 1; i < 1_000_000; ++i) {
            BigInteger res = Integers.sqrtIntegral(BigInteger.valueOf(i * i));
            if (!res.equals(BigInteger.valueOf(i))) {
                throw new RuntimeException(String.format("invalid result for %d: %s", i, res));
            }
        }
        for (long i = 1; i < 1_000_000; ++i) {
            BigInteger res = Integers.sqrtIntegral(BigInteger.valueOf(i));
            long realSqrt = (long) (Math.sqrt(i) + 0.5);
            boolean found = realSqrt * realSqrt == i;
            if (
                    (!found && !res.equals(Integers.NOT_SQUARE)) ||
                            (found && !res.equals(BigInteger.valueOf(realSqrt)))
            ) {
                throw new RuntimeException(String.format("invalid result for %d: %s", i, res));
            }
        }
        System.out.println("sqrtIntegral OK");
    }
}
