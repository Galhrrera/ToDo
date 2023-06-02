package com.example.todo.security;

import com.example.todo.model.Customer;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;


import java.util.Date;

@Component
public class JWTTokenUtil {

    private static final long EXPIRE_DURATION = 24*60*60*1000;
    private final String SECRET_KEY = "v7yG#5zqL`7707]";

    public String generateAccesToken(Customer customer){
        return Jwts.builder()
                .setSubject(String.format("%s,%s", customer.getId(), customer.getUsername()))
                .setIssuer(customer.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE_DURATION))
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact();
    }
}
