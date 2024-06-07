package org.smida.smidaApplication.service.impl;

import org.smida.smidaApplication.dto.ReportDto;
import org.smida.smidaApplication.entity.Report;
import org.smida.smidaApplication.excaption.ResourceNotFoundException;
import org.smida.smidaApplication.mapper.ReportMapper;
import org.smida.smidaApplication.repository.ReportRepository;
import org.smida.smidaApplication.service.ReportService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ReportServiceImpl implements ReportService {

    private final ReportRepository reportRepository;

    public ReportServiceImpl(ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }

    /**
     * Retrieves a list of all reports.
     *
     * @return List of ReportDto representing all reports.
     */
    @Override
    public List<ReportDto> getAllReports() {
        List<Report> reports = reportRepository.findAll();
        return ReportMapper.INSTANCE.reportsToReportDtos(reports);
    }

    /**
     * Retrieves a list of reports for a specific company based on the provided company ID.
     *
     * @param companyId - UUID of the company to retrieve reports for.
     * @return List of ReportDto representing the reports for the specified company.
     */
    @Override
    public List<ReportDto> getReportsByCompanyId(UUID companyId) {
        List<Report> reports = reportRepository.findAllByCompanyId(companyId);
        return ReportMapper.INSTANCE.reportsToReportDtos(reports);
    }

    /**
     * Retrieves a report based on the provided ID.
     *
     * @param uuid - UUID of the report to retrieve.
     * @return ReportDto representing the report details.
     */
    @Override
    public ReportDto getReport(UUID uuid) {
        return reportRepository.findById(uuid)
                .map(ReportMapper.INSTANCE::reportToReportDto)
                .orElseThrow(() -> new ResourceNotFoundException("Report", "uuid", uuid));
    }

    /**
     * Saves a new report.
     *
     * @param reportDto - ReportDto object containing the details of the report to be saved.
     * @return ReportDto representing the saved report details.
     */
    @Override
    public ReportDto saveReport(ReportDto reportDto) {
        Report report = ReportMapper.INSTANCE.reportDtoToReport(reportDto);
        Report createdReport = reportRepository.save(report);
        return ReportMapper.INSTANCE.reportToReportDto(createdReport);
    }

    /**
     * Updates an existing report.
     *
     * @param reportDto - ReportDto object containing the updated details of the report.
     * @return ReportDto representing the updated report details.
     */
    @Override
    public ReportDto updateReport(ReportDto reportDto) {
        Report report = ReportMapper.INSTANCE.reportDtoToReport(reportDto);
        Report updatedReport = reportRepository.save(report);
        return ReportMapper.INSTANCE.reportToReportDto(updatedReport);
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
            reportRepository.deleteById(uuid);
            return true; // Successfully deleted
        } catch (Exception e) {
            e.printStackTrace();
            return false; // Failed to delete
        }
    }
}
