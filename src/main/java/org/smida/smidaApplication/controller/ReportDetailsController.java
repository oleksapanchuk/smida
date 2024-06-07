package org.smida.smidaApplication.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.smida.smidaApplication.dto.ReportDetailsDto;
import org.smida.smidaApplication.service.ReportDetailsService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping(path = "/api/v1/reports-details", produces = {MediaType.APPLICATION_JSON_VALUE})
@Tag(name = "Report Details API", description = "REST API Documentation for report details")
public class ReportDetailsController {

    private final ReportDetailsService reportDetailsService;

    public ReportDetailsController(ReportDetailsService reportDetailsService) {
        this.reportDetailsService = reportDetailsService;
    }

    @Operation(summary = "Get report details by ID", description = "Retrieve details of a report by its ID")
    @GetMapping("/getReportDetails")
    public ResponseEntity<ReportDetailsDto> getReportDetails(@RequestParam UUID uuid) {
        return ResponseEntity.ok(reportDetailsService.getReportDetails(uuid));
    }
}
