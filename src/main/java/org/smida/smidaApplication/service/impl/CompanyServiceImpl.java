package org.smida.smidaApplication.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.smida.smidaApplication.dto.CompanyDto;
import org.smida.smidaApplication.entity.Company;
import org.smida.smidaApplication.excaption.ResourceNotFoundException;
import org.smida.smidaApplication.mapper.CompanyMapper;
import org.smida.smidaApplication.repository.CompanyRepository;
import org.smida.smidaApplication.service.CompanyService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * Service class to manage companies.
 */
@AllArgsConstructor
@Slf4j
@Service
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;

    /**
     * Retrieves a list of all companies.
     *
     * @return List of CompanyDto representing all companies.
     */
    @Override
    public List<CompanyDto> getAllCompanies() {
        log.info("Fetching all companies");
        List<Company> companies = companyRepository.findAll();
        log.info("Fetched {} companies", companies.size());
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
        log.info("Fetching company with ID: {}", id);
        return companyRepository.findById(id)
                .map(CompanyMapper.INSTANCE::companyToCompanyDto)
                .orElseThrow(() -> {
                    log.error("Company not found with ID: {}", id);
                    return new ResourceNotFoundException("Company", "id", id);
                });
    }

    /**
     * Saves a new company.
     *
     * @param companyDto - CompanyDto object containing the details of the company to be saved.
     * @return CompanyDto representing the saved company details.
     */
    @Override
    public CompanyDto saveCompany(CompanyDto companyDto) {
        log.info("Saving new company");
        Company company = CompanyMapper.INSTANCE.companyDtoToCompany(companyDto);
        Company createdCompany = companyRepository.save(company);
        log.info("New company saved with ID: {}", createdCompany.getId());
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
        log.info("Updating company with ID: {}", companyDto.getId());
        Company company = CompanyMapper.INSTANCE.companyDtoToCompany(companyDto);
        Company updatedCompany = companyRepository.save(company);
        log.info("Company with ID: {} updated successfully", companyDto.getId());
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
            log.info("Deleting company with ID: {}", id);
            companyRepository.deleteById(id);
            log.info("Company with ID: {} deleted successfully", id);
            return true;
        } catch (Exception e) {
            log.error("Failed to delete company with ID: {}", id, e);
            return false;
        }
    }
}
