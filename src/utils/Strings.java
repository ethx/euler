package utils;

import java.util.HashSet;
import java.util.Set;

/**
 * Utility methods for strings
 */
public class Strings {

    private Strings() {
    }

    public static Set<String> getPermutations(String s) {
        boolean[] used = new boolean[s.length()];
        return getPermuations(s.toCharArray(), "", used);
    }

    private static Set<String> getPermuations(char[] initialChars, String current, boolean[] used) {
        if (current.length() == initialChars.length) {
            Set<String> res = new HashSet<>();
            res.add(current);
            return res;
        }
        Set<String> res = new HashSet<>();
        for (int i = 0; i < initialChars.length; ++i) {
            if (!used[i]) {
                used[i] = true;
                res.addAll(getPermuations(initialChars, current + initialChars[i], used));
                used[i] = false;
            }
        }
        return res;
    }
}
