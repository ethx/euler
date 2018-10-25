import java.util.HashSet;
import java.util.List;
import java.util.Set;

import utils.Integers;

/**
 * https://projecteuler.net/problem=32
 */
public class Pb0032 {

    public static void main(String[] args) {
        long start = System.currentTimeMillis();

        Set<Integer> products = new HashSet<>();
        LOOP_I:
        for (int i = 1; i < 10_000; ++i) {
            for (int j = 1; j < 10_000; ++j) {
                if (i*j > 10_000) {
                    continue LOOP_I;
                }
                List<Integer> digitsOfI = Integers.extractDigits(i);
                List<Integer> digitsOfJ = Integers.extractDigits(j);
                List<Integer> digitsOfProduct = Integers.extractDigits(i*j);
                if (digitsOfI.size() + digitsOfJ.size() + digitsOfProduct.size() == 9) {
                    Set<Integer> digits = new HashSet<>();
                    digits.addAll(digitsOfI);
                    digits.addAll(digitsOfJ);
                    digits.addAll(digitsOfProduct);
                    if (!digits.contains(0) && digits.size() == 9) {
                        products.add(i * j);
                    }
                }
            }
        }

        System.out.println(String.format("Pb0032: %d in %d ms", products.stream().reduce((a,b)->a+b).orElse(-1),
                System.currentTimeMillis() - start));


    }

}