package org.smida.smidaApplication.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.smida.smidaApplication.dto.ReportDto;
import org.smida.smidaApplication.entity.Report;

import java.util.List;

@Mapper
public interface ReportMapper {
    ReportMapper INSTANCE = Mappers.getMapper(ReportMapper.class);

    ReportDto reportToReportDto(Report report);

    Report reportDtoToReport(ReportDto reportDto);

    List<ReportDto> reportsToReportDtos(List<Report> reports);
}
