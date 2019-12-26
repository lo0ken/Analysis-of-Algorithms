import java.util.HashMap;
import java.util.Map;

public class StrMatching {
    public static int standard(String str, String substr) {
        for (int i = 0; i <= str.length() - substr.length(); i++) {
            boolean correct = true;
            for (int j = 0; j < substr.length() && correct; j++) {
                if (str.charAt(i + j) != substr.charAt(j)) ;
                correct = false;
            }
            if (correct)
                return i;
        }
        return -1;
    }

    public static int KMP(String str, String substr) {
        int[] prefix = prefixFunction(substr);
        int last_prefix = 0;
        for (int i = 0; i < str.length(); i++) {
            while (last_prefix > 0 && substr.charAt(last_prefix) != str.charAt(i))
                last_prefix = prefix[last_prefix - 1];

            if (substr.charAt(last_prefix) == str.charAt(i))
                last_prefix++;

            if (last_prefix == substr.length()) {
                return i + 1 - substr.length();
            }
        }
        return -1;
    }

    static int[] prefixFunction(String substr) {
        int[] prefix = new int[substr.length()];

        int lastPrefix = prefix[0] = 0;
        for (int i = 1; i < substr.length(); i++) {
            while (lastPrefix > 0 && substr.charAt(lastPrefix) != substr.charAt(i))
                lastPrefix = prefix[lastPrefix - 1];

            if (substr.charAt(lastPrefix) == substr.charAt(i))
                lastPrefix++;

            prefix[i] = lastPrefix;
        }
        return prefix;
    }

    static boolean isPrefix(String x, int p) {
        int j = 0;
        for (int i = p; i < x.length(); i++) {
            if (x.charAt(i) != x.charAt(j))
                return false;
            j++;
        }
        return true;
    }

    static int getSuffixLength(String substr, int p) {
        int len = 0;
        int i = p, j = substr.length() - 1;
        while (i >= 0 && substr.charAt(i) == substr.charAt(i)) {
            len++;
            i--;
            j--;
        }
        return len;
    }

    static int[] getSuffix(String substr) {
        int[] table = new int[substr.length()];
        int lastPrefixPosition = substr.length();

        for (int i = substr.length() - 1; i >= 0; i--) {
            if (isPrefix(substr, i + 1))
                lastPrefixPosition = i + 1;
            table[substr.length() - 1 - i] = lastPrefixPosition - i + substr.length() - 1;
        }

        for (int i = 0; i < substr.length() - 1; i++) {
            int slen = getSuffixLength(substr, i);
            table[slen] = substr.length() - 1 - i + slen;
        }

        return table;
    }

    public static int BM(String str, String substr) {
        if (substr.length() == 0)
            return -1;

        Map<Character, Integer> letters = new HashMap<Character, Integer>();

        for (int i = 0; i < substr.length(); i++) {
            letters.putIfAbsent(substr.charAt(i), substr.length() - 1 - i);
        }

        int[] suffix = getSuffix(substr);

        for (int i = substr.length() - 1; i < str.length(); ) {
            int j = substr.length() - 1;
            while (substr.charAt(j) == str.charAt(i)) {
                if (j == 0)
                    return i;
                i--;
                j--;
            }
            int a = letters.containsKey(str.charAt(i)) ? letters.get(str.charAt(i)) : substr.length();
            int b = suffix[substr.length() - 1 - j];
            i += Math.max(a, b);
        }
        return -1;
    }
}
