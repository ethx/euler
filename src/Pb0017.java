import java.util.HashMap;
import java.util.Map;

/**
 * https://projecteuler.net/problem=17
 */
public class Pb0017 {

    public static void main(String[] args) {
        long start = System.currentTimeMillis();

        final String HUNDRED = "hundred";
        Map<Integer, String> letters = new HashMap<>();
        letters.put(1, "one");
        letters.put(2, "two");
        letters.put(3, "three");
        letters.put(4, "four");
        letters.put(5, "five");
        letters.put(6, "six");
        letters.put(7, "seven");
        letters.put(8, "eight");
        letters.put(9, "nine");
        letters.put(10, "ten");
        letters.put(11, "eleven");
        letters.put(12, "twelve");
        letters.put(13, "thirteen");
        letters.put(14, "fourteen");
        letters.put(15, "fifteen");
        letters.put(16, "sixteen");
        letters.put(17, "seventeen");
        letters.put(18, "eighteen");
        letters.put(19, "nineteen");
        letters.put(20, "twenty");
        letters.put(30, "thirty");
        letters.put(40, "forty");
        letters.put(50, "fifty");
        letters.put(60, "sixty");
        letters.put(70, "seventy");
        letters.put(80, "eighty");
        letters.put(90, "ninety");
        for (int i = 1; i < 1000; ++i) {
            if (!letters.containsKey(i)) {
                String s = "";
                int hundreds = i / 100;
                int mod100 = i % 100;
                if (hundreds > 0) {
                    s = letters.get(hundreds) + HUNDRED;
                    if (mod100 != 0) {
                        s += "and";
                    }
                }
                if (letters.containsKey(mod100)) {
                    s += letters.get(mod100);
                } else if (mod100 > 0){
                    int tens = mod100/10;
                    int digit = mod100 % 10;
                    String mod100Str = letters.get(tens * 10);
                    if (digit > 0) {
                        mod100Str += letters.get(digit);
                    }
                    letters.put(mod100, mod100Str);
                    s += mod100Str;
                }
                letters.put(i, s);
            }
        }
        letters.put(1000, "onethousand");

        System.out.println(String.format("Pb0017: %d in %d ms", letters.values().stream().map(String::length).reduce((a, b)->a+b).orElse(-1),
                System.currentTimeMillis() - start));
    }
}