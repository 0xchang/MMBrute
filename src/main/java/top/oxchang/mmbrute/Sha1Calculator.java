package top.oxchang.mmbrute;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Sha1Calculator {
    public static String calculateSha1(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            byte[] hashBytes = md.digest(input.getBytes());

            // 将字节数组转换为十六进制字符串
            StringBuilder hexString = new StringBuilder();
            for (byte b : hashBytes) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("SHA-1算法不可用", e);
        }
    }

    public static boolean verifysha1(String plain,String enc){
       return enc.equals(calculateSha1(plain));
    }

    public static void main(String[] args) {
        String testString = "123456";
        System.out.println("SHA-1哈希值: " + calculateSha1(testString));
    }
}
