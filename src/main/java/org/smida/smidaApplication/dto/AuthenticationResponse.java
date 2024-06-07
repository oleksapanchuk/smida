package org.smida.smidaApplication.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
        name = "AuthenticationResponse",
        description = "Schema for Authentication Response"
)
public class AuthenticationResponse {

    @Schema(
            description = "JWT token",
            example = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c"
    )
    private final String jwt;

    public AuthenticationResponse(String jwt) {
        this.jwt = jwt;
    }

    public String getJwt() {
        return jwt;
    }
}
