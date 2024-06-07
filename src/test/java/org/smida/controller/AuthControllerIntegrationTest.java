package org.smida.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.smida.smidaApplication.SmidaApplication;
import org.smida.smidaApplication.dto.AuthenticationRequest;
import org.smida.smidaApplication.service.impl.CustomUserDetailsService;
import org.smida.smidaApplication.service.impl.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = SmidaApplication.class)
@AutoConfigureMockMvc
public class AuthControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuthenticationManager authenticationManager;

    @MockBean
    private JwtService jwtService;

    @MockBean
    private CustomUserDetailsService userDetailsService;

    @Test
    public void testCreateAuthenticationToken() throws Exception {
        String username = "testUser";
        String password = "testPassword";
        String token = "testToken";

        AuthenticationRequest authenticationRequest = new AuthenticationRequest(username, password);
        UserDetails userDetails = new User(username, password, Collections.singletonList(new SimpleGrantedAuthority("ROLE_ADMIN")));

        when(authenticationManager.authenticate(any()))
                .thenReturn(null); // Mock successful authentication
        when(userDetailsService.loadUserByUsername(username))
                .thenReturn(userDetails); // Mock UserDetails retrieval
        when(jwtService.generateToken(userDetails))
                .thenReturn(token); // Mock JWT generation

        mockMvc.perform(post("/api/v1/authenticate/login")
                        .content(new ObjectMapper().writeValueAsString(authenticationRequest))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.jwt").value(token));
    }
}
