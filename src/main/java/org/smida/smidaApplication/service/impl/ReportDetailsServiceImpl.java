package org.smida.smidaApplication.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.smida.smidaApplication.dto.ReportDetailsDto;
import org.smida.smidaApplication.entity.ReportDetails;
import org.smida.smidaApplication.mapper.ReportDetailsMapper;
import org.smida.smidaApplication.repository.ReportDetailsRepository;
import org.smida.smidaApplication.service.ReportDetailsService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.UUID;

/**
 * Service class to manage report details.
 */
@AllArgsConstructor
@Slf4j
@Service
public class ReportDetailsServiceImpl implements ReportDetailsService {

    private final ReportDetailsRepository reportDetailsRepository;

    /**
     * Retrieves details of a report based on the provided ID.
     *
     * @param uuid - UUID of the report for which details are to be retrieved.
     * @return ReportDetailsDto representing the details of the report.
     */
    @Override
    public ReportDetailsDto getReportDetails(UUID uuid) {
        log.info("Attempting to retrieve report details for ID: {}", uuid);
        ReportDetails reportDetails = reportDetailsRepository.findByReportId(uuid)
                .orElseThrow(() -> {
                    log.error("Report details not found for ID: {}", uuid);
                    return new EntityNotFoundException("Report details not found with ID: " + uuid);
                });
        log.info("Report details retrieved successfully for ID: {}", uuid);
        return ReportDetailsMapper.INSTANCE.reportDetailsToReportDetailDto(reportDetails);
    }

    /**
     * Saves the provided report details.
     *
     * @param reportDetailsDto The {@link ReportDetailsDto} to be saved.
     * @return The saved {@link ReportDetailsDto}.
     */
    @Override
    public ReportDetailsDto saveReportDetails(ReportDetailsDto reportDetailsDto) {
        log.info("Attempting to save report details: {}", reportDetailsDto);
        ReportDetails reportDetails = ReportDetailsMapper.INSTANCE.reportDetailsDtoToReportDetails(reportDetailsDto);
        reportDetails = reportDetailsRepository.save(reportDetails);
        log.info("Report details saved successfully: {}", reportDetailsDto);
        return ReportDetailsMapper.INSTANCE.reportDetailsToReportDetailDto(reportDetails);
    }

    /**
     * Updates the provided report details.
     *
     * @param reportDetailsDto The {@link ReportDetailsDto} to be updated.
     * @return The updated {@link ReportDetailsDto}.
     */
    @Override
    public ReportDetailsDto updateReportDetails(ReportDetailsDto reportDetailsDto) {
        log.info("Attempting to update report details: {}", reportDetailsDto);
        ReportDetails existingReportDetails = reportDetailsRepository.findById(reportDetailsDto.getId())
                .orElseThrow(() -> {
                    log.error("Report details not found for ID: {}", reportDetailsDto.getId());
                    return new EntityNotFoundException("Report details not found with ID: " + reportDetailsDto.getId());
                });

        existingReportDetails.setFinancialData(reportDetailsDto.getFinancialData());
        existingReportDetails.setComments(reportDetailsDto.getComments());

        ReportDetails updatedReportDetails = reportDetailsRepository.save(existingReportDetails);
        log.info("Report details updated successfully: {}", updatedReportDetails);
        return ReportDetailsMapper.INSTANCE.reportDetailsToReportDetailDto(updatedReportDetails);
    }

    /**
     * Deletes the report details with the specified UUID.
     *
     * @param id The ID of the report details to be deleted.
     */
    @Override
    public void deleteReportDetails(String id) {
        log.info("Attempting to delete report details with ID: {}", id);
        if (!reportDetailsRepository.existsById(id)) {
            log.error("Report details not found for ID: {}", id);
            throw new EntityNotFoundException("Report details not found with ID: " + id);
        }
        reportDetailsRepository.deleteById(id);
        log.info("Report details deleted successfully with ID: {}", id);
    }
}
