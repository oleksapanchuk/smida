package org.smida.smidaApplication.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.smida.smidaApplication.dto.CompanyDto;
import org.smida.smidaApplication.entity.Company;

import java.util.List;

@Mapper
public interface CompanyMapper {
    CompanyMapper INSTANCE = Mappers.getMapper(CompanyMapper.class);

    CompanyDto companyToCompanyDto(Company company);

    Company companyDtoToCompany(CompanyDto companyDto);

    List<CompanyDto> companiesToCompanyDtos(List<Company> companies);
}
