package top.oxchang.mmbrute;

import io.jsonwebtoken.*;

import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Date;

public class Jwtutils {

    private static final String SECRET_KEY = "123456";
    private static final long EXPIRATION_TIME = 86400000; // 24小时

    public static String generateToken(String username) {
        byte[] keyBytes = SECRET_KEY.getBytes(StandardCharsets.UTF_8);
        SecretKeySpec key = new SecretKeySpec(keyBytes, "HmacSHA256");
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS256, key)
                .compact();
    }

    public static boolean validateToken(String token,String secret) {
        try {
            byte[] keyBytes = secret.getBytes(StandardCharsets.UTF_8);
            Jwts.parser()
                    .setSigningKey(keyBytes)
                    .parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static void main(String[] args) {
        System.out.println(validateToken("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyIjoiYWRtaW4iLCJpYXQiOjE3NTM3OTU4Njd9.-L4G_g3H8IeW0ZDQCsIbK9cfDs48rIktmnj2Gwmp6NY","123456"));
        System.out.println(validateToken("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyIjoiYWRtaW4iLCJpYXQiOjE3NTM3OTU4Njd9.-L4G_g3H8IeW0ZDQCsIbK9cfDs48rIktmnj2Gwmp6NY","1234567"));
        System.out.println(validateToken("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyIjoiYWRtaW4iLCJAYXQiOjE3NTM3OTU4Njd9.-L4G_g3H8IeW0ZDQCsIbK9cfDs48rIktmnj2Gwmp6NY","1234568"));
        System.out.println(validateToken("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyIjoiYWRtaW4iLCJpOXQiOjE3NTM3OTU4Njd9.-L4G_g3H8IeW0ZDQCsIbK9cfDs48rIktmnj2Gwmp6NY","123456"));
        System.out.println(validateToken("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyIjoiYWRAaW4iLCJpYXQiOjE3NTM3OTU4Njd9.-L4G_g3H8IeW0ZDQCsIbK9cfDs48rIktmnj2Gwmp6NY","123456"));
        System.out.println(validateToken("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyIjoiYWRtaW4iLCJpYXQiOjE3NTM3OTS4Njd9.-L4G_g3H8IeW0ZDQCsIbK9cfDs48rIktmnj2Gwmp6NY","1234567"));
        System.out.println(validateToken("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyIjoiYWRtaW4iLCJpYXQiOjE3NTM3OT04Njd9.-L4G_g3H8IeW0ZDQCsIbK9cfDs48rIktmnj2Gwmp6NY","123456"));
        System.out.println(validateToken("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyIjoiYWRtaW4iLCJpYXQiOjE3NT)3OTU4Njd9.-L4G_g3H8IeW0ZDQCsIbK9cfDs48rIktmnj2Gwmp6NY","123456"));
        System.out.println(validateToken("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyIjoiYWRtaW4iLCJpYXQiOjE3NTM3OTU4Njd9.-L4G_g3H8IeW0ZDQCsIbK9cfDs48rIktmnj2Gwmp6NY","123456"));
    }

}
