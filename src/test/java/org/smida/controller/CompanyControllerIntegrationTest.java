package org.smida.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.smida.smidaApplication.SmidaApplication;
import org.smida.smidaApplication.dto.CompanyDto;
import org.smida.smidaApplication.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Collections;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = SmidaApplication.class)
@AutoConfigureMockMvc
public class CompanyControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CompanyService companyService;

    @Test
    @WithMockUser
    public void testGetAllCompanies() throws Exception {
        when(companyService.getAllCompanies()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/api/v1/companies/getAllCompanies"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    public void testGetCompany() throws Exception {
        UUID uuid = UUID.randomUUID();
        when(companyService.getCompany(uuid)).thenReturn(new CompanyDto());

        mockMvc.perform(get("/api/v1/companies/getCompany").param("uuid", uuid.toString()))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "admin", roles = { "ADMIN" })
    void testSaveCompany() throws Exception {
        CompanyDto companyDto = new CompanyDto();
        companyDto.setName("Test Company");
        String jsonCompanyDto = new ObjectMapper().writeValueAsString(companyDto);

        when(companyService.saveCompany(any())).thenReturn(companyDto);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/companies/admin/saveCompany")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonCompanyDto))
                .andExpect(status().isCreated());
    }

    @Test
    @WithMockUser(username = "admin", roles = { "ADMIN" })
    void testUpdateCompany() throws Exception {
        CompanyDto companyDto = new CompanyDto();
        companyDto.setId(UUID.randomUUID());
        companyDto.setName("Updated Test Company");
        String jsonCompanyDto = new ObjectMapper().writeValueAsString(companyDto);

        when(companyService.updateCompany(any())).thenReturn(companyDto);

        mockMvc.perform(put("/api/v1/companies/admin/updateCompany")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonCompanyDto))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "admin", roles = { "ADMIN" })
    void testDeleteCompany() throws Exception {
        UUID uuid = UUID.randomUUID();
        when(companyService.deleteCompany(uuid)).thenReturn(true);

        mockMvc.perform(delete("/api/v1/companies/admin/deleteCompany")
                        .param("uuid", uuid.toString()))
                .andExpect(status().isOk());
    }
}
