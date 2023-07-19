package com.example.hr_managament.security;

import io.jsonwebtoken.*;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtProvider {

    private final Long expireTime = 365 * 24 * 3600 * 1000L;
    private final String SECRET_KEY = "mySecretKey";

    public String generateToken(String username) {
        return Jwts
                .builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expireTime))
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact();
    }

    public String getUsernameFromToken(String token) {
        return Jwts
                .parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public boolean validateToken(String token) {
        try {
            Jwts
                    .parser()
                    .setSigningKey(SECRET_KEY)
                    .parseClaimsJws(token);
            return true;
        } catch (MalformedJwtException e) {
            throw new MalformedJwtException("token is taken inappropriately");
        } catch (UnsupportedJwtException e) {
            throw new UnsupportedJwtException("token is not supported");
        } catch (ExpiredJwtException e) {
            throw new UnsupportedJwtException("token is expired already");
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("token is empty");
        } catch (SignatureException e) {
            throw new SignatureException("token is not original");
        }
    }


}
