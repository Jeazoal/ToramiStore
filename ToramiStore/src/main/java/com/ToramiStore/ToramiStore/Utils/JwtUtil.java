package com.ToramiStore.ToramiStore.Utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Date;

@Component
public class JwtUtil {

    private static final SecretKey SECRET_KEY = Keys.hmacShaKeyFor(Base64.getDecoder().decode(
            "u5w8B71Wt2Q3FqY6J1KcG8zD4LmN9PvR5XyM2A6ZbV0T7YsH3CoKdJqL8E4XnZtC"
    ));

    public static String generateToken(String correo) {
        return Jwts.builder()
                .setSubject(correo)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // 10 horas de validez
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }


    public static String generateVerifyToken(String email) {
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1 * 60 * 1000)) // 5 minutos
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    public static String generateTokenPassword(String email) {
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 2 * 60 * 1000)) // ⏳ 2 minutos
                .signWith(SECRET_KEY, SignatureAlgorithm.HS256)
                .compact();
    }

    public static boolean validateToken(String token, String email) {
        try {
            Claims claims = decodeToken(token);
            String tokenEmail = claims.getSubject();
            Date expiration = claims.getExpiration();

            return tokenEmail.equals(email) && expiration.after(new Date());
        } catch (Exception e) {
            return false;
        }
    }

    public static String extractEmail(String token) {
        try {
            Claims claims = decodeToken(token);
            return claims.getSubject();
        } catch (Exception e) {
            return null;
        }
    }



    public static Claims decodeToken(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();
    }
}
