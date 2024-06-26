package org.smida.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.smida.smidaApplication.service.impl.JwtServiceImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.util.ReflectionTestUtils;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class JwtServiceImplTest {

    private JwtServiceImpl jwtServiceImpl;

    @Mock
    private UserDetails userDetails;

    private final String secret = "testsecretkeytestsecretkeytestsecretkeytestsecretkey";
    private final Long expiration = 1000L * 60 * 60 * 24; // 1 day in milliseconds

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        jwtServiceImpl = new JwtServiceImpl();
        ReflectionTestUtils.setField(jwtServiceImpl, "secret", secret);
        ReflectionTestUtils.setField(jwtServiceImpl, "expiration", expiration);
    }


    @Test
    void testGenerateTokenWithUserDetails() {
        when(userDetails.getUsername()).thenReturn("test@example.com");
        String token = jwtServiceImpl.generateToken(userDetails);
        assertNotNull(token);
    }

    @Test
    void testGenerateTokenWithEmail() {
        String token = jwtServiceImpl.generateToken("test@example.com");
        assertNotNull(token);
    }

    @Test
    void testGenerateTokenWithExtraClaims() {
        when(userDetails.getUsername()).thenReturn("test@example.com");
        Map<String, Object> claims = new HashMap<>();
        claims.put("role", "ROLE_USER");
        String token = jwtServiceImpl.generateToken(claims, userDetails);
        assertNotNull(token);
    }


    private String generateTestToken(String email) {
        Key key = Keys.hmacShaKeyFor(secret.getBytes());
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    private String generateExpiredToken(String email) {
        Key key = Keys.hmacShaKeyFor(secret.getBytes());
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date(System.currentTimeMillis() - expiration * 2))
                .setExpiration(new Date(System.currentTimeMillis() - expiration))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }
}
