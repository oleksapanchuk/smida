package org.smida.smidaApplication.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.smida.smidaApplication.entity.User;
import org.smida.smidaApplication.repository.UserRepository;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Service class to load user details by username for authentication purposes.
 */
@AllArgsConstructor
@Slf4j
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    /**
     * Retrieves UserDetails based on the provided username.
     *
     * @param username - Username of the user.
     * @return UserDetails object representing the user details.
     * @throws UsernameNotFoundException if the user with the provided username is not found.
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("Attempting to load user details for username: {}", username);
        User user = userRepository.findByUsername(username);
        if (user == null) {
            log.error("User not found for username: {}", username);
            throw new UsernameNotFoundException("User not found");
        }
        log.info("User details loaded successfully for username: {}", username);
        // Create UserDetails object from User entity
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                AuthorityUtils.createAuthorityList(user.getRole()));
    }
}
