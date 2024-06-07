package org.smida.smidaApplication.controller;

import org.smida.smidaApplication.dto.ReportDetailsDto;
import org.smida.smidaApplication.service.ReportDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping(path = "/api/v1/reports-details", produces = {MediaType.APPLICATION_JSON_VALUE})
public class ReportDetailsController {

    @Autowired
    private ReportDetailsService reportDetailsService;

    @GetMapping("/getReportDetails")
    public ResponseEntity<ReportDetailsDto> getReportDetails(@RequestParam UUID uuid) {
        return ResponseEntity.ok(reportDetailsService.getReportDetails(uuid));
    }
}
