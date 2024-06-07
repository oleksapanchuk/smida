package org.smida.smidaApplication.service.impl;

import org.smida.smidaApplication.dto.ReportDetailsDto;
import org.smida.smidaApplication.entity.ReportDetails;
import org.smida.smidaApplication.mapper.ReportDetailsMapper;
import org.smida.smidaApplication.repository.ReportDetailsRepository;
import org.smida.smidaApplication.service.ReportDetailsService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ReportDetailsServiceImpl implements ReportDetailsService {

    private final ReportDetailsRepository reportDetailsRepository;

    public ReportDetailsServiceImpl(ReportDetailsRepository reportDetailsRepository) {
        this.reportDetailsRepository = reportDetailsRepository;
    }

    /**
     * Retrieves details of a report based on the provided ID.
     *
     * @param uuid - UUID of the report for which details are to be retrieved.
     * @return ReportDetailsDto representing the details of the report.
     */
    @Override
    public ReportDetailsDto getReportDetails(UUID uuid) {
        ReportDetails reportDetails = reportDetailsRepository.findByReportId(uuid);
        return ReportDetailsMapper.INSTANCE.reportDetailsToReportDetailDto(reportDetails);
    }
}
