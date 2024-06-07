package org.smida.smidaApplication.service;

import io.jsonwebtoken.Claims;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Map;
import java.util.function.Function;

/**
 * Interface for JWT (JSON Web Token) service.
 */
public interface JwtService {

    /**
     * Extracts the email from the JWT token.
     *
     * @param token - JWT token string.
     * @return Extracted email from the token.
     */
    String extractEmail(String token);

    /**
     * Extracts a claim from the JWT token.
     *
     * @param token          - JWT token string.
     * @param claimsResolver - Function to resolve the claim.
     * @param <T>            - Type of the claim.
     * @return Extracted claim from the token.
     */
    <T> T extractClaim(String token, Function<Claims, T> claimsResolver);

    /**
     * Generates a JWT token for the provided UserDetails.
     *
     * @param userDetails - UserDetails object.
     * @return Generated JWT token.
     */
    String generateToken(UserDetails userDetails);

    /**
     * Generates a JWT token for the provided email.
     *
     * @param email - Email address.
     * @return Generated JWT token.
     */
    String generateToken(String email);

    /**
     * Generates a JWT token with extra claims for the provided UserDetails.
     *
     * @param extraClaims - Extra claims to include in the token.
     * @param userDetails - UserDetails object.
     * @return Generated JWT token.
     */
    String generateToken(Map<String, Object> extraClaims, UserDetails userDetails);

    /**
     * Checks if the provided JWT token is valid for the given UserDetails.
     *
     * @param token       - JWT token string.
     * @param userDetails - UserDetails object.
     * @return true if the token is valid, false otherwise.
     */
    boolean isTokenValid(String token, UserDetails userDetails);

    /**
     * Checks if the provided JWT token is expired.
     *
     * @param token - JWT token string.
     * @return true if the token is expired, false otherwise.
     */
    boolean isTokenExpired(String token);
}