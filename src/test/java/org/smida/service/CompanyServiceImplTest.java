package org.smida.service;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.smida.smidaApplication.dto.CompanyDto;
import org.smida.smidaApplication.entity.Company;
import org.smida.smidaApplication.excaption.ResourceNotFoundException;
import org.smida.smidaApplication.repository.CompanyRepository;
import org.smida.smidaApplication.service.impl.CompanyServiceImpl;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CompanyServiceImplTest {

    @Mock
    private CompanyRepository companyRepository;

    @InjectMocks
    private CompanyServiceImpl companyService;

    private Company company;
    private CompanyDto companyDto;
    private UUID companyId;

    @BeforeEach
    void setUp() {
        companyId = UUID.randomUUID();
        company = new Company();
        company.setId(companyId);
        company.setName("Test Company");

        companyDto = new CompanyDto();
        companyDto.setId(companyId);
        companyDto.setName("Test Company");
    }

    @Test
    void testGetAllCompanies() {
        when(companyRepository.findAll()).thenReturn(Collections.singletonList(company));
        List<CompanyDto> companyDtos = companyService.getAllCompanies();
        assertNotNull(companyDtos);
        assertEquals(1, companyDtos.size());
        assertEquals("Test Company", companyDtos.get(0).getName());
        verify(companyRepository, times(1)).findAll();
    }

    @Test
    void testGetCompany() {
        when(companyRepository.findById(companyId)).thenReturn(Optional.of(company));
        CompanyDto foundCompany = companyService.getCompany(companyId);
        assertNotNull(foundCompany);
        assertEquals("Test Company", foundCompany.getName());
        verify(companyRepository, times(1)).findById(companyId);
    }

    @Test
    void testGetCompany_NotFound() {
        when(companyRepository.findById(companyId)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> companyService.getCompany(companyId));
        verify(companyRepository, times(1)).findById(companyId);
    }

    @Test
    void testSaveCompany() {
        when(companyRepository.save(any(Company.class))).thenReturn(company);
        CompanyDto savedCompany = companyService.saveCompany(companyDto);
        assertNotNull(savedCompany);
        assertEquals("Test Company", savedCompany.getName());
        verify(companyRepository, times(1)).save(any(Company.class));
    }

    @Test
    void testUpdateCompany() {
        when(companyRepository.save(any(Company.class))).thenReturn(company);
        CompanyDto updatedCompany = companyService.updateCompany(companyDto);
        assertNotNull(updatedCompany);
        assertEquals("Test Company", updatedCompany.getName());
        verify(companyRepository, times(1)).save(any(Company.class));
    }

    @Test
    void testDeleteCompany() {
        doNothing().when(companyRepository).deleteById(companyId);
        boolean isDeleted = companyService.deleteCompany(companyId);
        assertTrue(isDeleted);
        verify(companyRepository, times(1)).deleteById(companyId);
    }
}
