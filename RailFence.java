public class RailFence {
    public static String encrypt(String pt, int rails) {
        pt = pt.toUpperCase();
        String ct = "";
        char[][] fence = new char[rails][pt.length()];
        int curRail = 0;
        boolean dir = true;
        for (int i = 0; i < pt.length(); i++) {
            fence[curRail][getNextIdx(fence[curRail])] = pt.charAt(i);
            if (dir) {
                if (curRail == rails - 1) {
                    dir = false;
                    curRail--;
                } else {
                    curRail++;
                }
            } else {
                if (curRail == 0) {
                    dir = true;
                    curRail++;
                } else {
                    curRail--;
                }
            }
        }
        for (char[] rail : fence) {
            for (char cipherchar : rail) {
                ct += cipherchar;
            }
        }
        return ct;
    }

    private static int getNextIdx(char[] rail) {
        for (int i = 0; i < rail.length; i++) {
            if (rail[i] == 0) {
                return i;
            }
        }
        return -1;
    }
}
