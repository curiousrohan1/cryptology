public class Atbash {
    public static String encrypt(String pt) {
        pt = pt.toUpperCase();
        String ct = "";
        for (int i = 0; i < pt.length(); i++) {
            char cipherchar = pt.charAt(i);
            if (cipherchar >= 'A' && cipherchar <= 'Z') {
                cipherchar = (char) ('Z' - pt.charAt(i) + 'A');
            }
            ct += cipherchar;
        }
        return ct;
    }

    public static String decrypt(String ct) {
        ct = ct.toLowerCase();
        String pt = "";
        for (int i = 0; i < ct.length(); i++) {
            char plainchar = ct.charAt(i);
            if (plainchar >= 'a' && plainchar <= 'z') {
                plainchar = (char) ('z' - pt.charAt(i) + 'a');
            }
            pt += plainchar;
        }
        return pt;
    }
}
