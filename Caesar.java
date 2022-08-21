/**
 * Caesar
 */
public class Caesar {

    public static String encrypt(String pt, char shiftLetter) {
        String ct = "";
        pt = pt.toUpperCase();
        for (int i = 0; i < pt.length(); i++) {
            char cipherchar = pt.charAt(i);
            if (cipherchar >= 'A' && cipherchar <= 'Z') {
                cipherchar = (char) (cipherchar + shiftLetter - 'A');
            }
            if (cipherchar > 'Z') {
                cipherchar -= 26;
            }
            ct += cipherchar;
        }
        return ct;
    }

    public static String decrypt(String ct, char shiftLetter) {
        String pt = "";
        ct = ct.toUpperCase();
        for (int i = 0; i < ct.length(); i++) {
            char plainchar = ct.charAt(i);
            if (plainchar >= 'A' && plainchar <= 'Z') {
                plainchar = (char) (plainchar - shiftLetter + 'A');
            }
            if (plainchar < 'A') {
                plainchar += 26;
            }
            pt += plainchar;
        }
        return pt;
    }
}