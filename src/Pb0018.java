import java.util.ArrayList;
import java.util.List;

/**
 * https://projecteuler.net/problem=18
 */
public class Pb0018 {

    public static void main(String[] args) {
        long start = System.currentTimeMillis();

        String in = "75\n" +
                "95 64\n" +
                "17 47 82\n" +
                "18 35 87 10\n" +
                "20 04 82 47 65\n" +
                "19 01 23 75 03 34\n" +
                "88 02 77 73 07 63 67\n" +
                "99 65 04 28 06 16 70 92\n" +
                "41 41 26 56 83 40 80 70 33\n" +
                "41 48 72 33 47 32 37 16 94 29\n" +
                "53 71 44 65 25 43 91 52 97 51 14\n" +
                "70 11 33 28 77 73 17 78 39 68 17 57\n" +
                "91 71 52 38 17 14 91 43 58 50 27 29 48\n" +
                "63 66 04 68 89 53 67 30 73 16 69 87 40 31\n" +
                "04 62 98 27 23 09 70 98 73 93 38 53 60 04 23";
        String[] lines = in.split("\n");
        List<List<Integer>> parsed = new ArrayList<>();
        for(String line : lines) {
            List<Integer> l = new ArrayList<>();
            for (String nb : line.split(" ")) {
                l.add(Integer.parseInt(nb, 10));
            }
            parsed.add(l);
        }

        List<List<Integer>> total = new ArrayList<>();
        for (int row = 0; row < parsed.size(); ++row) {
            List<Integer> totalLine = new ArrayList<>();
            for (int col = 0; col < parsed.get(row).size(); ++col) {
                int currentVal = parsed.get(row).get(col);
                if (row == 0) {
                    totalLine.add(currentVal);
                } else {
                    totalLine.add(currentVal + Math.max(
                            col == 0 ? 0 : total.get(row-1).get(col-1),
                            col >= total.get(row-1).size() ? 0 : total.get(row-1).get(col)));
                }
            }
            total.add(totalLine);
        }

        total.forEach(l -> {
            l.forEach(nb -> System.out.print(nb + " "));
            System.out.println();
        });

        System.out.println(String.format("Pb0018: %d in %d ms", total.get(total.size()-1).stream().reduce(Math::max).orElse(-1),
                System.currentTimeMillis() - start));
    }
}