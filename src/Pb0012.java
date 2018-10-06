import utils.Integers;

/**
 * https://projecteuler.net/problem=12
 */
public class Pb0012 {

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        long currentSum = 0;
        for (long i = 1; ; ++i) {
            currentSum += i;
            // currentSum is i*(i+1)/2
            int nbDivisors = i%2 == 0 ?
                    Integers.divisors(i/2).size() * Integers.divisors(i+1).size() :
                    Integers.divisors(i).size() * Integers.divisors((i+1)/2).size();
            if (nbDivisors > 500) {
                System.out.println(String.format("Pb0012: %d in %d ms", currentSum, System.currentTimeMillis()-start));
                return;
            }
        }
    }

}