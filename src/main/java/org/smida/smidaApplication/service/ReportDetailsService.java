package org.smida.smidaApplication.service;

import org.smida.smidaApplication.dto.ReportDetailsDto;

import java.util.UUID;

/**
 * Service interface for managing report details.
 */
public interface ReportDetailsService {

    /**
     * Retrieves the details of a report by its UUID.
     *
     * @param uuid The UUID of the report for which details are to be retrieved.
     * @return The {@link ReportDetailsDto} representing the details of the report.
     */
    ReportDetailsDto getReportDetails(UUID uuid);

    /**
     * Saves the provided report details.
     *
     * @param reportDetailsDto The {@link ReportDetailsDto} to be saved.
     * @return The saved {@link ReportDetailsDto}.
     */
    ReportDetailsDto saveReportDetails(ReportDetailsDto reportDetailsDto);

    /**
     * Updates the provided report details.
     *
     * @param reportDetailsDto The {@link ReportDetailsDto} to be updated.
     * @return The updated {@link ReportDetailsDto}.
     */
    ReportDetailsDto updateReportDetails(ReportDetailsDto reportDetailsDto);

    /**
     * Deletes the report details with the specified UUID.
     *
     * @param id The ID of the report details to be deleted.
     */
    void deleteReportDetails(String id);
}