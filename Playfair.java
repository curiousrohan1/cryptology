import java.util.ArrayList;

public class Playfair {
    private static char[][] getGrid(String keyword) {
        char[][] grid = new char[5][5];
        keyword = Util.removeRepeats(keyword);
        keyword = keyword.toUpperCase();
        if (keyword.length() != 5) {
            System.err.println(
                    "Sorry, but that keyword didn't work; it was not 5 characters when repeating characters were removed.");
        } else {
            for (int i = 0; i < 5; i++) {
                grid[0][i] = keyword.charAt(i);
            }
            for (int i = 1; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    grid[i][j] = getNextLet(grid);
                }
            }
        }
        return grid;
    }

    public static String encrypt(String pt, String keyword) {
        keyword = keyword.toUpperCase();
        char[][] grid = getGrid(keyword);
        ArrayList<String> digraphs = Util.getDigraphs(pt);
        String ct = "";
        for (int i = 0; i < digraphs.size(); i++) {
            ct += encryptDig(digraphs.get(i), grid);
        }
        return ct;
    }

    public static String decrypt(String ct, String keyword) {
        keyword = keyword.toUpperCase();
        char[][] grid = getGrid(keyword);
        ArrayList<String> digraphs = Util.getDigraphs(ct);
        String pt = "";
        for (int i = 0; i < digraphs.size(); i++) {
            pt += decryptDig(digraphs.get(i), grid);
        }
        return pt;
    }

    private static String encryptDig(String dig, char[][] grid) {
        char one = dig.charAt(0);
        char two = dig.charAt(1);
        int onerow = findLetRow(grid, one);
        int tworow = findLetRow(grid, two);
        int onecol = findLetCol(grid, one);
        int twocol = findLetCol(grid, two);
        if (onerow == tworow) {
            one = grid[onerow][(onecol + 1) % 5];
            two = grid[tworow][(twocol + 1) % 5];
        } else if (onecol == twocol) {
            one = grid[(onerow + 1) % 5][onecol];
            two = grid[(tworow + 1) % 5][twocol];
        } else {
            one = grid[onerow][twocol];
            two = grid[tworow][onecol];
        }
        return "" + one + two;
    }

    private static String decryptDig(String dig, char[][] grid) {
        char one = dig.charAt(0);
        char two = dig.charAt(1);
        int onerow = findLetRow(grid, one);
        int tworow = findLetRow(grid, two);
        int onecol = findLetCol(grid, one);
        int twocol = findLetCol(grid, two);
        if (onerow == tworow) {
            if (onecol == 0) {
                onecol += 5;
            }
            if (twocol == 0) {
                twocol += 5;
            }
            one = grid[onerow][(onecol - 1) % 5];
            two = grid[tworow][(twocol - 1) % 5];
        } else if (onecol == twocol) {
            if (onerow == 0) {
                onerow += 5;
            }
            if (tworow == 0) {
                tworow += 5;
            }
            one = grid[(onerow - 1) % 5][onecol];
            two = grid[(tworow - 1) % 5][twocol];
        } else {
            one = grid[onerow][twocol];
            two = grid[tworow][onecol];
        }
        return "" + one + two;
    }

    private static int findLetRow(char[][] grid, char let) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (grid[i][j] == let) {
                    return i;
                }
                if (let == 'J') {
                    return findLetRow(grid, 'I');
                }
            }
        }
        return -1;
    }

    private static int findLetCol(char[][] grid, char let) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (grid[i][j] == let) {
                    return j;
                }
                if (let == 'J') {
                    return findLetCol(grid, 'I');
                }
            }
        }
        return -1;
    }

    private static char getNextLet(char[][] grid) {
        for (int i = 0; i < 26; i++) {
            char let = (char) (i + 'A');
            if (findLetRow(grid, let) == -1 && let != 'J') {
                return (char) (i + 'A');
            }
        }
        return '.';
    }
}
