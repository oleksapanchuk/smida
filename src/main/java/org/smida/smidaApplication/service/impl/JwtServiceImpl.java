package org.smida.smidaApplication.service.impl;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.smida.smidaApplication.service.JwtService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Slf4j
@Component
public class JwtServiceImpl implements JwtService {
    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private Long expiration;

    /**
     * Extracts the email from the JWT token.
     *
     * @param token - JWT token string.
     * @return Extracted email from the token.
     */
    public String extractEmail(String token) {
        log.info("Extracting email from JWT token");
        return extractClaim(token, Claims::getSubject);
    }

    /**
     * Extracts a claim from the JWT token.
     *
     * @param token          - JWT token string.
     * @param claimsResolver - Function to resolve the claim.
     * @param <T>            - Type of the claim.
     * @return Extracted claim from the token.
     */
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    /**
     * Generates a JWT token for the provided UserDetails.
     *
     * @param userDetails - UserDetails object.
     * @return Generated JWT token.
     */
    public String generateToken(UserDetails userDetails) {
        log.info("Generating JWT token for UserDetails: {}", userDetails.getUsername());
        return generateToken(new HashMap<>(), userDetails);
    }

    /**
     * Generates a JWT token for the provided email.
     *
     * @param email - Email address.
     * @return Generated JWT token.
     */
    public String generateToken(String email) {
        log.info("Generating JWT token for email: {}", email);
        return Jwts
                .builder()
                .setSubject(email)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    /**
     * Generates a JWT token with extra claims for the provided UserDetails.
     *
     * @param extraClaims - Extra claims to include in the token.
     * @param userDetails - UserDetails object.
     * @return Generated JWT token.
     */
    public String generateToken(
            Map<String, Object> extraClaims,
            UserDetails userDetails
    ) {
        log.info("Generating JWT token with extra claims for UserDetails: {}", userDetails.getUsername());
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    /**
     * Checks if the provided JWT token is valid for the given UserDetails.
     *
     * @param token       - JWT token string.
     * @param userDetails - UserDetails object.
     * @return true if the token is valid, false otherwise.
     */
    public boolean isTokenValid(String token, UserDetails userDetails) {
        log.info("Validating JWT token for UserDetails: {}", userDetails.getUsername());
        final String username = extractEmail(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    /**
     * Checks if the provided JWT token is expired.
     *
     * @param token - JWT token string.
     * @return true if the token is expired, false otherwise.
     */
    public boolean isTokenExpired(String token) {
        log.info("Checking if JWT token is expired");
        return extractExpiration(token).before(new Date());
    }

    private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }
}
