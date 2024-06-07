package org.smida.smidaApplication.service;

import org.smida.smidaApplication.dto.ReportDetailsDto;

import java.util.UUID;

public interface ReportDetailsService {
    /**
     * Retrieves details of a report based on the provided ID.
     *
     * @param uuid - UUID of the report for which details are to be retrieved.
     * @return ReportDetailsDto representing the details of the report.
     */
    ReportDetailsDto getReportDetails(UUID uuid);
}
