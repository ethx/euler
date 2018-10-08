import java.util.HashMap;
import java.util.Map;

/**
 * https://projecteuler.net/problem=14
 */
public class Pb0014 {

    public static void main(String[] args) {
        long start = System.currentTimeMillis();

        // only one million so can use a complete cache
        Map<Long, Integer> numberToChainLength = new HashMap<>();
        numberToChainLength.put(1L, 1);

        long res = 0;
        int maxLength = 0;
        for (int i = 1; i < 1_000_000; ++i) {
            int length = computeChainLength(i, numberToChainLength);
            if (length > maxLength) {
                maxLength = length;
                res = i;
            }
        }

        System.out.println(String.format("Pb0014: %s (chain length %d) in %d ms", res, maxLength, System.currentTimeMillis() - start));
    }

    private static int computeChainLength(long nb, Map<Long, Integer> cache) {
        if (cache.containsKey(nb)) {
            return cache.get(nb);
        }
        int length;
        if (nb % 2 == 0) {
            length = computeChainLength(nb / 2, cache) + 1;
        } else {
            length = computeChainLength(3 * nb + 1, cache) + 1;
        }
        cache.put(nb, length);
        return length;
    }

}