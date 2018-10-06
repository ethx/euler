import utils.Primes;

/**
 * https://projecteuler.net/problem=10
 */
public class Pb0010 {

    public static void main(String[] args) {
        System.out.println(String.format("Pb0010: %d",
                Primes.getPrimesUntil(2_000_000).stream().reduce((a, b) -> a + b).orElse(-1L)));

    }

}