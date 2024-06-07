package org.smida.smidaApplication.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
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

@RestController
@RequestMapping(path = "/api/v1/reports", produces = {MediaType.APPLICATION_JSON_VALUE})
@Tag(name = "CRUD REST APIs for Reports", description = "REST API Documentation for managing reports")
public class ReportController {

    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @Operation(summary = "Get all reports", description = "Retrieve a list of all reports")
    @GetMapping("/getAllReports")
    public ResponseEntity<List<ReportDto>> getAllReports() {
        return ResponseEntity.ok(reportService.getAllReports());
    }

    @Operation(summary = "Get reports by company ID", description = "Retrieve reports for a specific company")
    @GetMapping("/getReportsByCompanyId")
    public ResponseEntity<List<ReportDto>> getReportsByCompanyId(@RequestParam UUID companyId) {
        return ResponseEntity.ok(reportService.getReportsByCompanyId(companyId));
    }

    @Operation(summary = "Get report by ID", description = "Retrieve a report by its ID")
    @GetMapping("/getReport")
    public ResponseEntity<ReportDto> getReport(@RequestParam UUID uuid) {
        return ResponseEntity.ok(reportService.getReport(uuid));
    }

    @Operation(summary = "Save new report", description = "Create a new report")
    @ApiResponse(responseCode = "201", description = "Report created successfully")
    @PostMapping("/admin/saveReport")
    public ResponseEntity<ReportDto> saveReport(@RequestBody ReportDto reportDto) {

        ReportDto savedReport = reportService.saveReport(reportDto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(savedReport);
    }

    @Operation(summary = "Update report", description = "Update an existing report")
    @PutMapping("/admin/updateReport")
    public ResponseEntity<ReportDto> updateReport(@RequestBody ReportDto reportDto) {

        ReportDto updatedReport = reportService.updateReport(reportDto);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(updatedReport);
    }

    @Operation(summary = "Delete report", description = "Delete a report by its ID")
    @ApiResponse(responseCode = "200", description = "Report deleted successfully")
    @ApiResponse(responseCode = "417", description = "Failed to delete report")
    @DeleteMapping("/admin/deleteReport")
    public ResponseEntity<ResponseDto> deleteReport(@RequestParam UUID uuid) {
        boolean isDeleted = reportService.deleteReport(uuid);

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
