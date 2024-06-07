package org.smida.smidaApplication.service.impl;

import org.smida.smidaApplication.dto.ReportDetailsDto;
import org.smida.smidaApplication.entity.ReportDetails;
import org.smida.smidaApplication.mapper.ReportDetailsMapper;
import org.smida.smidaApplication.repository.ReportDetailsRepository;
import org.smida.smidaApplication.service.ReportDetailsService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
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
        ReportDetails reportDetails = reportDetailsRepository.findByReportId(uuid)
                .orElseThrow(() -> new EntityNotFoundException("Report details not found with ID: " + uuid));
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
        ReportDetails reportDetails = ReportDetailsMapper.INSTANCE.reportDetailsDtoToReportDetails(reportDetailsDto);
        reportDetails = reportDetailsRepository.save(reportDetails);
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
        // Retrieve the report details entity from the database
        ReportDetails existingReportDetails = reportDetailsRepository.findById(reportDetailsDto.getId())
                .orElseThrow(() -> new EntityNotFoundException("Report details not found with ID: " + reportDetailsDto.getId()));

        // Update the existing report details entity with the new data
        existingReportDetails.setFinancialData(reportDetailsDto.getFinancialData());
        existingReportDetails.setComments(reportDetailsDto.getComments());

        // Save the updated report details entity
        ReportDetails updatedReportDetails = reportDetailsRepository.save(existingReportDetails);

        // Map and return the updated report details DTO
        return ReportDetailsMapper.INSTANCE.reportDetailsToReportDetailDto(updatedReportDetails);
    }

    /**
     * Deletes the report details with the specified UUID.
     *
     * @param id The ID of the report details to be deleted.
     */
    @Override
    public void deleteReportDetails(String id) {
        // Check if the report details exist
        if (!reportDetailsRepository.existsById(id)) {
            throw new EntityNotFoundException("Report details not found with ID: " + id);
        }

        // Delete the report details from the database
        reportDetailsRepository.deleteById(id);
    }
}
