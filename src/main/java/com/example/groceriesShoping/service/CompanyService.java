package com.example.groceriesShoping.service;

import com.example.groceriesShoping.dto.CompanyDto;
import com.example.groceriesShoping.model.Company;

import java.util.List;

public interface CompanyService {
    Company findCompanyById(Long id);

    List<Company> findAllCompanies();
    Company addCompany(CompanyDto company);
    Company updateCompany(Long companyId,CompanyDto company);
    void deleteCompany(Long id);

}
