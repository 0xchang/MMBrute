package top.oxchang.mmbrute;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Sha2Calculator {
    public enum Algorithm {
        SHA256("SHA-256"),
        SHA512("SHA-512");

        private final String name;

        Algorithm(String name) {
            this.name = name;
        }
    }

    public static String calculate(String input, String algo) {
        Algorithm algorithm = null;
        if (algo.equals("sha256")) {
            algorithm = Algorithm.SHA256;
        } else if (algo.equals("sha512")) {
            algorithm = Algorithm.SHA512;
        } else {
            return "None";
        }
        try {
            MessageDigest md = MessageDigest.getInstance(algorithm.name);
            byte[] hashBytes = md.digest(input.getBytes());

            StringBuilder hexString = new StringBuilder();
            for (byte b : hashBytes) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(algorithm.name + "算法不可用", e);
        }
    }

    public static boolean verifysha2(String plain,String enc,String algo){
        return enc.equals(calculate(plain,algo));
    }

    public static void main(String[] args) {
        String testStr = "123456";
        System.out.println("SHA-256: " + calculate(testStr, "sha256"));
        System.out.println("SHA-512: " + calculate(testStr, "sha512"));
    }
}
