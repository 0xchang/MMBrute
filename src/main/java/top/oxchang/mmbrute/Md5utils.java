package top.oxchang.mmbrute;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Md5utils {
    public static String encrypt(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());

            StringBuilder hexString = new StringBuilder();
            for (byte b : messageDigest) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean verifymd5(String hash, String input) {
        return hash.equals(encrypt(input));
    }

    public static void main(String[] args) {
        String testString = "123456";
        System.out.println("MD5 hash of \"" + testString + "\": " + encrypt(testString));
    }
}
