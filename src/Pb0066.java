import java.math.BigInteger;
import java.util.ArrayList;

import utils.FractionBigInteger;
import utils.Fractions;

/**
 * https://projecteuler.net/problem=66
 */
public class Pb0066 {

    public static void main(String[] args) {
        // x2 - Dy2 = 1
        // ==> x = sqrt(Dy2 + 1)
        // ==> if it exists, x = (sqrt(D) * y) + 1
        final int MAX_D = 1_000;

        int result = -1;
        BigInteger maxX = BigInteger.ZERO;
        for (int D = 2; D <= MAX_D; D++) {
            // no solution when D is a square
            if ((int) Math.sqrt(D) * (int) Math.sqrt(D) != D) {
                // https://fr.wikipedia.org/wiki/%C3%89quation_de_Pell-Fermat#Cas_m_=_1
                BigInteger x;
                BigInteger y;
                ArrayList<Long> continuedFrac = Fractions.sqrtContinuedFraction(D);
                int period = continuedFrac.size() - 1;
                if (period % 2 == 0) {
                    FractionBigInteger reducedFrac = FractionBigInteger.fromContinuedFraction(continuedFrac.subList(0, continuedFrac.size() - 1));
                    x = reducedFrac.numerator;
                    y = reducedFrac.denominator;
                } else {
                    for (int i = 1; i < period; ++i) {
                        continuedFrac.add(continuedFrac.get(i));
                    }
                    FractionBigInteger reducedFrac = FractionBigInteger.fromContinuedFraction(continuedFrac);
                    x = reducedFrac.numerator;
                    y = reducedFrac.denominator;
                }
                if (check(D, x, y)) {
                    if (x.compareTo(maxX) > 0) {
                        maxX = x;
                        result = D;
                    }
                    System.out.println(String.format("D=%d, x=%d, y=%d", D, x, y));
                } else {
                    throw new RuntimeException(String.format("invalid solution for D=%d, x=%d, y=%d", D, x, y));
                }

            }
        }
        System.out.println(String.format("Pb0066: %d (x=%d)", result, maxX));
    }

    private static boolean check(int D, BigInteger x, BigInteger y) {
        return x.multiply(x).equals(y.multiply(y).multiply(BigInteger.valueOf(D)).add(BigInteger.ONE));
    }
}