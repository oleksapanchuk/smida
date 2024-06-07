package org.smida.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.smida.smidaApplication.dto.ReportDto;
import org.smida.smidaApplication.entity.Report;
import org.smida.smidaApplication.excaption.ResourceNotFoundException;
import org.smida.smidaApplication.repository.ReportRepository;
import org.smida.smidaApplication.service.impl.ReportServiceImpl;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ReportServiceImplTest {

    @Mock
    private ReportRepository reportRepository;

    @InjectMocks
    private ReportServiceImpl reportService;

    private Report report;
    private ReportDto reportDto;
    private UUID reportId;
    private UUID companyId;

    @BeforeEach
    void setUp() {
        reportId = UUID.randomUUID();
        companyId = UUID.randomUUID();
        report = new Report();
        report.setId(reportId);
        report.setCompanyId(companyId);

        reportDto = new ReportDto();
        reportDto.setId(reportId);
        reportDto.setCompanyId(companyId);
    }

    @Test
    void testGetAllReports() {
        when(reportRepository.findAll()).thenReturn(Collections.singletonList(report));
        List<ReportDto> reportDtos = reportService.getAllReports();
        assertNotNull(reportDtos);
        assertEquals(1, reportDtos.size());
        verify(reportRepository, times(1)).findAll();
    }

    @Test
    void testGetReportsByCompanyId() {
        when(reportRepository.findAllByCompanyId(companyId)).thenReturn(Collections.singletonList(report));
        List<ReportDto> reportDtos = reportService.getReportsByCompanyId(companyId);
        assertNotNull(reportDtos);
        assertEquals(1, reportDtos.size());
        verify(reportRepository, times(1)).findAllByCompanyId(companyId);
    }

    @Test
    void testGetReport() {
        when(reportRepository.findById(reportId)).thenReturn(Optional.of(report));
        ReportDto foundReport = reportService.getReport(reportId);
        assertNotNull(foundReport);
        verify(reportRepository, times(1)).findById(reportId);
    }

    @Test
    void testGetReport_NotFound() {
        when(reportRepository.findById(reportId)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> reportService.getReport(reportId));
        verify(reportRepository, times(1)).findById(reportId);
    }

    @Test
    void testSaveReport() {
        when(reportRepository.save(any(Report.class))).thenReturn(report);
        ReportDto savedReport = reportService.saveReport(reportDto);
        assertNotNull(savedReport);
        verify(reportRepository, times(1)).save(any(Report.class));
    }

    @Test
    void testUpdateReport() {
        when(reportRepository.save(any(Report.class))).thenReturn(report);
        ReportDto updatedReport = reportService.updateReport(reportDto);
        assertNotNull(updatedReport);
        verify(reportRepository, times(1)).save(any(Report.class));
    }

    @Test
    void testDeleteReport() {
        doNothing().when(reportRepository).deleteById(reportId);
        boolean isDeleted = reportService.deleteReport(reportId);
        assertTrue(isDeleted);
        verify(reportRepository, times(1)).deleteById(reportId);
    }
}
