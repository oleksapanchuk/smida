package org.smida.smidaApplication.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.smida.smidaApplication.constants.ProjectConstants;
import org.smida.smidaApplication.dto.ReportDto;
import org.smida.smidaApplication.dto.ResponseDto;
import org.smida.smidaApplication.service.ReportService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Slf4j
@RestController
@RequestMapping(path = "/api/v1/reports", produces = {MediaType.APPLICATION_JSON_VALUE})
@Tag(name = "CRUD REST APIs for Reports", description = "REST API Documentation for managing reports")
public class ReportController {

    private final ReportService reportService;

    @Operation(summary = "Get all reports", description = "Retrieve a list of all reports")
    @GetMapping("/getAllReports")
    public ResponseEntity<List<ReportDto>> getAllReports() {
        log.info("Request received to retrieve all reports");
        List<ReportDto> reports = reportService.getAllReports();
        log.info("Retrieved {} reports", reports.size());
        return ResponseEntity.ok(reports);
    }

    @Operation(summary = "Get reports by company ID", description = "Retrieve reports for a specific company")
    @GetMapping("/getReportsByCompanyId")
    public ResponseEntity<List<ReportDto>> getReportsByCompanyId(@RequestParam UUID companyId) {
        log.info("Request received to retrieve reports for company ID: {}", companyId);
        List<ReportDto> reports = reportService.getReportsByCompanyId(companyId);
        log.info("Retrieved {} reports for company ID: {}", reports.size(), companyId);
        return ResponseEntity.ok(reports);
    }

    @Operation(summary = "Get report by ID", description = "Retrieve a report by its ID")
    @GetMapping("/getReport")
    public ResponseEntity<ReportDto> getReport(@RequestParam UUID uuid) {
        log.info("Request received to retrieve report by ID: {}", uuid);
        ReportDto report = reportService.getReport(uuid);
        log.info("Retrieved report: {}", report);
        return ResponseEntity.ok(report);
    }

    @Operation(summary = "Save new report", description = "Create a new report")
    @ApiResponse(responseCode = "201", description = "Report created successfully")
    @PostMapping("/admin/saveReport")
    public ResponseEntity<ReportDto> saveReport(@RequestBody ReportDto reportDto) {
        log.info("Request received to save report: {}", reportDto);
        ReportDto savedReport = reportService.saveReport(reportDto);
        log.info("Saved report: {}", savedReport);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(savedReport);
    }

    @Operation(summary = "Update report", description = "Update an existing report")
    @PutMapping("/admin/updateReport")
    public ResponseEntity<ReportDto> updateReport(@RequestBody ReportDto reportDto) {
        log.info("Request received to update report: {}", reportDto);
        ReportDto updatedReport = reportService.updateReport(reportDto);
        log.info("Updated report: {}", updatedReport);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(updatedReport);
    }

    @Operation(summary = "Delete report", description = "Delete a report by its ID")
    @ApiResponse(responseCode = "200", description = "Report deleted successfully")
    @ApiResponse(responseCode = "417", description = "Failed to delete report")
    @DeleteMapping("/admin/deleteReport")
    public ResponseEntity<ResponseDto> deleteReport(@RequestParam UUID uuid) {
        log.info("Request received to delete report by ID: {}", uuid);
        boolean isDeleted = reportService.deleteReport(uuid);
        if (isDeleted) {
            log.info("Report with ID {} deleted successfully", uuid);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(ProjectConstants.STATUS_200, ProjectConstants.MESSAGE_200));
        } else {
            log.warn("Failed to delete report with ID: {}", uuid);
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto(ProjectConstants.STATUS_417, ProjectConstants.MESSAGE_417_DELETE));
        }
    }
}
