package com.xxs.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.Map;

/**
 * JWT 工具类
 */
public class JwtUtils {

    /** 密钥（与你测试类中的保持一致） */
    private static final String SECRET_KEY = "eGlhb3hpYW9zdWk=";

    /** 过期时间（1小时，单位：毫秒） */
    private static final long EXPIRATION_TIME = 12 * 3600 * 1000;

    /**
     * 生成 JWT 令牌
     *
     * @param claims 自定义载荷数据
     * @return JWT 字符串
     */
    public static String generateToken(Map<String, Object> claims) {
        return Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY) // 指定算法和密钥
                .addClaims(claims)                              // 自定义数据
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME)) // 过期时间
                .compact();
    }

    /**
     * 解析 JWT 令牌
     *
     * @param token JWT 字符串
     * @return Claims（载荷数据）
     */
    public static Claims parseToken(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)   // 指定密钥
                .parseClaimsJws(token)       // 解析
                .getBody();
    }
}
