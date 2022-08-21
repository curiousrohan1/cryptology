public class Vigenere {
    public static String encrypt(String pt, String keyword) {
        String ct = "";
        pt = pt.toUpperCase();// LEXI
        keyword = keyword.toUpperCase();// LOVE
        keyword = Util.removeRepeats(keyword);
        while (pt.length() > keyword.length()) {
            keyword += keyword;
        }
        for (int i = 0; i < pt.length(); i++) {
            char cipherchar = pt.charAt(i);
            char shiftLetter = keyword.charAt(i);
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

    public static String decrypt(String ct, String keyword) {
        String pt = "";
        ct = ct.toUpperCase();
        keyword = keyword.toUpperCase();
        keyword = Util.removeRepeats(keyword);
        while (ct.length() > keyword.length()) {
            keyword += keyword;
        }
        for (int i = 0; i < ct.length(); i++) {
            char plainchar = ct.charAt(i);
            char shiftLetter = keyword.charAt(i);
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
