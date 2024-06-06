package org.smida.smidaApplication.service;

import org.smida.smidaApplication.dto.ReportDto;

import java.util.List;
import java.util.UUID;

public interface ReportService {
    List<ReportDto> getAllReports();

    List<ReportDto> getReportsByCompanyId(UUID companyId);

    ReportDto getReport(UUID uuid);

    ReportDto saveReport(ReportDto reportDto);

    ReportDto updateReport(ReportDto reportDto);

    boolean deleteReport(UUID uuid);
}
