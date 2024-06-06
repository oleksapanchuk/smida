package org.smida.smidaApplication.service;

import org.smida.smidaApplication.dto.CompanyDto;

import java.util.List;
import java.util.UUID;

public interface CompanyService {
    List<CompanyDto> getAllCompanies();

    CompanyDto getCompany(UUID id);

    CompanyDto saveCompany(CompanyDto companyDto);

    CompanyDto updateCompany(CompanyDto companyDto);

    boolean deleteCompany(UUID id);
}
