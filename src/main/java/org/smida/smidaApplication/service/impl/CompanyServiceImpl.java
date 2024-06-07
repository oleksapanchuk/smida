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

    /**
     * Retrieves a list of all companies.
     *
     * @return List of CompanyDto representing all companies.
     */
    @Override
    public List<CompanyDto> getAllCompanies() {
        List<Company> companies = companyRepository.findAll();
        return CompanyMapper.INSTANCE.companiesToCompanyDtos(companies);
    }

    /**
     * Retrieves a company based on the provided ID.
     *
     * @param id - UUID of the company to retrieve.
     * @return CompanyDto representing the company details.
     */
    @Override
    public CompanyDto getCompany(UUID id) {
        return companyRepository.findById(id)
                .map(CompanyMapper.INSTANCE::companyToCompanyDto)
                .orElseThrow(() -> new ResourceNotFoundException("Company", "id", id));
    }

    /**
     * Saves a new company.
     *
     * @param companyDto - CompanyDto object containing the details of the company to be saved.
     * @return CompanyDto representing the saved company details.
     */
    @Override
    public CompanyDto saveCompany(CompanyDto companyDto) {
        Company company = CompanyMapper.INSTANCE.companyDtoToCompany(companyDto);
        Company createdCompany = companyRepository.save(company);
        return CompanyMapper.INSTANCE.companyToCompanyDto(createdCompany);
    }

    /**
     * Updates an existing company.
     *
     * @param companyDto - CompanyDto object containing the updated details of the company.
     * @return CompanyDto representing the updated company details.
     */
    @Override
    public CompanyDto updateCompany(CompanyDto companyDto) {
        Company company = CompanyMapper.INSTANCE.companyDtoToCompany(companyDto);
        Company updatedCompany = companyRepository.save(company);
        return CompanyMapper.INSTANCE.companyToCompanyDto(updatedCompany);
    }

    /**
     * Deletes a company based on the provided ID.
     *
     * @param id - UUID of the company to delete.
     * @return boolean indicating if the deletion of the company was successful or not.
     */
    @Override
    public boolean deleteCompany(UUID id) {
        try {
            companyRepository.deleteById(id);
            return true; // Successfully deleted
        } catch (Exception e) {
            e.printStackTrace();
            return false; // Failed to delete
        }
    }
}
