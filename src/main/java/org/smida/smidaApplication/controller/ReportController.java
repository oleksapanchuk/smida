package org.smida.smidaApplication.controller;

import org.smida.smidaApplication.constants.ProjectConstants;
import org.smida.smidaApplication.dto.ReportDto;
import org.smida.smidaApplication.dto.ResponseDto;
import org.smida.smidaApplication.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "/api/v1/reports", produces = {MediaType.APPLICATION_JSON_VALUE})
public class ReportController {

    @Autowired
    private ReportService reportService;

    @GetMapping("/getAllReports")
    public ResponseEntity<List<ReportDto>> getAllReports() {
        return ResponseEntity.ok(reportService.getAllReports());
    }

    @GetMapping("/getReportsByCompanyId")
    public ResponseEntity<List<ReportDto>> getReportsByCompanyId(@RequestParam UUID companyId) {
        return ResponseEntity.ok(reportService.getReportsByCompanyId(companyId));
    }

    @GetMapping("/getReport")
    public ResponseEntity<ReportDto> getReport(@RequestParam UUID uuid) {
        return ResponseEntity.ok(reportService.getReport(uuid));
    }

    @PostMapping("/saveReport")
    public ResponseEntity<ReportDto> saveReport(@RequestBody ReportDto reportDto) {

        ReportDto savedReport = reportService.saveReport(reportDto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(savedReport);
    }

    @PutMapping("/updateReport")
    public ResponseEntity<ReportDto> updateReport(@RequestBody ReportDto reportDto) {

        ReportDto updatedReport = reportService.updateReport(reportDto);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(updatedReport);
    }

    @DeleteMapping("/deleteReport")
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
