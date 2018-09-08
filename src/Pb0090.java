import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * https://projecteuler.net/problem=90
 */
public class Pb0090 {

    public static void main(String[] args) {
        final int[] squares = {1, 4, 9, 16, 25, 36, 49, 64, 81};

        // Maybe simpler to just try all arrangements
        // Here we build the solutions recursively
        List<Result> results = generateWithCubes(squares, 0, new Cube(), new Cube());

        results.forEach(r -> {
            check(r, squares);
            System.out.println(r.cube1.values + " -- " + r.cube2.values);
        });

        // Remove dups
        List<Result> withoudDups = new ArrayList<>();
        results.forEach(r -> {
            boolean alreadyHere = false;
            for (Result r2 : withoudDups) {
                if ((r2.cube1.equals(r.cube1) && r2.cube2.equals(r.cube2)) ||
                        (r2.cube2.equals(r.cube1) && r2.cube1.equals(r.cube2))
                        ) {
                    alreadyHere = true;
                    break;
                }
            }
            if (!alreadyHere) {
                withoudDups.add(r);
            }
        });
        System.out.println(String.format("Pb0090: %d", withoudDups.size()));
    }

    /**
     * Check if the solution is valid
     */
    private static void check(Result r, int[] numbers) {
        for (int number : numbers) {
            int leftDigit = number < 10 ? 0 : number / 10;
            int rightDigit = number % 10;

            if (!(
                    (r.cube1.contains(leftDigit) && r.cube2.contains(rightDigit)) ||
                            (r.cube1.contains(rightDigit) && r.cube2.contains(leftDigit))
            )) {
                throw new RuntimeException("invalid solution");
            }
        }
    }

    /**
     * Recursively go through the numbers to generate to build the solutions
     */
    private static List<Result> generateWithCubes(int[] numbers, int idx, Cube cube1, Cube cube2) {
        // Base case: we have a solution, just fill the 2 cubes up to 6 digits each
        if (idx >= numbers.length) {
            List<Result> results = new ArrayList<>();

            // fill cube to 6 digits
            List<Cube> cube1Filled = cube1.fill();
            List<Cube> cube2Filled = cube2.fill();
            cube1Filled.forEach(c1 -> cube2Filled.forEach(c2 -> results.add(new Result(c1, c2))));
            return results;
        }

        int number = numbers[idx];
        int leftDigit = number < 10 ? 0 : number / 10;
        int rightDigit = number % 10;

        List<Result> results = new ArrayList<>();
        // 2 ways to generate this number: left digit to cube 1 or to cube 2, right digit to the other cube
        if ((cube1.contains(leftDigit) || !cube1.isFull()) &&
                (cube2.contains(rightDigit) || !cube2.isFull())) {
            List<Cube> cube1Candidates = cube1.add(leftDigit);
            List<Cube> cube2Candidates = cube2.add(rightDigit);

            cube1Candidates.forEach(c1 -> cube2Candidates.forEach(c2 -> results.addAll(generateWithCubes(numbers, idx + 1, c1, c2))));
        }

        if ((cube1.contains(rightDigit) || !cube1.isFull()) &&
                (cube2.contains(leftDigit) || !cube2.isFull())) {
            List<Cube> cube1Candidates = cube1.add(rightDigit);
            List<Cube> cube2Candidates = cube2.add(leftDigit);

            cube1Candidates.forEach(c1 -> cube2Candidates.forEach(c2 -> results.addAll(generateWithCubes(numbers, idx + 1, c1, c2))));
        }

        return results;
    }

    private static class Cube {
        private Set<Integer> values = new HashSet<>();

        boolean contains(int number) {
            for (int value : values) {
                if (number == value ||
                        number == 6 && value == 9 ||
                        number == 9 && value == 6) {
                    return true;
                }
            }
            return false;
        }

        boolean reallyContains(int number) {
            for (int value : values) {
                if (number == value) {
                    return true;
                }
            }
            return false;
        }

        boolean isFull() {
            return values.size() == 6;
        }

        /**
         * Returns a list because possibly 2 ways if 6 or 9
         */
        List<Cube> add(int number) {
            if (number != 6 && number != 9) {
                if (!contains(number)) {
                    Cube res = this.copy();
                    if (res.isFull()) {
                        throw new RuntimeException("cannot add because the cube is full");
                    }
                    res.values.add(number);
                    return Collections.singletonList(res);
                }
                return Collections.singletonList(this);
            } else {
                List<Cube> resList = new ArrayList<>();
                for (int i : new int[]{6, 9}) {
                    if (!reallyContains(i)) {
                        Cube res = this.copy();
                        if (!res.isFull()) {
                            res.values.add(i);
                            resList.add(res);
                        }
                    } else {
                        resList.add(this);
                    }
                }
                return resList;
            }
        }

        private Cube copy() {
            Cube copy = new Cube();
            copy.values.addAll(values);
            return copy;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Cube cube = (Cube) o;

            return values != null ? values.equals(cube.values) : cube.values == null;
        }

        @Override
        public int hashCode() {
            return values != null ? values.hashCode() : 0;
        }

        /**
         * Recursively fill to 6 digits
         */
        List<Cube> fill() {
            if (values.size() == 6) {
                return Collections.singletonList(this);
            }
            List<Cube> res = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                if (!reallyContains(i)) {
                    Cube c = copy();
                    c.values.add(i);
                    res.addAll(c.fill());
                }
            }
            return res;
        }


    }

    private static class Result {
        Cube cube1;
        Cube cube2;

        Result(Cube cube1, Cube cube2) {
            this.cube1 = cube1;
            this.cube2 = cube2;
        }

    }
}