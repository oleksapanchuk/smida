package org.smida.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.smida.smidaApplication.dto.ReportDetailsDto;
import org.smida.smidaApplication.entity.ReportDetails;
import org.smida.smidaApplication.repository.ReportDetailsRepository;
import org.smida.smidaApplication.service.impl.ReportDetailsServiceImpl;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ReportDetailsServiceImplTest {

    @Mock
    private ReportDetailsRepository reportDetailsRepository;

    @InjectMocks
    private ReportDetailsServiceImpl reportDetailsService;

    private ReportDetails reportDetails;
    private ReportDetailsDto reportDetailsDto;
    private UUID reportId;

    @BeforeEach
    void setUp() {
        reportId = UUID.randomUUID();
        reportDetails = new ReportDetails();
        reportDetails.setReportId(reportId);

        reportDetailsDto = new ReportDetailsDto();
        reportDetailsDto.setId(reportId.toString());
    }

    @Test
    void testGetReportDetails() {
        // Mocking repository behavior
        when(reportDetailsRepository.findByReportId(reportId)).thenReturn(Optional.of(reportDetails));

        // Calling the service method
        ReportDetailsDto result = reportDetailsService.getReportDetails(reportId);

        // Assertions
        assertNotNull(result);
        assertEquals(reportId, result.getReportId());
        // Add assertions for other fields as needed

        // Verify repository method invocation
        verify(reportDetailsRepository, times(1)).findByReportId(reportId);
    }

    @Test
    void testGetReportDetails_NotFound() {
        // Mocking repository behavior
        when(reportDetailsRepository.findByReportId(reportId)).thenReturn(Optional.empty());

        // Calling the service method and expecting an exception
        assertThrows(EntityNotFoundException.class, () -> reportDetailsService.getReportDetails(reportId));

        // Verify repository method invocation
        verify(reportDetailsRepository, times(1)).findByReportId(reportId);
    }

    @Test
    void testSaveReportDetails() {
        // Mocking repository behavior
        when(reportDetailsRepository.save(any(ReportDetails.class))).thenReturn(reportDetails);

        // Calling the service method
        ReportDetailsDto result = reportDetailsService.saveReportDetails(reportDetailsDto);

        // Assertions
        assertNotNull(result);
        assertEquals(reportId, result.getReportId());
        // Add assertions for other fields as needed

        // Verify repository method invocation
        verify(reportDetailsRepository, times(1)).save(any(ReportDetails.class));
    }


    @Test
    void testUpdateReportDetails() {
        // Mocking repository behavior
        when(reportDetailsRepository.findById(reportId.toString())).thenReturn(Optional.of(reportDetails));
        when(reportDetailsRepository.save(any(ReportDetails.class))).thenReturn(reportDetails);

        // Calling the service method
        ReportDetailsDto result = reportDetailsService.updateReportDetails(reportDetailsDto);

        // Assertions
        assertNotNull(result);
        assertEquals(reportId, result.getReportId());
        // Add assertions for other fields as needed

        // Verify repository method invocation
        verify(reportDetailsRepository, times(1)).findById(reportId.toString());
        verify(reportDetailsRepository, times(1)).save(any(ReportDetails.class));
    }

    @Test
    void testDeleteReportDetails() {
        // Mocking repository behavior
        when(reportDetailsRepository.existsById(reportId.toString())).thenReturn(true);

        // Calling the service method
        reportDetailsService.deleteReportDetails(reportId.toString());

        // Verify repository method invocation
        verify(reportDetailsRepository, times(1)).existsById(reportId.toString());
        verify(reportDetailsRepository, times(1)).deleteById(reportId.toString());
    }

    @Test
    void testDeleteReportDetails_NotFound() {
        // Mocking repository behavior
        when(reportDetailsRepository.existsById(reportId.toString())).thenReturn(false);

        // Calling the service method
        assertThrows(EntityNotFoundException.class, () -> reportDetailsService.deleteReportDetails(reportId.toString()));

        // Verify repository method invocation
        verify(reportDetailsRepository, times(1)).existsById(reportId.toString());
        verify(reportDetailsRepository, never()).deleteById(reportId.toString());
    }
}

