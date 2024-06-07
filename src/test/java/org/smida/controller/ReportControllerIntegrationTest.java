package org.smida.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.junit.jupiter.MockitoExtension;
import org.smida.smidaApplication.SmidaApplication;
import org.smida.smidaApplication.controller.ReportController;
import org.smida.smidaApplication.dto.ReportDto;
import org.smida.smidaApplication.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = SmidaApplication.class)
@AutoConfigureMockMvc
public class ReportControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ReportService reportService;

    @Test
    @WithMockUser
    public void testGetAllReports() throws Exception {
        when(reportService.getAllReports()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/api/v1/reports/getAllReports"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    public void testGetReportsByCompanyId() throws Exception {
        UUID companyId = UUID.randomUUID();
        when(reportService.getReportsByCompanyId(companyId)).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/api/v1/reports/getReportsByCompanyId").param("companyId", companyId.toString()))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    public void testGetReport() throws Exception {
        UUID uuid = UUID.randomUUID();
        when(reportService.getReport(uuid)).thenReturn(new ReportDto());

        mockMvc.perform(get("/api/v1/reports/getReport").param("uuid", uuid.toString()))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    void testSaveReport() throws Exception {
        ReportDto reportDto = new ReportDto();
        String jsonReportDto = new ObjectMapper().writeValueAsString(reportDto);

        when(reportService.saveReport(any())).thenReturn(reportDto);

        mockMvc.perform(post("/api/v1/reports/saveReport")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonReportDto))
                .andExpect(status().isCreated());
    }

    @Test
    @WithMockUser
    void testUpdateReport() throws Exception {
        ReportDto reportDto = new ReportDto();
        reportDto.setId(UUID.randomUUID());
        String jsonReportDto = new ObjectMapper().writeValueAsString(reportDto);

        when(reportService.updateReport(any())).thenReturn(reportDto);

        mockMvc.perform(put("/api/v1/reports/updateReport")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonReportDto))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    void testDeleteReport() throws Exception {
        UUID uuid = UUID.randomUUID();
        when(reportService.deleteReport(uuid)).thenReturn(true);

        mockMvc.perform(delete("/api/v1/reports/deleteReport")
                        .param("uuid", uuid.toString()))
                .andExpect(status().isOk());
    }
}
