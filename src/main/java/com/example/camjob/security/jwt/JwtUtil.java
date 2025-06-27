package com.example.camjob.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.Map;

@Component
public class JwtUtil {
    private final Key secret;

    public JwtUtil(Key secret) {
        this.secret = secret;
    }

    public String generateToken(String email, String nickname) {
        return Jwts.builder()
                .claim("email", email)
                .claim("nickname", nickname)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24)) // 1일 유효
                .signWith(secret, SignatureAlgorithm.HS256)
                .compact();
    }

    public Map<String, Object> validateToken(String token) {
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(secret)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

            return claims;
        } catch (JwtException | IllegalArgumentException e) {
            return null;
        }
    }
}

