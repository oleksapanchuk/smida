package org.smida.smidaApplication.mapper;

import org.smida.smidaApplication.dto.ReportDetailsDto;
import org.smida.smidaApplication.entity.ReportDetails;

import java.util.List;
import java.util.stream.Collectors;

public class ReportDetailsMapper {

    // Convert ReportDetails entity to ReportDetailsDto
    public static ReportDetailsDto toDto(ReportDetails reportDetails) {
        if (reportDetails == null) {
            return null;
        }

        ReportDetailsDto dto = new ReportDetailsDto();
        dto.setId(reportDetails.getId());
        dto.setReportId(reportDetails.getReportId());
        dto.setFinancialData(reportDetails.getFinancialData());
        dto.setComments(reportDetails.getComments());

        return dto;
    }

    // Convert ReportDetailsDto to ReportDetails entity
    public static ReportDetails toEntity(ReportDetailsDto dto) {
        if (dto == null) {
            return null;
        }

        ReportDetails reportDetails = new ReportDetails();
        reportDetails.setId(dto.getId());
        reportDetails.setReportId(dto.getReportId());
        reportDetails.setFinancialData(dto.getFinancialData());
        reportDetails.setComments(dto.getComments());

        return reportDetails;
    }

    // Convert List of ReportDetails entities to List of ReportDetailsDto
    public static List<ReportDetailsDto> reportDetailsToReportDetailsDtos(List<ReportDetails> reportDetailsList) {
        if (reportDetailsList == null || reportDetailsList.isEmpty()) {
            return null;
        }

        return reportDetailsList.stream()
                .map(ReportDetailsMapper::toDto)
                .collect(Collectors.toList());
    }
}
