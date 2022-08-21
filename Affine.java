public class Affine {
    public static String encrypt(String pt, int mult, int add) {
        pt = pt.toUpperCase();
        String ct = "";
        for (int i = 0; i < pt.length(); i++) {
            int cipherint = pt.charAt(i) - 'A';
            cipherint *= mult;
            cipherint += add;
            cipherint %= 29;
            char cipherchar = '!';
            switch (cipherint) {
                case 28: {
                    cipherchar = '.';
                    break;
                }
                case 27: {
                    cipherchar = ',';
                    break;
                }
                case 26: {
                    cipherchar = ' ';
                    break;
                }
                default: {
                    cipherchar = (char) (cipherint + 'A');
                    break;
                }
            }
            ct += cipherchar;
        }
        return ct;
    }

    public static String decrypt(String ct, int mult, int add) {
        ct = ct.toUpperCase();
        String pt = "";
        for (int i = 0; i < ct.length(); i++) {
            int plainint = ct.charAt(i) - 'A';
            plainint -= add;
            if (plainint < 0) {
                plainint += 29;
            }
            plainint *= Util.mod(mult, 29);
            plainint %= 29;
            char plainchar = '!';
            switch (plainint) {
                case 28: {
                    plainchar = '.';
                    break;
                }
                case 27: {
                    plainchar = ',';
                    break;
                }
                case 26: {
                    plainchar = ' ';
                    break;
                }
                default: {
                    plainchar = (char) (plainint + 'A');
                    break;
                }
            }
            pt += plainchar;
        }
        return pt;
    }
}
