package com.student.management.security;

import com.student.management.config.JwtProperties;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;

import java.nio.charset.StandardCharsets;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import java.util.function.Function;


@Service
public class JwtService {

    private final JwtProperties jwtProperties;


    public JwtService(
            JwtProperties jwtProperties) {

        this.jwtProperties =
                jwtProperties;
    }


    // =================================================
    // GENERATE TOKEN
    // =================================================

    public String generateToken(
            UserDetails userDetails) {

        return generateToken(
                new HashMap<>(),
                userDetails
        );
    }


    public String generateToken(
            Map<String, Object> extraClaims,
            UserDetails userDetails) {

        Date now =
                new Date();

        Date expiryDate =
                new Date(
                        now.getTime()
                                + jwtProperties.getExpiration()
                );


        return Jwts.builder()
                .claims(extraClaims)
                .subject(
                        userDetails.getUsername()
                )
                .issuedAt(now)
                .expiration(expiryDate)
                .signWith(
                        getSigningKey()
                )
                .compact();
    }


    // =================================================
    // EXTRACT USERNAME
    // =================================================

    @SuppressWarnings("null")
    public String extractUsername(
            String token) {

        return extractClaim(
                token,
                Claims::getSubject
        );
    }


    // =================================================
    // EXTRACT CLAIM
    // =================================================

    public <T> T extractClaim(
            String token,
            Function<Claims, T> claimsResolver) {

        Claims claims =
                extractAllClaims(
                        token
                );

        return claimsResolver.apply(
                claims
        );
    }


    // =================================================
    // EXTRACT ALL CLAIMS
    // =================================================

    private Claims extractAllClaims(
            String token) {

        return Jwts.parser()
                .verifyWith(
                        getSigningKey()
                )
                .build()
                .parseSignedClaims(
                        token
                )
                .getPayload();
    }


    // =================================================
    // TOKEN VALIDATION
    // =================================================

    public boolean isTokenValid(
            String token,
            UserDetails userDetails) {

        String username =
                extractUsername(
                        token
                );

        return username.equals(
                userDetails.getUsername()
        )
                &&
                !isTokenExpired(
                        token
                );
    }


    // =================================================
    // TOKEN EXPIRY
    // =================================================

    public boolean isTokenExpired(
            String token) {

        return extractExpiration(
                token
        ).before(
                new Date()
        );
    }


    @SuppressWarnings("null")
    public Date extractExpiration(
            String token) {

        return extractClaim(
                token,
                Claims::getExpiration
        );
    }


    // =================================================
    // SIGNING KEY
    // =================================================

        private SecretKey getSigningKey() {

        return Keys.hmacShaKeyFor(
                jwtProperties
                        .getSecret()
                        .getBytes(
                                StandardCharsets.UTF_8
                        )
        );
    }
}