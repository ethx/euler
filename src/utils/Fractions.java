package utils;

import java.util.ArrayList;

/**
 * Utility methods for fractions
 */
public class Fractions {

    private Fractions() {
    }

    /**
     * Compute the continuous fraction of sqrt(input).
     * Only for non square numbers.
     * https://en.wikipedia.org/wiki/Continued_fraction
     */
    public static ArrayList<Long> sqrtContinuedFraction(int input) {
        ArrayList<Long> result = new ArrayList<>();

        double sqrt = Math.sqrt(input);
        // find a0
        long a0 = (long) sqrt;
        result.add(a0);

        // next number is of the form (sqrt(input) + x) / y
        long x = a0;
        long y = input - (a0*a0);
        for (; ;) {
            long nextA = (long) ((sqrt + x)/y);
            result.add(nextA);
            if (nextA == 2*a0) {
                // period ends with 2*a0
                break;
            }
            // https://fr.wikipedia.org/wiki/Fraction_continue_d%27un_irrationnel_quadratique#Introduction_sur_un_exemple
            x = (y * nextA) - x;
            y = (input - x*x) / y;
        }
        return result;
    }

}
