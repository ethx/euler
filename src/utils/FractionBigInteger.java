package utils;

import java.math.BigInteger;
import java.util.List;
import java.util.Objects;

public class FractionBigInteger {
    public final BigInteger numerator;
    public final BigInteger denominator;

    public FractionBigInteger(BigInteger numerator, BigInteger denominator) {
        this.numerator = numerator;
        this.denominator = denominator;
    }

    private static FractionBigInteger reduce(FractionBigInteger f) {
        BigInteger num = f.numerator;
        BigInteger denom = f.denominator;
        for (; ; ) {
            BigInteger gcd = num.gcd(denom);
            if (!gcd.equals(BigInteger.ONE)) {
                num = num.divide(gcd);
                denom = denom.divide(gcd);
            } else {
                break;
            }
        }
        return new FractionBigInteger(num, denom);
    }

    public static FractionBigInteger multiply(FractionBigInteger f1, FractionBigInteger f2) {
        BigInteger num = f1.numerator.multiply(f2.numerator);
        BigInteger denom = f1.denominator.multiply(f2.denominator);

        return reduce(new FractionBigInteger(num, denom));
    }

    public static FractionBigInteger add(FractionBigInteger f1, FractionBigInteger f2) {
        BigInteger num = f1.numerator.multiply(f2.denominator).add(f2.numerator.multiply(f1.denominator));
        BigInteger denom = f1.denominator.multiply(f2.denominator);

        return reduce(new FractionBigInteger(num, denom));
    }

    public static FractionBigInteger fromContinuedFraction(List<Long> input) {
        BigInteger num = BigInteger.ZERO;
        BigInteger denom = BigInteger.ONE;
        for (int idx = input.size() - 1; idx >= 0; --idx) {
            BigInteger oldNum = num;
            num = denom;
            denom = BigInteger.valueOf(input.get(idx)).multiply(denom).add(oldNum);
            // see if we can reduce
            for (; ; ) {
                BigInteger gcd = num.gcd(denom);
                if (!gcd.equals(BigInteger.ONE)) {
                    num = num.divide(gcd);
                    denom = denom.divide(gcd);
                } else {
                    break;
                }
            }
        }
        return new FractionBigInteger(denom, num); // swapped on purpose
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FractionBigInteger fraction = (FractionBigInteger) o;
        return numerator.equals(fraction.numerator) &&
                denominator.equals(fraction.denominator);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numerator, denominator);
    }
}
