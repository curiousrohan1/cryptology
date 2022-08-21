import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ItiTran {
    private static final Scanner input = new Scanner(System.in);
    private static final Map<Character, Character> chars = new HashMap<>();

    static {
        chars.put('a', 'E');
        chars.put('b', 'C');
        chars.put('c', 'D');
        chars.put('d', 'F');
        chars.put('e', 'I');
        chars.put('f', 'G');
        chars.put('g', 'H');
        chars.put('h', 'J');
        chars.put('i', 'O');
        chars.put('j', 'K');
        chars.put('k', 'L');
        chars.put('l', 'M');
        chars.put('m', 'N');
        chars.put('n', 'P');
        chars.put('o', 'U');
        chars.put('p', 'Q');
        chars.put('q', 'R');
        chars.put('r', 'S');
        chars.put('s', 'T');
        chars.put('t', 'V');
        chars.put('u', 'A');
        chars.put('v', 'W');
        chars.put('w', 'X');
        chars.put('x', 'Y');
        chars.put('y', 'Z');
        chars.put('z', 'B');
    }

    public static void main(String[] args) {
        String ans = "y";
        while (ans.equals("y")) {
            System.out.println("((/*￣︶￣)/ Hello! please enter a word to translate it to Itipejus.\\(￣︶￣*\\))");
            System.out.println(translateIti(input.nextLine()));
            System.out.println("Do you want to translate another?(y/n)");
            ans = input.nextLine();
        }
    }

    public static boolean isConsonant(final char letter) {
        return !(letter == 'a' || letter == 'e' || letter == 'i' || letter == 'o' || letter == 'u');
    }

    private static String translateIti(String eng) {
        char let;
        final StringBuilder itiBuild = new StringBuilder();
        for (int i = 0; i < eng.length(); i++) {
            let = eng.charAt(i);
            itiBuild.append(charTrans(Character.toLowerCase(let)));
            if (isConsonant(let) && i < eng.length() - 1) {
                if (isConsonant(eng.charAt(i + 1))) {
                    itiBuild.append('I');
                }
            }
            if (!isConsonant(let) && i < eng.length() - 1) {
                char nextLet = eng.charAt(i + 1);
                if (!isConsonant(nextLet)) {
                    itiBuild.append('R');
                }
            }
        }
        itiBuild.reverse();
        return itiBuild.toString();
    }

    private static char charTrans(char engChar) {
        if (engChar > 'z' || engChar < 'a') {
            return engChar;
        }
        return chars.get(engChar);
    }
}
