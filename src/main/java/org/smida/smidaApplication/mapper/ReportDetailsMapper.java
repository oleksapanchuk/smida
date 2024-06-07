package org.smida.smidaApplication.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.smida.smidaApplication.dto.ReportDetailsDto;
import org.smida.smidaApplication.entity.ReportDetails;

import java.util.List;

@Mapper
public interface ReportDetailsMapper {
    ReportDetailsMapper INSTANCE = Mappers.getMapper(ReportDetailsMapper.class);

    ReportDetailsDto reportDetailsToReportDetailDto(ReportDetails reportDetails);

    ReportDetails reportDetailsDtoToReportDetails(ReportDetailsDto reportDetailsDto);

    List<ReportDetailsDto> reportDetailsToReportDetailDtos(List<ReportDetails> reportDetailsDto);
}
