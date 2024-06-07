package org.smida.smidaApplication.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.smida.smidaApplication.constants.ProjectConstants;
import org.smida.smidaApplication.dto.CompanyDto;
import org.smida.smidaApplication.dto.ResponseDto;
import org.smida.smidaApplication.service.CompanyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "/api/v1/companies", produces = {MediaType.APPLICATION_JSON_VALUE})
@Tag(
        name = "CRUD REST APIs for Companies",
        description = "REST API Documentation for managing companies"
)
public class CompanyController {

    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @Operation(summary = "Get all companies", description = "Retrieve a list of all companies")
    @GetMapping("/getAllCompanies")
    public ResponseEntity<List<CompanyDto>> getAllCompanies() {
        return ResponseEntity.ok(companyService.getAllCompanies());
    }

    @Operation(summary = "Get company by ID", description = "Retrieve a company by its ID")
    @GetMapping("/getCompany")
    public ResponseEntity<CompanyDto> getCompany(@RequestParam UUID uuid) {
        return ResponseEntity.ok(companyService.getCompany(uuid));
    }

    @Operation(summary = "Save new company", description = "Create a new company")
    @ApiResponse(responseCode = "201", description = "Company created successfully")
    @PostMapping("/admin/saveCompany")
    public ResponseEntity<CompanyDto> saveCompany(@RequestBody CompanyDto companyDto) {

        CompanyDto savedCompany = companyService.saveCompany(companyDto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(savedCompany);
    }

    @Operation(summary = "Update company", description = "Update an existing company")
    @PutMapping("/admin/updateCompany")
    public ResponseEntity<CompanyDto> updateCompany(@RequestBody CompanyDto companyDto) {

        CompanyDto updatedCompany = companyService.updateCompany(companyDto);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(updatedCompany);
    }

    @Operation(summary = "Delete company", description = "Delete a company by its ID")
    @ApiResponse(responseCode = "200", description = "Company deleted successfully")
    @ApiResponse(responseCode = "417", description = "Failed to delete company")
    @DeleteMapping("/admin/deleteCompany")
    public ResponseEntity<ResponseDto> deleteCompany(@RequestParam UUID uuid) {
        boolean isDeleted = companyService.deleteCompany(uuid);

        if (isDeleted) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(ProjectConstants.STATUS_200, ProjectConstants.MESSAGE_200));
        }
        return ResponseEntity
                .status(HttpStatus.EXPECTATION_FAILED)
                .body(new ResponseDto(ProjectConstants.STATUS_417, ProjectConstants.MESSAGE_417_DELETE));
    }
}