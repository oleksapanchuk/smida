package org.smida.smidaApplication.service;

import org.smida.smidaApplication.dto.CompanyDto;

import java.util.List;
import java.util.UUID;

public interface CompanyService {

    /**
     * Retrieves a list of all companies.
     *
     * @return List of CompanyDto representing all companies.
     */
    List<CompanyDto> getAllCompanies();

    /**
     * Retrieves a company based on the provided ID.
     *
     * @param id - UUID of the company to retrieve.
     * @return CompanyDto representing the company details.
     */
    CompanyDto getCompany(UUID id);

    /**
     * Saves a new company.
     *
     * @param companyDto - CompanyDto object containing the details of the company to be saved.
     * @return CompanyDto representing the saved company details.
     */
    CompanyDto saveCompany(CompanyDto companyDto);

    /**
     * Updates an existing company.
     *
     * @param companyDto - CompanyDto object containing the updated details of the company.
     * @return CompanyDto representing the updated company details.
     */
    CompanyDto updateCompany(CompanyDto companyDto);

    /**
     * Deletes a company based on the provided ID.
     *
     * @param id - UUID of the company to delete.
     * @return boolean indicating if the deletion of the company was successful or not.
     */
    boolean deleteCompany(UUID id);
}
