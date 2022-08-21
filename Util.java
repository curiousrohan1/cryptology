import java.util.ArrayList;

public class Util {
    public static String removeRepeats(String keyword) {
        int i = 0;
        while (i < keyword.length()) {
            if (i != keyword.lastIndexOf(keyword.charAt(i))) {
                keyword = keyword.substring(0, i) + keyword.substring(i + 1);
            } else {
                i++;
            }
        }
        return keyword;
    }

    public static int mod(int a, int m) {
        if (gcd(a, m) != 1) {
            return -1;
        }
        int x;
        for (x = 1; x < m; x++) {
            if ((a * x) % m == 1) {
                break;
            }
        }
        return x;
    }

    public static int gcd(int r, int s) {
        while (s != 0) {
            int t = s;
            s = r % s;
            r = t;
        }
        return r;
    }

    public static String xor(String bin1, String bin2) {
        String xor = "";
        for (int i = 0; i < bin1.length(); i++) {
            if (bin1.charAt(i) == bin2.charAt(i)) {
                xor += '0';
            } else {
                xor += '1';
            }
        }
        return xor;
    }

    public static char xor(char bin1, char bin2) {
        if (bin1 == bin2) {
            return '0';
        } else {
            return '1';
        }
    }

    public static String binToString(String bin) {
        String ct = "";
        for (int i = 0; i < bin.length(); i += 5) {
            int cipherint = Integer.parseInt(bin.substring(i, i + 5), 2);
            char cipherchar = 0;
            if (cipherint < 26) {
                cipherchar = (char) (cipherint + 'A');
            } else {
                switch (cipherint) {
                    case 26: {
                        cipherchar = ' ';
                        break;
                    }
                    case 27: {
                        cipherchar = ',';
                        break;
                    }
                    case 28: {
                        cipherchar = '.';
                        break;
                    }
                    case 29: {
                        cipherchar = '/';
                        break;
                    }
                    case 30: {
                        cipherchar = '\'';
                        break;
                    }
                    case 31: {
                        cipherchar = '?';
                        break;
                    }
                }
            }
            ct += cipherchar;
        }
        return ct;
    }

    public static ArrayList<String> getDigraphs(String pt) {
        pt = pt.toUpperCase();
        ArrayList<String> digraphs = new ArrayList<>();
        for (int i = 0; i < pt.length(); i += 2) {
            if (i + 1 == pt.length()) {
                pt += 'X';
            }
            digraphs.add("" + pt.charAt(i) + pt.charAt(i + 1));
        }
        return digraphs;
    }

    public static String getBinRep(String pt) {
        String ct = "";
        pt = pt.toUpperCase();
        for (int i = 0; i < pt.length(); i++) {
            int let = 0;
            if (pt.charAt(i) >= 'A' && pt.charAt(i) <= 'Z') {
                let = pt.charAt(i) - 'A';
            } else {
                switch (pt.charAt(i)) {
                    case ' ': {
                        let = 26;
                        break;
                    }
                    case ',': {
                        let = 27;
                        break;
                    }
                    case '.': {
                        let = 28;
                        break;
                    }
                    case '/': {
                        let = 29;
                        break;
                    }
                    case '\'': {
                        let = 30;
                        break;
                    }
                    case '?': {
                        let = 31;
                        break;
                    }
                }
            }
            String cipherchar = Integer.toBinaryString(let);
            while (cipherchar.length() < 5) {
                cipherchar = '0' + cipherchar;
            }
            ct += cipherchar;
        }
        return ct;
    }
}
