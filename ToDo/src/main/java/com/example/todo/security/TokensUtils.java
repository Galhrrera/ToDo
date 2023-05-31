package com.example.todo.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class TokensUtils {
    //d1cBx98YXso2VAsEsWy6LLuEYe8BXaCScnMWtixGf4fgtGoEsQRKKcLfT4mKlDd
    private static final String ACCESS_TOKEN_SECRET = "d1cBx98YXso2VAsEsWy6LLuEYe8BXaCScnMWtixGf4fgtGoEsQRKKcLfT4mKlDd";
    private static final Long ACCESS_TOKEN_SECONDS = 2592000L;

    public static String createToken(String username){
        long expirationTime = ACCESS_TOKEN_SECONDS*1000;
        Date expirationDate = new Date(System.currentTimeMillis()+expirationTime);

        Map<String, Object> extraPayload = new HashMap<>();
        extraPayload.put("username", username);

        String token = Jwts.builder()
                .setSubject(username)
                .setExpiration(expirationDate)
                .addClaims(extraPayload)
                .signWith(Keys.hmacShaKeyFor(ACCESS_TOKEN_SECRET.getBytes()))
                .compact();

        return token;
    }

    public static UsernamePasswordAuthenticationToken getAuthentication (String token){
        try{
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(ACCESS_TOKEN_SECRET.getBytes())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

            String username = claims.getSubject();

            return new UsernamePasswordAuthenticationToken(username, null, Collections.emptyList());
        }
        catch (JwtException e){
            return null;
        }
    }
}
