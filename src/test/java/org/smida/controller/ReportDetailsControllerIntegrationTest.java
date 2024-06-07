package org.smida.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.smida.smidaApplication.SmidaApplication;
import org.smida.smidaApplication.dto.ReportDetailsDto;
import org.smida.smidaApplication.service.ReportDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = SmidaApplication.class)
@AutoConfigureMockMvc
public class ReportDetailsControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ReportDetailsService reportDetailsService;

    @Test
    void testGetReportDetails() throws Exception {
        UUID uuid = UUID.randomUUID();
        ReportDetailsDto mockDto = new ReportDetailsDto();
        // Mocking service behavior
        when(reportDetailsService.getReportDetails(uuid)).thenReturn(mockDto);

        mockMvc.perform(get("/api/v1/report-details/getReportDetails")
                        .param("uuid", uuid.toString())
                        .contentType(APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "admin", roles = { "ADMIN" })
    void testCreateReportDetails() throws Exception {
        ReportDetailsDto requestDto = new ReportDetailsDto();
        String jsonRequestDto = new ObjectMapper().writeValueAsString(requestDto);
        ReportDetailsDto mockDto = new ReportDetailsDto();
        // Mocking service behavior
        when(reportDetailsService.saveReportDetails(any())).thenReturn(mockDto);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/report-details/admin/createReportDetails")
                        .content(jsonRequestDto)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    @WithMockUser(username = "admin", roles = { "ADMIN" })
    void testUpdateReportDetails() throws Exception {
        ReportDetailsDto requestDto = new ReportDetailsDto();
        String jsonRequestDto = new ObjectMapper().writeValueAsString(requestDto);
        ReportDetailsDto mockDto = new ReportDetailsDto();
        // Mocking service behavior
        when(reportDetailsService.updateReportDetails(any())).thenReturn(mockDto);

        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/report-details/admin/updateReportDetails")
                        .content(jsonRequestDto)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @WithMockUser(username = "admin", roles = { "ADMIN" })
    void testDeleteReportDetails() throws Exception {
        UUID id = UUID.randomUUID();
        // Mocking service behavior
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/report-details/admin/deleteReportDetails")
                        .param("id", id.toString()))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }


}
