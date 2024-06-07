package org.smida.smidaApplication.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
        name = "AuthenticationRequest",
        description = "Schema for Authentication Request"
)
public class AuthenticationRequest {

    @Schema(
            description = "Username",
            example = "john_doe"
    )
    private String username;

    @Schema(
            description = "Password",
            example = "password123"
    )
    private String password;

    public AuthenticationRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
