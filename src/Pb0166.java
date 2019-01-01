import java.util.HashMap;
import java.util.Map;

/**
 * https://projecteuler.net/problem=166
 */
public class Pb0166 {

    public static void main(String[] args) {
        int[] grid3v3 = new int[3*3];

        Map<Integer, Integer> res = new HashMap<>();
        build3x3GridRecursively(grid3v3, 0, res);
        System.out.println(String.format("Pb0166: %d grids", res.values().stream().reduce((a,b)->a+b).orElse(-1)));

    }

    private static void build3x3GridRecursively(int[] grid, int position, Map<Integer, Integer> res) {
        // 3v3 grid is complete
        if (position >= grid.length) {
            // max possible total is with all 9
            for (int i=0; i <= 4*9; ++i) {
                if (check4v4grid(grid, i)) {
                    res.compute(i, (k,v)-> (v==null) ? 1 : v+1);
                }
            }
            return;
        }

        // build the 3v3 grid
        for (int i=0; i <= 9; ++i) {
            if (position == 0) {
                System.out.println("root level, trying " + i);
            }
            grid[position] = i;
            build3x3GridRecursively(grid, position+1, res);
        }
    }

    /**
     * See if we can increase the 3v3 to a 4v4 with the given total by line, column, diagonals.
     */
    private static boolean check4v4grid(int[] grid3x3, int totalByLine) {
        // bottom line
        int line1, line2, line3;
        // right column
        int col1, col2, col3;
        // bottom right diag
        int diag;

        col1 = totalByLine - (grid3x3[0] + grid3x3[1] + grid3x3[2]);
        if (col1 < 0 || col1 > 9) { return false; }
        col2 = totalByLine - (grid3x3[3] + grid3x3[4] + grid3x3[5]);
        if (col2 < 0 || col2 > 9) { return false; }
        col3 = totalByLine - (grid3x3[6] + grid3x3[7] + grid3x3[8]);
        if (col3 < 0 || col3 > 9) { return false; }

        line1 = totalByLine - (grid3x3[0] + grid3x3[3] + grid3x3[6]);
        if (line1 < 0 || line1 > 9) { return false; }
        line2 = totalByLine - (grid3x3[1] + grid3x3[4] + grid3x3[7]);
        if (line2 < 0 || line2 > 9) { return false; }
        line3 = totalByLine - (grid3x3[2] + grid3x3[5] + grid3x3[8]);
        if (line3 < 0 || line3 > 9) { return false; }

        diag = totalByLine - (grid3x3[0] + grid3x3[4] + grid3x3[8]);
        if (diag < 0 || diag > 9) { return false; }

        // check total on last column
        if (col1 + col2 + col3 + diag != totalByLine) {
            return false;
        }

        // check total on last line
        if (line1 + line2 + line3 + diag != totalByLine) {
            return false;
        }

        // check second diag
        if (line1 + grid3x3[7] + grid3x3[5] + col1 != totalByLine) {
            return false;
        }

        //System.out.println("found " + Arrays.toString(grid3x3) + " for total " + totalByLine);
        return true;
    }

}