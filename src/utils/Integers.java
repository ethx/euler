package utils;

import java.math.BigInteger;
import java.util.HashSet;
import java.util.Set;

/**
 * Utility methods for integers
 */
public class Integers {

    /**
     * A square is either 0, 1, 4 or 9 modulo 16. Shift this number to the mod 16 of the number
     * and compare the first bit to 1 to know if this is the case.
     */
    private static final long SQUARES_MOD_16 = (1 << 0) | (1 << 1) | (1 << 4) | (1 << 9);
    public static final BigInteger NOT_SQUARE = BigInteger.valueOf(-1);

    private Integers() {
    }

    public static BigInteger sqrtIntegral(BigInteger input) {
        return sqrtIntegral(input, BigInteger.valueOf(100));
    }

    /**
     * Returns -1 if the input is not a square number. Otherwise returns the square root.
     */
    public static BigInteger sqrtIntegral(BigInteger input, BigInteger initialEstimate) {
        // fast exclusion of numbers with a bad modulo 16
        if ((
                (SQUARES_MOD_16 >> (input.intValue() & 0xF)) // don't care if input is truncated, we just want mod 16
                        & 1)
                != 1) {
            return NOT_SQUARE;
        }

        // Apply Newton's method to find the sqrt
        // f(x) = x2 - input
        // fPrime(x) = 2x
        Set<BigInteger> alreadyTried = new HashSet<>();
        BigInteger estimate = initialEstimate;
        for (int i = 0; i < 1_000; ++i) {
            BigInteger fEstimate = estimate.multiply(estimate).subtract(input);
            if (fEstimate.equals(BigInteger.ZERO)) {
                return estimate;
            }
            // not the answer, try another one
            alreadyTried.add(estimate);
            BigInteger fPrimeEstimate = estimate.add(estimate); // 2x
            BigInteger move = fEstimate.divide(fPrimeEstimate); // f(x)/f'(x)
            if (move.signum() != -1) {
                move = move.add(BigInteger.ONE);
            }
            estimate = estimate.subtract(move);
            if (alreadyTried.contains(estimate)) {
                return NOT_SQUARE;
            }
        }
        throw new RuntimeException("still not found after 1_000 iterations: " + input);
    }

    public static boolean isSquare(long in) {
        long sqrt = (long) Math.sqrt(in);
        return (sqrt*sqrt == in);
    }
}
