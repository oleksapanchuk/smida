package org.smida.smidaApplication.service;

import org.smida.smidaApplication.dto.ReportDto;

import java.util.List;
import java.util.UUID;

public interface ReportService {

    /**
     * Retrieves a list of all reports.
     *
     * @return List of ReportDto representing all reports.
     */
    List<ReportDto> getAllReports();

    /**
     * Retrieves a list of reports for a specific company based on the provided company ID.
     *
     * @param companyId - UUID of the company to retrieve reports for.
     * @return List of ReportDto representing the reports for the specified company.
     */
    List<ReportDto> getReportsByCompanyId(UUID companyId);

    /**
     * Retrieves a report based on the provided ID.
     *
     * @param uuid - UUID of the report to retrieve.
     * @return ReportDto representing the report details.
     */
    ReportDto getReport(UUID uuid);

    /**
     * Saves a new report.
     *
     * @param reportDto - ReportDto object containing the details of the report to be saved.
     * @return ReportDto representing the saved report details.
     */
    ReportDto saveReport(ReportDto reportDto);

    /**
     * Updates an existing report.
     *
     * @param reportDto - ReportDto object containing the updated details of the report.
     * @return ReportDto representing the updated report details.
     */
    ReportDto updateReport(ReportDto reportDto);

    /**
     * Deletes a report based on the provided ID.
     *
     * @param uuid - UUID of the report to delete.
     * @return boolean indicating if the deletion of the report was successful or not.
     */
    boolean deleteReport(UUID uuid);
}
