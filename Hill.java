public class Hill {
    public static String encrypt(String pt, String keystring) {
        int[][] key = stringToMat(keystring);
        int[][] ciphermat = matMult(key, stringToMat(pt));
        for (int i = 0; i < ciphermat.length; i++) {
            for (int j = 0; j < ciphermat[0].length; j++) {
                ciphermat[i][j] %= 29;
            }
        }
        return matToString(ciphermat);
    }

    public static String decrypt(String ct, String keystring) {
        int[][] key = stringToMat(keystring);
        int[][] inverse = inverseMat(key);
        int[][] plainmat = matMult(inverse, stringToMat(ct));
        for (int i = 0; i < plainmat.length; i++) {
            for (int j = 0; j < plainmat[0].length; j++) {
                plainmat[i][j] %= 29;
            }
        }
        return matToString(plainmat);
    }

    private static int[][] inverseMat(int[][] mat) {
        int[][] inversed = new int[2][2];
        int determinant = mat[0][0] * mat[1][1] - mat[1][0] * mat[0][1];
        determinant %= 29;
        if (determinant < 0) {
            determinant += 29;
        }
        inversed[0][0] = mat[1][1];
        inversed[0][1] = (0 - mat[0][1]);
        inversed[1][0] = (0 - mat[1][0]);
        inversed[1][1] = mat[0][0];
        int invDeterminant = Util.mod(determinant, 29);
        for (int i = 0; i < inversed.length; i++) {
            for (int j = 0; j < inversed[0].length; j++) {
                inversed[i][j] *= invDeterminant;
                while (inversed[i][j] < 0) {
                    inversed[i][j] += 29;
                }
                inversed[i][j] %= 29;
            }
        }
        return inversed;
    }

    private static String matToString(int[][] mat) {
        String ct = "";
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < 2; j++) {
                ct += getCharRep(mat[j][i]);
            }
        }
        return ct;
    }

    private static char getCharRep(int cipherint) {
        if (cipherint < 26) {
            return (char) (cipherint + 'A');
        }
        switch (cipherint) {
            case 28: {
                return '.';
            }
            case 27: {
                return ',';
            }
            case 26: {
                return ' ';
            }
        }

        return '!';
    }

    private static int[][] stringToMat(String pt) {
        pt = pt.toUpperCase();
        if (pt.length() % 2 == 1) {
            pt += 'X';
        }
        int[][] plainmat = new int[2][pt.length() / 2];
        for (int i = 0; i < pt.length() / 2; i++) {
            for (int j = 0; j < 2; j++) {
                plainmat[j][i] = getIntRep(pt.charAt(i * 2 + j));
            }
        }
        return plainmat;
    }

    private static int getIntRep(char plainchar) {
        if (plainchar >= 'A' && plainchar <= 'Z') {
            return plainchar - 'A';
        }
        switch (plainchar) {
            case '.': {
                return 28;
            }
            case ',': {
                return 27;
            }
            case ' ': {
                return 26;
            }
        }

        return -1;
    }

    private static int[][] matMult(int[][] firstMatrix, int[][] secondMatrix) {
        int[][] result = new int[firstMatrix.length][secondMatrix[0].length];

        for (int row = 0; row < result.length; row++) {
            for (int col = 0; col < result[row].length; col++) {
                int cell = 0;
                for (int i = 0; i < secondMatrix.length; i++) {
                    cell += firstMatrix[row][i] * secondMatrix[i][col];
                }
                result[row][col] = cell;
            }
        }

        return result;
    }
}