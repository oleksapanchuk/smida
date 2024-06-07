package org.smida.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.smida.smidaApplication.entity.User;
import org.smida.smidaApplication.repository.UserRepository;
import org.smida.smidaApplication.service.impl.CustomUserDetailsService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Collection;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CustomUserDetailsServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private CustomUserDetailsService customUserDetailsService;

    private User user;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setUsername("testuser");
        user.setPassword("password");
        user.setRole("ROLE_ADMIN");
    }


    @Test
    void testLoadUserByUsername_UserFound() {
        when(userRepository.findByUsername("testuser")).thenReturn(user);

        UserDetails userDetails = customUserDetailsService.loadUserByUsername("testuser");

        assertNotNull(userDetails);
        assertEquals("testuser", userDetails.getUsername());
        assertEquals("password", userDetails.getPassword());
        assertAuthorities(userDetails.getAuthorities(), Collections.singletonList(new SimpleGrantedAuthority("ROLE_ADMIN")));
        verify(userRepository, times(1)).findByUsername("testuser");
    }

    @Test
    void testLoadUserByUsername_UserNotFound() {
        when(userRepository.findByUsername("nonexistentuser")).thenReturn(null);

        assertThrows(UsernameNotFoundException.class, () -> customUserDetailsService.loadUserByUsername("nonexistentuser"));
        verify(userRepository, times(1)).findByUsername("nonexistentuser");
    }

    private void assertAuthorities(Collection<? extends GrantedAuthority> actualAuthorities, Collection<? extends GrantedAuthority> expectedAuthorities) {
        assertEquals(expectedAuthorities.size(), actualAuthorities.size());
        assertTrue(actualAuthorities.containsAll(expectedAuthorities));
    }
}
