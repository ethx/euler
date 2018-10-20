package utils;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class TestPrimes {

    public static void main(String[] args) {

        // Test prime generation
        List<Long> primes1 = Primes.getPrimesUntil(1_000_000);
        Set<Integer> primes2 = Primes.getPrimesUntilFast(1_000_000);
        if (primes2.size() != primes1.size() || !primes1.stream().map(Long::intValue).collect(Collectors.toSet()).containsAll(primes2)) {
            throw new RuntimeException("bad prime generation");
        }
        System.out.println("prime generation OK");
    }
}
