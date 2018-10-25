/**
 * https://projecteuler.net/problem=31
 */
public class Pb0031 {

    public static void main(String[] args) {
        long start = System.currentTimeMillis();

        int[] pieces = new int[] {200, 100, 50, 20, 10, 5, 2, 1};

        int ways = countCombinations(pieces, 0, 0, 200);

        System.out.println(String.format("Pb0031: %d in %d ms", ways,
                System.currentTimeMillis() - start));


    }

    private static int countCombinations(int[] pieces, int pieceIdx, int currentValue, int target) {
        if (currentValue == target) {
            return 1;
        }
        if (currentValue > target || pieceIdx > pieces.length-1) {
            return 0;
        }
        // see how many pieces of the current piece we can put
        int count = 0;
        for (int i=0; i <= target-currentValue / pieces[pieceIdx]; ++i) {
            count += countCombinations(pieces, pieceIdx+1, currentValue + i*pieces[pieceIdx], target);
        }
        return count;
    }

}