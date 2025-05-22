package com.eriksgda.hotel_management.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.impl.lang.Function;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JWTUtil {

    private String generateToken(Map<String, Object> extraClaims, UserDetails details) {
        return Jwts.builder().claims(extraClaims).subject(details.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 1000*60*60*24))
                .signWith(getSigningKey()).compact();
    }

    public String generateToken(UserDetails userDetails) {
        return generateToken(new HashMap<>(), userDetails);
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);

        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser().verifyWith(getSigningKey()).build().parseSignedClaims(token).getPayload();
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolvers) {
        final Claims claims = extractAllClaims(token);

        return claimsResolvers.apply(claims);
    }

    private SecretKey getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode("ZHVtbXlTZWNyZXRLZXlTdHJpbmdGb3JKV1QxMjM0NTY3ODkwMTI=");

        return Keys.hmacShaKeyFor(keyBytes);
    }
}
