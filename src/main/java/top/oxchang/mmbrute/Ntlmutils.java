package top.oxchang.mmbrute;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import java.security.MessageDigest;
import java.security.Security;
import java.nio.charset.StandardCharsets;

public class Ntlmutils {

    static {
        Security.addProvider(new BouncyCastleProvider());
    }

    public static String v1encrypt(String password) {
        try {
            // 确保正确处理所有字符类型
            byte[] unicodePwd = password.getBytes(StandardCharsets.UTF_16LE);
            MessageDigest md = MessageDigest.getInstance("MD4");
            byte[] hash = md.digest(unicodePwd);
            return bytesToHex(hash);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "None";
    }

    private static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

    public static boolean verifyntlmv1(String ntlm,String pass){
        return ntlm.equals(v1encrypt(pass));
    }

    public static void main(String[] args) {
        System.out.println(Ntlmutils.v1encrypt("123456789@a"));
        System.out.println(verifyntlmv1("20B2A7351C899FD8E230CAAD9DD9B994","123456789a"));
    }

}
