package org.smida.smidaApplication.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.smida.smidaApplication.dto.ReportDto;
import org.smida.smidaApplication.entity.Report;
import org.smida.smidaApplication.excaption.ResourceNotFoundException;
import org.smida.smidaApplication.mapper.ReportMapper;
import org.smida.smidaApplication.repository.ReportRepository;
import org.smida.smidaApplication.service.ReportService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static org.smida.smidaApplication.mapper.ReportMapper.*;

/**
 * Service class to manage reports.
 */
@AllArgsConstructor
@Slf4j
@Service
public class ReportServiceImpl implements ReportService {

    private final ReportRepository reportRepository;

    /**
     * Retrieves a list of all reports.
     *
     * @return List of ReportDto representing all reports.
     */
    @Override
    public List<ReportDto> getAllReports() {
        log.info("Fetching all reports");
        List<Report> reports = reportRepository.findAll();
        log.info("Fetched {} reports", reports.size());
        return reportsToReportDtos(reports);
    }

    /**
     * Retrieves a list of reports for a specific company based on the provided company ID.
     *
     * @param companyId - UUID of the company to retrieve reports for.
     * @return List of ReportDto representing the reports for the specified company.
     */
    @Override
    public List<ReportDto> getReportsByCompanyId(UUID companyId) {
        log.info("Fetching reports for company with ID: {}", companyId);
        List<Report> reports = reportRepository.findAllByCompanyId(companyId);
        log.info("Fetched {} reports for company with ID: {}", reports.size(), companyId);
        return reportsToReportDtos(reports);
    }

    /**
     * Retrieves a report based on the provided ID.
     *
     * @param uuid - UUID of the report to retrieve.
     * @return ReportDto representing the report details.
     */
    @Override
    public ReportDto getReport(UUID uuid) {
        log.info("Fetching report with ID: {}", uuid);
        return reportRepository.findById(uuid)
                .map(ReportMapper::toDto)
                .orElseThrow(() -> {
                    log.error("Report not found with ID: {}", uuid);
                    return new ResourceNotFoundException("Report", "uuid", uuid);
                });
    }

    /**
     * Saves a new report.
     *
     * @param reportDto - ReportDto object containing the details of the report to be saved.
     * @return ReportDto representing the saved report details.
     */
    @Override
    public ReportDto saveReport(ReportDto reportDto) {
        log.info("Saving new report");
        Report report = toEntity(reportDto);
        Report createdReport = reportRepository.save(report);
        log.info("New report saved with ID: {}", createdReport.getId());
        return toDto(createdReport);
    }

    /**
     * Updates an existing report.
     *
     * @param reportDto - ReportDto object containing the updated details of the report.
     * @return ReportDto representing the updated report details.
     */
    @Override
    public ReportDto updateReport(ReportDto reportDto) {
        log.info("Updating report with ID: {}", reportDto.getId());
        Report report = toEntity(reportDto);
        Report updatedReport = reportRepository.save(report);
        log.info("Report with ID: {} updated successfully", reportDto.getId());
        return toDto(updatedReport);
    }

    /**
     * Deletes a report based on the provided ID.
     *
     * @param uuid - UUID of the report to delete.
     * @return boolean indicating if the deletion of the report was successful or not.
     */
    @Override
    public boolean deleteReport(UUID uuid) {
        try {
            log.info("Deleting report with ID: {}", uuid);
            reportRepository.deleteById(uuid);
            log.info("Report with ID: {} deleted successfully", uuid);
            return true;
        } catch (Exception e) {
            log.error("Failed to delete report with ID: {}", uuid, e);
            return false;
        }
    }
}
