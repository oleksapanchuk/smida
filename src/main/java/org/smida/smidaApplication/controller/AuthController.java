package org.smida.smidaApplication.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.smida.smidaApplication.dto.AuthenticationRequest;
import org.smida.smidaApplication.dto.AuthenticationResponse;
import org.smida.smidaApplication.service.impl.CustomUserDetailsService;
import org.smida.smidaApplication.service.impl.JwtServiceImpl;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@Slf4j
@RestController
@RequestMapping(path = "/api/v1/authenticate", produces = {MediaType.APPLICATION_JSON_VALUE})
@Tag(name = "Authentication API", description = "REST API Documentation for authentication")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final JwtServiceImpl jwtServiceImpl;
    private final CustomUserDetailsService userDetailsService;

    @Operation(summary = "Authenticate user", description = "Authenticate user and generate JWT token")
    @ApiResponse(responseCode = "200", description = "JWT token generated successfully")
    @ApiResponse(responseCode = "401", description = "Unauthorized: Incorrect username or password")
    @PostMapping("/login")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        log.info("Authentication request received for user: {}", authenticationRequest.getUsername());
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
            );
            log.info("Authentication successful for user: {}", authenticationRequest.getUsername());
        } catch (BadCredentialsException e) {
            log.error("Authentication failed for user: {}", authenticationRequest.getUsername(), e);
            throw new Exception("Incorrect username or password", e);
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        final String jwt = jwtServiceImpl.generateToken(userDetails);
        log.info("JWT token generated successfully for user: {}", authenticationRequest.getUsername());

        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }
}
