import java.math.BigInteger;
import java.util.HashSet;
import java.util.Set;

/**
 * https://projecteuler.net/problem=29
 */
public class Pb0029 {

    public static void main(String[] args) {
        long start = System.currentTimeMillis();

        Set<BigInteger> res = new HashSet<>();
        for(int a=2; a< 101; ++a) {
            for(int b=2; b< 101; ++b) {
                res.add(BigInteger.valueOf(a).pow(b));
            }
        }

        System.out.println(String.format("Pb0029: %d in %d ms", res.size(),
                System.currentTimeMillis() - start));


    }

}