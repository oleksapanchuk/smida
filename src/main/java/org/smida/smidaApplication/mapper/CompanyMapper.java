package org.smida.smidaApplication.mapper;

import org.smida.smidaApplication.dto.CompanyDto;
import org.smida.smidaApplication.entity.Company;

import java.util.List;
import java.util.stream.Collectors;

public class CompanyMapper {

    // Convert Company entity to CompanyDto
    public static CompanyDto toDto(Company company) {
        if (company == null) {
            return null;
        }

        CompanyDto dto = new CompanyDto();
        dto.setId(company.getId());
        dto.setName(company.getName());
        dto.setRegistrationNumber(company.getRegistrationNumber());
        dto.setAddress(company.getAddress());
        dto.setCreatedAt(company.getCreatedAt());

        return dto;
    }

    // Convert CompanyDto to Company entity
    public static Company toEntity(CompanyDto dto) {
        if (dto == null) {
            return null;
        }

        Company company = new Company();
        company.setId(dto.getId());
        company.setName(dto.getName());
        company.setRegistrationNumber(dto.getRegistrationNumber());
        company.setAddress(dto.getAddress());
        company.setCreatedAt(dto.getCreatedAt());

        return company;
    }

    // Convert List of Company entities to List of CompanyDto
    public static List<CompanyDto> companiesToCompanyDtos(List<Company> companies) {
        if (companies == null || companies.isEmpty()) {
            return null;
        }

        return companies.stream()
                .map(CompanyMapper::toDto)
                .collect(Collectors.toList());
    }
}
