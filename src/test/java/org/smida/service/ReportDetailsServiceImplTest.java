package org.smida.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.smida.smidaApplication.dto.ReportDetailsDto;
import org.smida.smidaApplication.entity.ReportDetails;
import org.smida.smidaApplication.mapper.ReportDetailsMapper;
import org.smida.smidaApplication.repository.ReportDetailsRepository;
import org.smida.smidaApplication.service.impl.ReportDetailsServiceImpl;

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
        // Initialize other fields of reportDetails as needed

        reportDetailsDto = new ReportDetailsDto();
        reportDetailsDto.setReportId(reportId);
        // Initialize other fields of reportDetailsDto as needed
    }

    @Test
    void testGetReportDetails() {
        when(reportDetailsRepository.findByReportId(reportId)).thenReturn(reportDetails);

        ReportDetailsDto result = reportDetailsService.getReportDetails(reportId);

        assertNotNull(result);
        assertEquals(reportId, result.getReportId());
        // Add assertions for other fields as needed
        verify(reportDetailsRepository, times(1)).findByReportId(reportId);
    }

    @Test
    void testGetReportDetails_NotFound() {
        when(reportDetailsRepository.findByReportId(reportId)).thenReturn(null);

        ReportDetailsDto result = reportDetailsService.getReportDetails(reportId);

        assertNull(result);
        verify(reportDetailsRepository, times(1)).findByReportId(reportId);
    }
}

