import java.util.HashSet;
import java.util.Set;

/**
 * https://projecteuler.net/problem=9
 */
public class Pb0009 {

    public static void main(String[] args) {
        Set<Integer> squares = new HashSet<>();
        for (int i = 1; i < 500; ++i) {
            squares.add(i*i);
        }

        for (int a2 : squares) {
            for (int b2 : squares) {
                if (b2<a2) {
                    continue;
                }
                int c2 = a2 + b2;
                if (squares.contains(c2) && (Math.sqrt(a2) + Math.sqrt(b2) + Math.sqrt(c2)) == 1000) {
                    System.out.println(String.format("Pb0009: %d %d %d %f", a2, b2, c2, Math.sqrt(a2) * Math.sqrt(b2) * Math.sqrt(c2)));
                }
            }
        }
    }

}