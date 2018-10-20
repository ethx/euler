/**
 * https://projecteuler.net/problem=28
 */
public class Pb0028 {

    public static void main(String[] args) {
        long start = System.currentTimeMillis();

        final int SPIRAL_SIZE = 1001;
        int[][] spiral = new int[SPIRAL_SIZE][SPIRAL_SIZE];

        // build spiral starting top right
        int limitUp = 0;
        int limitLeft = 0;
        int limitRight = SPIRAL_SIZE-1;
        int limitDown = SPIRAL_SIZE-1;
        int row = 0;
        int col = SPIRAL_SIZE-1;
        int nb = SPIRAL_SIZE * SPIRAL_SIZE;
        OUTER:
        for (; ;) {
            // go left
            for (; ;) {
                spiral[row][col]=nb;
                --nb;
                if (nb == 0) {
                    break OUTER;
                }
                if (col!=limitLeft) {
                    --col;
                } else {
                    break;
                }
            }
            ++limitUp;
            ++row;
            // go down
            for (; ;) {
                spiral[row][col]=nb;
                --nb;
                if (row!=limitDown) {
                    ++row;
                } else {
                    break;
                }
            }
            ++limitLeft;
            ++col;
            // go right
            for (; ;) {
                spiral[row][col]=nb;
                --nb;
                if (col!=limitRight) {
                    ++col;
                } else {
                    break;
                }
            }
            --limitDown;
            --row;
            // go up
            for (; ;) {
                spiral[row][col]=nb;
                --nb;
                if (row!=limitUp) {
                    --row;
                } else {
                    break;
                }
            }
            --limitRight;
            --col;
        }

        // compute sum
        long sum = 0;
        for (int i = 0; i < SPIRAL_SIZE / 2; ++i) {
            sum += spiral[i][i];
            sum += spiral[SPIRAL_SIZE - 1 - i][i];
            sum += spiral[i][SPIRAL_SIZE - 1 - i];
            sum += spiral[SPIRAL_SIZE - 1 - i][SPIRAL_SIZE - 1 - i];
        }
        sum += spiral[SPIRAL_SIZE/2][SPIRAL_SIZE/2];

        //printGrid(spiral);
        System.out.println(String.format("Pb0028: %d in %d ms", sum,
                System.currentTimeMillis() - start));


    }

    private static void printGrid(int[][] grid) {
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid.length; col++) {
                System.out.print(String.format("%6d ", grid[row][col]));
            }
            System.out.println();
        }
    }

}