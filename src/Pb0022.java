import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

/**
 * https://projecteuler.net/problem=22
 */
public class Pb0022 {

    public static void main(String[] args) throws IOException {
        long start = System.currentTimeMillis();

        final List<String> input = Arrays.asList(Files.readAllLines(Paths.get("src/pb0022.txt")).get(0).split(","));

        input.sort((o1, o2) -> {
            if (o1.equals(o2)) {
                return 0;
            }
            char[] chars1 = o1.toCharArray();
            char[] chars2 = o2.toCharArray();
            for (int i = 0; i < chars1.length; ++i) {
                if (chars2.length <= i) {
                    // o2 starts with the same and is shorter: it is smaller
                    return 1;
                }
                if (chars1[i]<chars2[i]) {
                    return -1;
                } else if(chars1[i]>chars2[i]) {
                    return 1;
                }
            }
            // o1 starts with the same and is shorter: it is smaller
            return -1;
        });

        long sum = 0;
        for(int i = 0; i < input.size(); ++i) {
            int wordValue = 0;
            for(char c : input.get(i).toCharArray()) {
                int charValue = c-'A'+1;
                wordValue += charValue;
            }
            sum += (wordValue * (i+1));
        }
        System.out.println(String.format("Pb0022: %s in %d ms", sum,
                System.currentTimeMillis() - start));
    }
}