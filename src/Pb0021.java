import java.util.Set;

import utils.Integers;

/**
 * https://projecteuler.net/problem=21
 */
public class Pb0021 {

    public static void main(String[] args) {
        long start = System.currentTimeMillis();

        final int MAX = 10_000;
        long[] sumProperDivisors = new long[MAX - 1];

        // first pass to compute sum of proper divisors of every one
        for (int i = 1; i < MAX; ++i) {
            Set<Long> divisors = Integers.divisors(i);
            final int currentNb = i;
            long sum = divisors.stream().filter(d -> d < currentNb).reduce((a, b) -> a + b).orElse(0L);
            sumProperDivisors[i - 1] = sum;
        }

        // second pass to find amicable numbers
        long sum = 0;
        for (int i = 1; i < MAX; ++i) {
            long currentSum = sumProperDivisors[i - 1];
            if (currentSum > 0 && currentSum < MAX && sumProperDivisors[(int) currentSum - 1] == i && currentSum != i) {
                sum += i;
            }
        }

        System.out.println(String.format("Pb0021: %s in %d ms", sum,
                System.currentTimeMillis() - start));
    }
}