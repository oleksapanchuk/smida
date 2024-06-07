package org.smida.smidaApplication.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.sql.Timestamp;
import java.util.UUID;

@Data
@Schema(
        name = "CompanyDto",
        description = "Schema for Company data transfer object"
)
public class CompanyDto {

    @Schema(
            description = "Company ID",
            example = "123e4567-e89b-12d3-a456-426614174000"
    )
    private UUID id;

    @Schema(
            description = "Company name",
            example = "Acme Corporation"
    )
    private String name;

    @Schema(
            description = "Registration number",
            example = "REG12345"
    )
    private String registrationNumber;

    @Schema(
            description = "Company address",
            example = "123 Main Street, Anytown, USA"
    )
    private String address;

    @Schema(
            description = "Timestamp when the company was created",
            example = "2022-06-01T12:00:00Z"
    )
    private Timestamp createdAt;


}
