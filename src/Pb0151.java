import java.math.BigInteger;

import utils.FractionBigInteger;

/**
 * https://projecteuler.net/problem=151
 */
public class Pb0151 {

    public static void main(String[] args) {
        FractionBigInteger res = explore(new Sheets(1, 1, 1, 1));

        System.out.println(String.format("Pb0151: %f (%d/%d)",
                (double)res.numerator.longValue() / res.denominator.longValue(),
                res.numerator, res.denominator));
    }

    private static FractionBigInteger explore(Sheets remaining) {
        FractionBigInteger res = new FractionBigInteger(BigInteger.ZERO, BigInteger.ONE);

        int remainingCount = remaining.a2 + remaining.a3 + remaining.a4 + remaining.a5;
        if (remainingCount == 1 && remaining.a5 == 1) {
            // don't count the last sheet
            return res;
        }

        // recursively browse picking possibilities
        if (remaining.a2 > 0) {
            FractionBigInteger a2Res = explore(new Sheets(remaining.a2-1, remaining.a3+1, remaining.a4+1, remaining.a5+1));
            res = FractionBigInteger.add(res, FractionBigInteger.multiply(a2Res,
                    new FractionBigInteger(BigInteger.valueOf(remaining.a2), BigInteger.valueOf(remainingCount))));
        }
        if (remaining.a3 > 0) {
            FractionBigInteger a3Res = explore(new Sheets(remaining.a2, remaining.a3-1, remaining.a4+1, remaining.a5+1));
            res = FractionBigInteger.add(res, FractionBigInteger.multiply(a3Res,
                    new FractionBigInteger(BigInteger.valueOf(remaining.a3), BigInteger.valueOf(remainingCount))));
        }
        if (remaining.a4 > 0) {
            FractionBigInteger a4Res = explore(new Sheets(remaining.a2, remaining.a3, remaining.a4-1, remaining.a5+1));
            res = FractionBigInteger.add(res, FractionBigInteger.multiply(a4Res,
                    new FractionBigInteger(BigInteger.valueOf(remaining.a4), BigInteger.valueOf(remainingCount))));
        }
        if (remaining.a5 > 0) {
            FractionBigInteger a5Res = explore(new Sheets(remaining.a2, remaining.a3, remaining.a4, remaining.a5-1));
            res = FractionBigInteger.add(res, FractionBigInteger.multiply(a5Res,
                    new FractionBigInteger(BigInteger.valueOf(remaining.a5), BigInteger.valueOf(remainingCount))));
        }

        if (remainingCount == 1) {
            res = FractionBigInteger.add(res, new FractionBigInteger(BigInteger.ONE, BigInteger.ONE));
        }

        return res;
    }

    private static class Sheets {
        private final int a2;
        private final int a3;
        private final int a4;
        private final int a5;

        private Sheets(int a2, int a3, int a4, int a5) {
            this.a2 = a2;
            this.a3 = a3;
            this.a4 = a4;
            this.a5 = a5;
        }
    }

}