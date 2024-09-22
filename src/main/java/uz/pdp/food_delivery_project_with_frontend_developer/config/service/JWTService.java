package uz.pdp.food_delivery_project_with_frontend_developer.config.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Base64;
import java.util.Date;

@Component
public class JWTService {
    @Value("${spring.jwt.secretKey}")
    private String secretKey;

    @Value("${spring.jwt.accessTokenExpiredTime}")
    private Long accessTokenExpiration;

    @Value("${spring.jwt.refreshTokenExpiration}")
    private Long refreshTokenExpiration;

    public String accessToken(@NotNull String username) {

        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + accessTokenExpiration))
                .signWith(getKeyFromToken(username), SignatureAlgorithm.HS256)
                .compact();
    }

    public String refreshToken(@NotNull String username) {

        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + refreshTokenExpiration))
                .signWith(getKeyFromToken(username), SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean isValid(@NotNull String token) {
        try{
            getClaims(token);
            return isTokenExpired(token);
        }
        catch (Exception e){
            return false;
        }
    }

    public String getUsername(@NotNull String token) {
        return getClaims(token)
                .getSubject();
    }

    private boolean isTokenExpired(@NotNull String token) {
        return getClaims(token).getExpiration().after(new Date());
    }

    private Claims getClaims(@NotNull String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getKeyFromToken(token))
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getKeyFromToken(@NotNull String token) {
        byte[] decodedKey = Base64
                .getDecoder()
                .decode(secretKey);
        return new SecretKeySpec(decodedKey, 0, decodedKey.length, "HmacSHA256");
    }

}