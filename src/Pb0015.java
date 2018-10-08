/**
 * https://projecteuler.net/problem=15
 */
public class Pb0015 {

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        final int GRID_SIZE = 21;

        long[][] possiblePaths = new long[GRID_SIZE][GRID_SIZE];
        possiblePaths[GRID_SIZE-1][GRID_SIZE-1] = 1;
        // browse line by line bottom to top, right to left
        for (int row = GRID_SIZE-1; row >= 0; row--) {
            for (int col = GRID_SIZE-1; col >= 0; col--) {
                if (row == GRID_SIZE-1 && col == GRID_SIZE-1) {
                    continue;
                }
                possiblePaths[row][col] =
                        (col < GRID_SIZE-1 ? possiblePaths[row][col + 1] : 0) + // go right
                                (row < GRID_SIZE-1 ? possiblePaths[row + 1][col] : 0); // go down
            }
        }

        System.out.println(String.format("Pb0015: %d in %d ms", possiblePaths[0][0], System.currentTimeMillis() - start));
        printGrid(possiblePaths);
    }

    private static void printGrid(long[][] grid) {
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid.length; col++) {
                System.out.print(String.format("%15d ", grid[row][col]));
            }
            System.out.println();
        }
    }
}