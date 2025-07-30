package top.oxchang.mmbrute;

import org.springframework.security.crypto.bcrypt.BCrypt;

public class Bcryutils {

    public static String encwithsalt(String plainText, String key) {
        // 注释防止单行
        return BCrypt.hashpw(plainText, key);
    }

    public static boolean verifybcry(String plainText, String key) {
        return key.equals(encwithsalt(plainText, key));
    }

    public static void main(String[] args) {
        // 可直接将密文传入当salt，会自动提取salt，加密结果和密文一样说明明文正确。
        System.out.println(encwithsalt("123456", "$2a$10$EY9T1qMaL/edr.ZYE2q5N.uxXcvXO5Szt9AlZD43YN6VMsqr57Ani"));
    }
}
