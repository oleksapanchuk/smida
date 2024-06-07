package org.smida.smidaApplication.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
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

}
