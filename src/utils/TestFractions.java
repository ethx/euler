package utils;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TestFractions {

    public static void main(String[] args) {

        // Test sqrtContinuedFraction
        ArrayList<Long> res = Fractions.sqrtContinuedFraction(28);
        if (!res.equals(Stream.of(5L, 3L, 2L, 3L, 10L).collect(Collectors.toList())) ||
            !FractionBigInteger.fromContinuedFraction(res.subList(0, res.size()-1))
                    .equals(new FractionBigInteger(BigInteger.valueOf(127), BigInteger.valueOf(24)))) {
            throw new RuntimeException(String.format("invalid result for %d: %s", 28, res));
        }
        res = Fractions.sqrtContinuedFraction(19);
        if (!res.equals(Stream.of(4L, 2L, 1L, 3L, 1L, 2L, 8L).collect(Collectors.toList())) ||
                !FractionBigInteger.fromContinuedFraction(res.subList(0, res.size()-1))
                        .equals(new FractionBigInteger(BigInteger.valueOf(170), BigInteger.valueOf(39)))) {
            throw new RuntimeException(String.format("invalid result for %d: %s", 19, res));
        }
        res = Fractions.sqrtContinuedFraction(17);
        if (!res.equals(Stream.of(4L, 8L).collect(Collectors.toList())) ||
                !FractionBigInteger.fromContinuedFraction(res)
                        .equals(new FractionBigInteger(BigInteger.valueOf(33), BigInteger.valueOf(8)))) {
            throw new RuntimeException(String.format("invalid result for %d: %s", 17, res));
        }
        res = Fractions.sqrtContinuedFraction(29);
        if (!res.equals(Stream.of(5L, 2L, 1L, 1L, 2L, 10L).collect(Collectors.toList()))) {
            throw new RuntimeException(String.format("invalid result for %d: %s", 29, res));
        }
        System.out.println("sqrtContinuedFraction OK");
    }
}
