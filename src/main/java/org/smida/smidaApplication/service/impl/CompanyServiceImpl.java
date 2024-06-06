package org.smida.smidaApplication.service.impl;

import org.smida.smidaApplication.dto.CompanyDto;
import org.smida.smidaApplication.entity.Company;
import org.smida.smidaApplication.excaption.ResourceNotFoundException;
import org.smida.smidaApplication.mapper.CompanyMapper;
import org.smida.smidaApplication.repository.CompanyRepository;
import org.smida.smidaApplication.service.CompanyService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;

    public CompanyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public List<CompanyDto> getAllCompanies() {
        List<Company> companies = companyRepository.findAll();
        return CompanyMapper.INSTANCE.companiesToCompanyDtos(companies);
    }

    @Override
    public CompanyDto getCompany(UUID id) {
        return companyRepository.findById(id)
                .map(CompanyMapper.INSTANCE::companyToCompanyDto)
                .orElseThrow(() -> new ResourceNotFoundException("Company", "id", id));
    }

    @Override
    public CompanyDto saveCompany(CompanyDto companyDto) {
        Company company = CompanyMapper.INSTANCE.companyDtoToCompany(companyDto);
        Company createdCompany = companyRepository.save(company);
        return CompanyMapper.INSTANCE.companyToCompanyDto(createdCompany);
    }

    @Override
    public CompanyDto updateCompany(CompanyDto companyDto) {
        Company company = CompanyMapper.INSTANCE.companyDtoToCompany(companyDto);
        Company updatedCompany = companyRepository.save(company);
        return CompanyMapper.INSTANCE.companyToCompanyDto(updatedCompany);
    }

    @Override
    public boolean deleteCompany(UUID id) {
        companyRepository.deleteById(id);
        return true;
    }
}
