package org.smida.controller;

import org.junit.jupiter.api.Test;
import org.smida.smidaApplication.SmidaApplication;
import org.smida.smidaApplication.dto.ReportDetailsDto;
import org.smida.smidaApplication.service.ReportDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.mockito.Mockito.when;
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
    @WithMockUser
    public void testGetReportDetails() throws Exception {
        UUID uuid = UUID.randomUUID();
        ReportDetailsDto reportDetailsDto = new ReportDetailsDto(); // Create a dummy ReportDetailsDto for testing
        when(reportDetailsService.getReportDetails(uuid)).thenReturn(reportDetailsDto);

        mockMvc.perform(get("/api/v1/reports-details/getReportDetails")
                        .param("uuid", uuid.toString()))
                .andExpect(status().isOk());
    }
}
