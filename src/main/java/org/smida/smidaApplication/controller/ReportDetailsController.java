package org.smida.smidaApplication.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.smida.smidaApplication.dto.ReportDetailsDto;
import org.smida.smidaApplication.service.ReportDetailsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@AllArgsConstructor
@Slf4j
@RestController
@RequestMapping(path = "/api/v1/report-details", produces = {MediaType.APPLICATION_JSON_VALUE})
@Tag(name = "Report Details API", description = "REST API Documentation for report details")
public class ReportDetailsController {

    private final ReportDetailsService reportDetailsService;

    @Operation(summary = "Get report details by ID", description = "Retrieve details of a report by its ID")
    @GetMapping("/getReportDetails")
    public ResponseEntity<ReportDetailsDto> getReportDetails(@RequestParam UUID uuid) {
        log.info("Request received to retrieve report details by ID: {}", uuid);
        ReportDetailsDto reportDetailsDto = reportDetailsService.getReportDetails(uuid);
        log.info("Retrieved report details: {}", reportDetailsDto);
        return ResponseEntity.ok(reportDetailsDto);
    }

    @Operation(summary = "Create report details", description = "Create new report details")
    @PostMapping("/admin/createReportDetails")
    public ResponseEntity<ReportDetailsDto> createReportDetails(@RequestBody ReportDetailsDto reportDetailsDto) {
        log.info("Request received to create report details: {}", reportDetailsDto);
        ReportDetailsDto createdReportDetails = reportDetailsService.saveReportDetails(reportDetailsDto);
        log.info("Created report details: {}", createdReportDetails);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdReportDetails);
    }

    @Operation(summary = "Update report details", description = "Update existing report details")
    @PutMapping("/admin/updateReportDetails")
    public ResponseEntity<ReportDetailsDto> updateReportDetails(@RequestBody ReportDetailsDto reportDetailsDto) {
        log.info("Request received to update report details: {}", reportDetailsDto);
        ReportDetailsDto updatedReportDetails = reportDetailsService.updateReportDetails(reportDetailsDto);
        log.info("Updated report details: {}", updatedReportDetails);
        return ResponseEntity.ok(updatedReportDetails);
    }

    @Operation(summary = "Delete report details by ID", description = "Delete report details by its ID")
    @DeleteMapping("/admin/deleteReportDetails")
    public ResponseEntity<Void> deleteReportDetails(@RequestParam String id) {
        log.info("Request received to delete report details by ID: {}", id);
        reportDetailsService.deleteReportDetails(id);
        log.info("Deleted report details with ID: {}", id);
        return ResponseEntity.noContent().build();
    }
}
