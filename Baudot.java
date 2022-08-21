public class Baudot {
    public static String encrypt(String pt, String seed, String rule) {
        String ct = Util.getBinRep(pt);
        seed = generateSeed(seed, ct.length(), rule);
        ct = Util.xor(ct, seed);
        ct = Util.binToString(ct);
        return ct;
    }

    private static String generateSeed(String seed, int length, String rule) {
        while (seed.length() < length) {
            char bin1 = seed.charAt(seed.length() - (rule.charAt(0) - '0'));
            char bin2 = seed.charAt(seed.length() - (rule.charAt(1) - '0'));
            seed += Util.xor(bin1, bin2);
        }
        return seed;
    }

    public static String decrypt(String ct, String seed, String rule) {
        String pt = Util.getBinRep(ct);
        seed = generateSeed(seed, pt.length(), rule);
        pt = Util.xor(pt, seed);
        pt = Util.binToString(pt);
        return pt;
    }
}
