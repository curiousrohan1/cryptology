public class Monoalph {
    public static char[] getAlph(String keyword, char keyletter) {
        char[] cipherbet = new char[26];
        keyword = keyword.toUpperCase();
        keyword = Util.removeRepeats(keyword);
        int k = 0;
        int j = keyletter - 'A';
        while (j < 26 && k < keyword.length()) {
            cipherbet[j] = keyword.charAt(k);
            k++;
            j++;
        }
        if (k >= keyword.length()) {
            while (j < 26) {
                cipherbet[j] = getNextLet(cipherbet);
                j++;
            }
            j = 0;
            while (j < keyletter - 'A') {
                cipherbet[j] = getNextLet(cipherbet);
                j++;
            }
        } else {
            j = 0;
            while (k < keyword.length()) {
                cipherbet[j] = keyword.charAt(k);
                k++;
                j++;
            }
            while (j < keyletter - 'A') {
                cipherbet[j] = getNextLet(cipherbet);
                j++;
            }
        }
        return cipherbet;
    }

    public static String encrypt(String pt, char[] cipherbet) {
        pt = pt.toUpperCase();
        String ct = "";
        for (int i = 0; i < pt.length(); i++) {
            ct += cipherbet[pt.charAt(i) - 'A'];
        }
        return ct;
    }

    public static String decrypt(String ct, char[] cipherbet) {
        ct = ct.toUpperCase();
        String pt = "";
        for (int i = 0; i < ct.length(); i++) {
            pt += getPlainchar(cipherbet, ct.charAt(i));
        }
        return pt;
    }

    private static char getPlainchar(char[] cipherbet, char cipherchar) {
        for (int i = 0; i < cipherbet.length; i++) {
            if (cipherbet[i] == cipherchar) {
                char plainchar = (char) (i + 'A');
                return plainchar;
            }
        }
        return '!';
    }

    private static int findLet(char[] cipherbet, char let) {
        for (int i = 0; i < cipherbet.length; i++) {
            if (cipherbet[i] == let) {
                return i;
            }
        }
        return -1;
    }

    private static char getNextLet(char[] cipherbet) {
        for (int i = 0; i < 26; i++) {
            char let = (char) (i + 'A');
            if (findLet(cipherbet, let) == -1) {
                return (char) (i + 'A');
            }
        }
        return '.';
    }
}
