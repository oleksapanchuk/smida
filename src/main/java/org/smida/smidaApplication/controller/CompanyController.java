package org.smida.smidaApplication.controller;

import org.smida.smidaApplication.constants.ProjectConstants;
import org.smida.smidaApplication.dto.CompanyDto;
import org.smida.smidaApplication.dto.ResponseDto;
import org.smida.smidaApplication.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "/api/v1/companies", produces = {MediaType.APPLICATION_JSON_VALUE})
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @GetMapping("/getAllCompanies")
    public ResponseEntity<List<CompanyDto>> getAllCompanies() {
        return ResponseEntity.ok(companyService.getAllCompanies());
    }

    @GetMapping("/getCompany")
    public ResponseEntity<CompanyDto> getCompany(@RequestParam UUID uuid) {
        return ResponseEntity.ok(companyService.getCompany(uuid));
    }

    @PostMapping("/saveCompany")
    public ResponseEntity<CompanyDto> saveCompany(@RequestBody CompanyDto companyDto) {

        CompanyDto savedCompany = companyService.saveCompany(companyDto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(savedCompany);
    }

    @PutMapping("/updateCompany")
    public ResponseEntity<CompanyDto> updateCompany(@RequestBody CompanyDto companyDto) {

        CompanyDto updatedCompany = companyService.updateCompany(companyDto);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(updatedCompany);
    }

    @DeleteMapping("/deleteCompany")
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