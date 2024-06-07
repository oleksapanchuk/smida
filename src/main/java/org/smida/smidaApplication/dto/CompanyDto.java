package org.smida.smidaApplication.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.sql.Timestamp;
import java.util.UUID;

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

    public CompanyDto() {
    }

    public CompanyDto(UUID id, String name, String registrationNumber, String address, Timestamp createdAt) {
        this.id = id;
        this.name = name;
        this.registrationNumber = registrationNumber;
        this.address = address;
        this.createdAt = createdAt;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
}
