package com.irctcClone.RailwayManager.config;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenUtil {
    @Value("${jwt.secret}")
    private String secret;

    // Method to generate the token
    public String generateToken(UserDetails userDetails) {
        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 86400000)) // 1 day validity
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    // Method to extract username from the token
    public String extractUsername(String token) {
        // Using parserBuilder() for JJWT version 0.12.x and above
        JwtParser jwtParser = Jwts.parser()
                .setSigningKey(secret)  // Set the signing key
                .build();  // Build the JwtParser
        return jwtParser.parseClaimsJws(token)  // Parse the JWT and get claims
                .getBody()
                .getSubject();  // Extract the subject (username)
    }

    // Method to validate the token
    public boolean validateToken(String token, UserDetails userDetails) {
        return extractUsername(token).equals(userDetails.getUsername()) &&
                !isTokenExpired(token);
    }

    // Method to check if the token is expired
    private boolean isTokenExpired(String token) {
        // Using parserBuilder() for JJWT version 0.12.x and above
        JwtParser jwtParser = Jwts.parser()
                .setSigningKey(secret)  // Set the signing key
                .build();  // Build the JwtParser
        Date expiration = jwtParser.parseClaimsJws(token)  // Parse the JWT
                .getBody()
                .getExpiration();  // Extract the expiration date
        return expiration.before(new Date());  // Check if expired
    }
}
