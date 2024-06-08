package org.smida.smidaApplication.mapper;

import org.smida.smidaApplication.dto.ReportDto;
import org.smida.smidaApplication.entity.Report;

import java.util.List;
import java.util.stream.Collectors;

public class ReportMapper {

    // Convert Report entity to ReportDto
    public static ReportDto toDto(Report report) {
        if (report == null) {
            return null;
        }

        ReportDto dto = new ReportDto();
        dto.setId(report.getId());
        dto.setCompanyId(report.getCompanyId());
        dto.setReportDate(report.getReportDate());
        dto.setTotalRevenue(report.getTotalRevenue());
        dto.setNetProfit(report.getNetProfit());

        return dto;
    }

    // Convert ReportDto to Report entity
    public static Report toEntity(ReportDto dto) {
        if (dto == null) {
            return null;
        }

        Report report = new Report();
        report.setId(dto.getId());
        report.setCompanyId(dto.getCompanyId());
        report.setReportDate(dto.getReportDate());
        report.setTotalRevenue(dto.getTotalRevenue());
        report.setNetProfit(dto.getNetProfit());

        return report;
    }

    // Convert List of Report entities to List of ReportDto
    public static List<ReportDto> reportsToReportDtos(List<Report> reports) {
        if (reports == null || reports.isEmpty()) {
            return null;
        }

        return reports.stream()
                .map(ReportMapper::toDto)
                .collect(Collectors.toList());
    }
}
