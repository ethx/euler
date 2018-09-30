import java.util.List;

import utils.Primes;

/**
 * https://projecteuler.net/problem=50
 */
public class Pb0050 {

    public static void main(String[] args) {
        List<Long> primes = Primes.getPrimesUntil(1_000_000);
        int most = 0;
        long res = 0;
        // not optimal at all, we keep computing the same sums over and over
        for (int i=0; i < primes.size(); ++i) {
            long target = primes.get(i);
            // try starting at every prime below self
            for (int j=0; j < i; ++j) {
                // sum until we reach or get bigger
                long sum = 0;
                int count = 0;
                for (int k=j; sum <= target; ++k) {
                    if (sum == target) {
                        if (count > most) {
                            most = count;
                            res = target;
                        }
                        break;
                    }
                    sum += primes.get(k);
                    count++;
                }
            }
        }
        System.out.println(String.format("Pb0050: %d", res));
    }
}