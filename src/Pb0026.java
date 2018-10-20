import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * https://projecteuler.net/problem=26
 */
public class Pb0026 {

    public static void main(String[] args) {
        long start = System.currentTimeMillis();

        int res = 0;
        int longestCycle = 0;
        for (int i = 2; i < 1_000; ++i) {

            List<Integer> decimals = new ArrayList<>();
            int remainder = 1;
            // map remainder to digit, to easily have the cycle
            Map<Integer, Integer> remainders = new HashMap<>();
            for (; remainder != 0; ) {
                int nextDecimal = remainder * 10 / i;
                remainder = remainder * 10 - (nextDecimal * i);

                if (remainders.containsKey(remainder)) {
                    // cycle detected
                    int cycleLength = decimals.size() - remainders.get(remainder);
                    if (cycleLength > longestCycle) {
                        res = i;
                        longestCycle = cycleLength;
                    }
                    break;
                }

                decimals.add(nextDecimal);
                remainders.put(remainder, decimals.size() - 1);
            }
        }

        System.out.println(String.format("Pb0026: %d (cycle length %d) in %d ms", res, longestCycle,
                System.currentTimeMillis() - start));


    }

}