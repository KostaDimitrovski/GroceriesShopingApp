package com.example.groceriesShoping.service;

import io.jsonwebtoken.Claims;
import org.springframework.security.core.userdetails.UserDetails;

import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

public interface JwtService {
    String extractUserEmail(String token);

    Claims extractAllClaims(String token);

    Date extractExpiration(String token);

    boolean isTokenExpired(String token);

    Key getSignInKey();

    <T> T extractClaim(String token, Function<Claims, T> claimsResolver);

    String generateToken(Map<String, Object> extraClaims, UserDetails userDetails);

    boolean isTokenValid(String token, UserDetails userDetails);

    String generateToken(UserDetails userDetails);
    Key getRefreshSignInKey();
    String generateRefreshToken(UserDetails userDetails);
}
