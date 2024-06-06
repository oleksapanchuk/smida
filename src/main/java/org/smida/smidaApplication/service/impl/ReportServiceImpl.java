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

    @Override
    public List<ReportDto> getAllReports() {
        List<Report> reports = reportRepository.findAll();
        return ReportMapper.INSTANCE.reportsToReportDtos(reports);
    }

    @Override
    public List<ReportDto> getReportsByCompanyId(UUID companyId) {
        List<Report> reports = reportRepository.findAllByCompanyId(companyId);
        return ReportMapper.INSTANCE.reportsToReportDtos(reports);
    }

    @Override
    public ReportDto getReport(UUID uuid) {
        return reportRepository.findById(uuid)
                .map(ReportMapper.INSTANCE::reportToReportDto)
                .orElseThrow(() -> new ResourceNotFoundException("Report", "uuid", uuid));
    }

    @Override
    public ReportDto saveReport(ReportDto reportDto) {
        Report report = ReportMapper.INSTANCE.reportDtoToReport(reportDto);
        Report createdReport = reportRepository.save(report);
        return ReportMapper.INSTANCE.reportToReportDto(createdReport);
    }

    @Override
    public ReportDto updateReport(ReportDto reportDto) {
        Report report = ReportMapper.INSTANCE.reportDtoToReport(reportDto);
        Report updatedReport = reportRepository.save(report);
        return ReportMapper.INSTANCE.reportToReportDto(updatedReport);
    }

    @Override
    public boolean deleteReport(UUID uuid) {
        reportRepository.deleteById(uuid);
        return true;
    }
}
