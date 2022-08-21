
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class MD5 {

    private static final Charset UTF_8 = StandardCharsets.UTF_8;

    private static byte[] digest(byte[] input) throws NoSuchAlgorithmException {
        MessageDigest md;
        md = MessageDigest.getInstance("MD5");
        byte[] result = md.digest(input);
        return result;
    }

    private static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

    public static void main(String[] args) throws NoSuchAlgorithmException {
        Scanner input = new Scanner(System.in);
        System.out.println("Welcome. Enter what you want to encrypt using the MD5 hash:");
        String pText = input.nextLine();
        input.close();
        byte[] md5InBytes = digest(pText.getBytes(UTF_8));
        System.out.println(bytesToHex(md5InBytes));
        System.out.println(md5InBytes.length);

    }

}
