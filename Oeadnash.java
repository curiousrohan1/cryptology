import java.util.ArrayList;

public class Oeadnash {
    static char[] vowels = { 'A', 'E', 'I', 'O', 'U' };
    static char[] consonants = { 'B', 'C', 'D', 'F', 'G', 'H', 'J', 'K', 'L', 'M', 'N', 'P', 'Q', 'R', 'S', 'T', 'V',
            'W', 'X', 'Y', 'Z' };

    public static String encrypt(String pt, char key) {
        pt = RailFence.encrypt(pt, 2);
        int[] table = getTable(key);
        String ct = "";
        int shiftnumber = key - 'A';
        for (int i = 0; i < pt.length(); i++) {
            int idx = vowelIdx(pt.charAt(i));
            char cipherchar;
            if (idx >= 0) {
                idx += shiftnumber;
                idx %= 5;
                cipherchar = vowels[idx];
            } else {
                idx = consonantIdx(pt.charAt(i));
                idx += shiftnumber;
                idx %= 21;
                cipherchar = consonants[idx];
            }
            cipherchar = (char) table[cipherchar - 'A'];
            ct += cipherchar;
        }
        String finalCt = "";
        for (int i = 0; i < ct.length() - 1; i++) {
            int avg = (ct.charAt(i) + ct.charAt(i + 1)) / 2;
            finalCt += (char) avg;
        }
        return finalCt;
    }

    public static void main(String[] args) {
        System.out.println(encrypt("rohanese", 'R'));
    }

    private static int[] getTable(char key) {
        int[] table = new int[26];
        ArrayList<Integer> unusedChars = new ArrayList<>();
        for (int i = 0; i < 26; i++) {
            unusedChars.add(i);
        }
        for (int i = key - 'A'; i < table.length; i++) {
            Integer calc = (int) Math.pow(i, 2) % 26;
            if (findRepeat(calc, table) == -1) {
                unusedChars.remove(calc);
                table[i] = calc;
            } else {
                table[i] = unusedChars.get(0);
            }
        }
        for (int i = 0; i < key - 'A'; i++) {
            int calc = (int) Math.pow(i, 2) % 26;
            if (findRepeat(calc, table) == -1) {
                table[i] = calc;
            } else {
                table[i] = unusedChars.get(0);
            }
        }
        for (int character : table) {
            System.out.println((char) (character + 'A'));
        }
        return table;
    }

    private static int findRepeat(int calc, int[] table) {
        for (int i = 0; i < table.length; i++) {
            if (table[i] == calc) {
                return i;
            }
        }
        return -1;
    }

    private static int consonantIdx(char cons) {
        for (int i = 0; i < consonants.length; i++) {
            if (cons == consonants[i]) {
                return i;
            }
        }
        return -1;
    }

    private static int vowelIdx(char let) {
        for (int i = 0; i < vowels.length; i++) {
            if (let == vowels[i]) {
                return i;
            }
        }
        return -1;
    }
}
