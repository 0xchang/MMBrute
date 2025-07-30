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
        try{
            byte[] unicodePwd = password.toUpperCase()
                    .getBytes(StandardCharsets.UTF_16LE);
            MessageDigest md = MessageDigest.getInstance("MD4");
            byte[] hash = md.digest(unicodePwd);
            return bytesToHex(hash);
        }catch (Exception e){
            System.out.println(e.getMessage());
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
        System.out.println(Ntlmutils.v1encrypt("123456"));
        System.out.println(verifyntlmv1("32ed87bdb5fdc5e9cba88547376818d4","1234568"));
    }

}
