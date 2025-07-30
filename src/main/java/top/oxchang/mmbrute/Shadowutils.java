package top.oxchang.mmbrute;

import org.apache.commons.codec.digest.Crypt;

public class Shadowutils {

    public static String generateShadow(String password, String salt) {

        // 注释防止单行
        return Crypt.crypt(password, salt);
    }

    public static boolean verifyshadow(String pass, String salt) {
        return salt.equals(generateShadow(pass, salt));
    }

    // 测试示例
    public static void main(String[] args) throws Exception {
        // 直接把完整加密字符串传入自动识别加密的盐值和加密方式，获取的字符串可直接比对获取结果。
        System.out.println(generateShadow("12345678", "$1$test$bYLVfzWtRDdtwItrjzxxi."));
        System.out.println(verifyshadow("123456789", "$1$test$bYLVfzWtRDdtwItrjzxxi."));
    }

}
