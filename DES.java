import java.util.ArrayList;

public class DES {
    public static String encrypt(String pt, String key) {
        key = Util.getBinRep(key.toUpperCase());
        pt = pt.toUpperCase();
        String k1 = key.substring(0, 5);
        String k2 = key.substring(5);
        ArrayList<String> digraphs = Util.getDigraphs(pt);
        String ct = "";
        for (int i = 0; i < digraphs.size(); i++) {
            ct += encryptDig(digraphs.get(i), k1, k2);
        }
        return ct;
    }

    public static String decrypt(String ct, String key) {
        key = Util.getBinRep(key.toUpperCase());
        ct = ct.toUpperCase();
        String k1 = key.substring(0, 5);
        String k2 = key.substring(5);
        ArrayList<String> digraphs = Util.getDigraphs(ct);
        String pt = "";
        for (int i = 0; i < digraphs.size(); i++) {
            pt += decryptDig(digraphs.get(i), k1, k2);
        }
        return pt;
    }

    private static String f(String bin) {
        char x1 = Util.xor(bin.charAt(0), bin.charAt(1));
        char x2 = (char) ((bin.charAt(1) - '0') * (bin.charAt(2) - '0') + '0');
        char x3 = Util.xor(bin.charAt(2), bin.charAt(3));
        char x4 = (char) ((bin.charAt(3) - '0') * (bin.charAt(4) - '0') + '0');
        char x5 = Util.xor(bin.charAt(1), bin.charAt(4));
        return "" + x1 + x2 + x3 + x4 + x5;
    }

    private static String encryptDig(String dig, String k1, String k2) {
        String char1 = Util.getBinRep(dig.substring(0, 1));
        String char2 = Util.getBinRep(dig.substring(1));
        String encrypt1 = Util.xor(f(Util.xor(char2, k1)), char1);
        String encrypt2 = Util.xor(f(Util.xor(encrypt1, k2)), char2);
        return "" + Util.binToString(encrypt1) + Util.binToString(encrypt2);
    }

    private static String decryptDig(String dig, String k1, String k2) {
        String char1 = Util.getBinRep(dig.substring(0, 1));
        String char2 = Util.getBinRep(dig.substring(1));
        String encrypt2 = Util.xor(f(Util.xor(char1, k2)), char2);
        String encrypt1 = Util.xor(f(Util.xor(encrypt2, k1)), char1);
        return "" + Util.binToString(encrypt1) + Util.binToString(encrypt2);
    }

}
