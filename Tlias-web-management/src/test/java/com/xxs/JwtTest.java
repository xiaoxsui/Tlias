package com.xxs;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class JwtTest {

    //生成JWT令牌
    @Test
    public void testGenerate() {
        Map<String, Object> datamap = new HashMap<>();
        datamap.put("id", 1);
        datamap.put("username", "admin");
        String jwt = Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, "eGlhb3hpYW9zdWk=")   //指定加密算法、密钥
                .addClaims(datamap)   //添加自定义数据
                .setExpiration(new Date(System.currentTimeMillis() + 3600 * 1000))   //设置令牌有效期（当前时间戳+1小时）
                .compact();//生成令牌

        System.out.println(jwt);
    }

    //解析JWT令牌
    @Test
    public void testParse() {
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJpZCI6MSwidXNlcm5hbWUiOiJhZG1pbiIsImV4cCI6MTc2Njg1MTcyNn0.4BUuHLICOXY4xgWmpNzRqOp7GNQd0muAQw4LT-h8NtE";
        Claims claims = Jwts.parser()
                .setSigningKey("eGlhb3hpYW9zdWk=")   //指定密钥
                .parseClaimsJws(token)  //解析令牌
                .getBody();
        System.out.println(claims);
    }
}
