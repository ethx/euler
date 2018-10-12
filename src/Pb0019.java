/**
 * https://projecteuler.net/problem=19
 */
public class Pb0019 {

    public static void main(String[] args) {
        long start = System.currentTimeMillis();

        int count = 0;
        int firstOfMonth = 365 % 7; // 0 is Monday
        for (int year = 1901; year< 2001; year++) {
            boolean leapYear = (year % 4 == 0) && (year % 100 != 0 || year % 400 == 0);
            // Jan
            count += (firstOfMonth == 6) ? 1 : 0;
            firstOfMonth = (firstOfMonth + 31) % 7;
            // Feb
            count += (firstOfMonth == 6) ? 1 : 0;
            firstOfMonth = (firstOfMonth + (leapYear ? 29 : 28)) % 7;
            // March
            count += (firstOfMonth == 6) ? 1 : 0;
            firstOfMonth = (firstOfMonth + 31) % 7;
            // April
            count += (firstOfMonth == 6) ? 1 : 0;
            firstOfMonth = (firstOfMonth + 30) % 7;
            // May
            count += (firstOfMonth == 6) ? 1 : 0;
            firstOfMonth = (firstOfMonth + 31) % 7;
            // June
            count += (firstOfMonth == 6) ? 1 : 0;
            firstOfMonth = (firstOfMonth + 30) % 7;
            // July
            count += (firstOfMonth == 6) ? 1 : 0;
            firstOfMonth = (firstOfMonth + 31) % 7;
            // August
            count += (firstOfMonth == 6) ? 1 : 0;
            firstOfMonth = (firstOfMonth + 31) % 7;
            // Sep
            count += (firstOfMonth == 6) ? 1 : 0;
            firstOfMonth = (firstOfMonth + 30) % 7;
            // Oct
            count += (firstOfMonth == 6) ? 1 : 0;
            firstOfMonth = (firstOfMonth + 31) % 7;
            // Nov
            count += (firstOfMonth == 6) ? 1 : 0;
            firstOfMonth = (firstOfMonth + 30) % 7;
            // Dec
            count += (firstOfMonth == 6) ? 1 : 0;
            firstOfMonth = (firstOfMonth + 31) % 7;
        }

        System.out.println(String.format("Pb0019: %d in %d ms", count,
                System.currentTimeMillis() - start));
    }
}